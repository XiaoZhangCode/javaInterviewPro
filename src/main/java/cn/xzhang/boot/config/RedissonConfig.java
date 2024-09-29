package cn.xzhang.boot.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author code_zhang
 * @Date 2024/9/29 20:37
 * Redisson 配置类
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "spring.redis")
@Slf4j
public class RedissonConfig {


    private String host;

    private Integer port;

    private Integer database;

    private String password;


    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setDatabase(database)
                .setPassword(password);
        RedissonClient redissonClient = Redisson.create(config);
        log.info("RedissonClient 初始化完成");
        return redissonClient;
    }



}
