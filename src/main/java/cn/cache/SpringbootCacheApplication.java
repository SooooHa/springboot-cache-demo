package cn.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 缓存的使用
 * 步骤
 * 1. 启动类开启注解缓存
 * 2. 标记缓存注解即可
 * @EnableCaching 开启缓存
 * @Cacheable 针对方法配置,根据方法的请求参数对结果缓存
 * @CacheEvict 清空缓存
 * @CachePut 保证方法被调用,又希望结果被缓存
 * @EnableCaching 开启基于注解的缓存
 */

@EnableCaching
@MapperScan("cn.cache.dao")
@SpringBootApplication
public class SpringbootCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheApplication.class, args);
    }

}



