package com.my.base.cache.config;

import com.my.base.cache.RedisCacheManager;
import com.my.base.cache.impl.RedisCacheManagerImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis自动配置
 * 
 */
@Configuration
@EnableConfigurationProperties(JedisProperties.class)
public class BaseJedisConfig {
	@Bean
	public JedisPool jedisPool(JedisPoolConfig jedisConfig,
			JedisProperties jedisProperties) {
		JedisPool jedisPool = new JedisPool(jedisConfig, jedisProperties.getHost(),
				jedisProperties.getPort(), jedisProperties.getTimeout(),
				jedisProperties.getRequirePass());
		return jedisPool;
	}

	@Bean
	public JedisPoolConfig jedisConfig(JedisProperties jedisProperties) {
		JedisPoolConfig config = new JedisPoolConfig();
		if (jedisProperties.getMaxTotal() != null) {
			config.setMaxTotal(jedisProperties.getMaxTotal());
		}
		if (jedisProperties.getMaxIdle() != null) {
			config.setMaxIdle(jedisProperties.getMaxIdle());
		}
		if (jedisProperties.getMinIdle() != null) {
			config.setMinIdle(jedisProperties.getMinIdle());
		}
		return config;
	}

	@Bean
	public RedisCacheManager redisCacheManager(JedisPool jedisPool) {
		return new RedisCacheManagerImpl(jedisPool);
	}
}
