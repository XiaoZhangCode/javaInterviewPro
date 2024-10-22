package cn.xzhang.boot.aspect;

import com.jd.platform.hotkey.client.callback.JdHotKeyStore;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author code_zhang
 * @Date 2024/10/22 20:48
 */
@Aspect
@Component
public class CacheHotKeyAspect {

    @Around("@annotation(cacheHotKey)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, CacheHotKey cacheHotKey) throws Throwable {
        // 获取方法签名和参数
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        // 获取参数名称和参数值的映射
        Map<String, Object> argsMap = IntStream.range(0, args.length)
                .boxed()
                .collect(Collectors.toMap(i -> signature.getParameterNames()[i], i -> args[i]));

        // 获取指定的参数值
        String paramName = cacheHotKey.param();
        Object paramValue = argsMap.get(paramName);

        if (paramValue == null) {
            throw new IllegalArgumentException("Parameter '" + paramName + "' not found");
        }

        // 如果参数是一个对象，获取对象的指定属性值
        if (!cacheHotKey.paramField().isEmpty()) {
            try {
                Field field = paramValue.getClass().getDeclaredField(cacheHotKey.paramField());
                field.setAccessible(true);
                paramValue = field.get(paramValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("Field '" + cacheHotKey.paramField() + "' not found in parameter '" + paramName + "'");
            }
        }

        // 生成缓存键
        String key = cacheHotKey.keyPrefix() + paramValue;

        // 检查是否是热键
        if (JdHotKeyStore.isHotKey(key)) {
            Object cachedValue = JdHotKeyStore.get(key);
            if (cachedValue != null) {
                return cachedValue;
            }
        }

        // 执行方法
        Object result = joinPoint.proceed();

        // 缓存结果
        JdHotKeyStore.smartSet(key, result);

        return result;
    }


}
