package cn.xzhang.boot.aspect;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author code_zhang
 * @Date 2024/10/22 20:47
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheHotKey {

    String keyPrefix() default "hot_key_";

    /**
     * 参数名称 如果是对象参数，则需要指定字段名称 如果不是对象参数 请指定对应的参数名称
     * @return 参数名称
     */
    String param() default "id"; // 默认参数名称为 "id"

    /**
     * 如果是对象参数 此参数必填 这个是对象中的字段名称
     * @return 对象参数的字段名称
     */
    String paramField() default ""; // 对象参数的字段名称

}
