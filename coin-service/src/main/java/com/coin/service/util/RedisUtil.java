package com.coin.service.util;

import cn.hutool.core.util.ObjectUtil;
import com.coin.cache.UserExtInfoCache;
import com.coin.service.constant.BizCons;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    //redis地址
    @Value("${spring.redis.host}")
    private String host;
    //redis端口号
    @Value("${spring.redis.port}")
    private int port;
    //redis密码
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private int database;

    private final String namespace = "public-api-office:";

    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void initRedisTemplate() {
        redisTemplate = redisTemplate(database);
    }

    public static String getOfficeKey(String keyStr) {
        return BizCons.SYS_OFFICE + keyStr;
    }

    public static String getApiKey(String keyStr) {
        return BizCons.SYS_API + keyStr;
    }

    public void set(String k, Object v, long second) {
        String key = namespace + k;
        redisTemplate.opsForValue().set(key, v, second, TimeUnit.SECONDS);
    }

    public void set(String k, Object v) {
        set(k, v, 360000);
    }

    public String get(String k) {
        Object o = redisTemplate.opsForValue().get(namespace + k);
        if (ObjectUtil.isNotNull(o)) {
            return o + "";
        }
        return null;
    }

    public void remove(String key) {
        redisTemplate.delete(namespace + key);
    }

    public void setExpire(String key, long time) {
        redisTemplate.expire(namespace + key, time, TimeUnit.SECONDS);
    }

    public ValueOperations<String, Object> getRedisTemplateForObject() {
        return redisTemplate.opsForValue();
    }

    public HashOperations<String, Object, Object> getRedisTemplateForHash() {
        return redisTemplate.opsForHash();
    }

    public HashOperations<String, String, UserExtInfoCache> getRedisTemplateForUserExtInfoCache() {
        return redisTemplate.opsForHash();
    }

    private LettuceConnectionFactory redisConnection(int db) {
        RedisStandaloneConfiguration server = new RedisStandaloneConfiguration();
        server.setHostName(host); // 指定地址
        server.setDatabase(db); // 指定数据库
        server.setPort(port); //指定端口
        server.setPassword(password); //指定密码
        LettuceConnectionFactory factory = new LettuceConnectionFactory(server);
        factory.afterPropertiesSet(); //刷新配置
        return factory;
    }

    private RedisTemplate<String, Object> redisTemplate(int db) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnection(db)); //设置连接
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //hash序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
