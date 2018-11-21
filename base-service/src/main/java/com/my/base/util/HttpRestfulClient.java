package com.my.base.util;

import java.util.Map;

import com.my.base.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

/**
 * 只支持返回json和xml格式
 *
 */
@Configuration
public class HttpRestfulClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpRestfulClient.class);
    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate        restTemplate;

    public <T> T doPost(Class<T> responseClazz, Object request, HttpHeaders headers,
                        String reqUrl) throws BizException {
        T result = null;
        Gson json = new Gson();
        try {
            HttpEntity<Object> requestEntity = new HttpEntity<Object>(request, headers);
            result = this.restTemplate.postForObject(reqUrl, requestEntity, responseClazz);
        } catch (HttpClientErrorException arg7) {
            logger.info("-------------------------------------------------{}",
                arg7.getStatusText());
            logger.info("-------------------------------------------------{}",
                arg7.getStatusCode());
            logger.info("response={}", arg7.getResponseBodyAsString());
            result = (T) json.fromJson(arg7.getResponseBodyAsString(), responseClazz);
        } catch (HttpServerErrorException arg8) {
            logger.info("-------------------------------------------------{}",
                arg8.getStatusText());
            logger.info("-------------------------------------------------{}",
                arg8.getStatusCode());
            logger.info("response={}", arg8.getResponseBodyAsString());
            result = (T) json.fromJson(arg8.getResponseBodyAsString(), responseClazz);
        } catch (Exception arg9) {
            throw new BizException(arg9);
        }

        return result;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> T doPost(Class<T> responseClazz, Object request, String reqUrl) throws BizException {
        T result = null;
        Gson json = new Gson();
        try {
            ResponseEntity e = this.restTemplate.postForEntity(reqUrl, request, responseClazz,
                new Object[0]);
            result = (T) e.getBody();
        } catch (HttpClientErrorException arg7) {
            logger.info("-------------------------------------------------{}",
                arg7.getStatusText());
            logger.info("-------------------------------------------------{}",
                arg7.getStatusCode());
            logger.info("response={}", arg7.getResponseBodyAsString());
            result = (T) json.fromJson(arg7.getResponseBodyAsString(), responseClazz);
        } catch (HttpServerErrorException arg8) {
            logger.info("-------------------------------------------------{}",
                arg8.getStatusText());
            logger.info("-------------------------------------------------{}",
                arg8.getStatusCode());
            logger.info("response={}", arg8.getResponseBodyAsString());
            result = (T) json.fromJson(arg8.getResponseBodyAsString(), responseClazz);
        } catch (Exception arg9) {
            throw new BizException(arg9);
        }

        return result;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> T doGet(Class<T> responseClazz, Map<String, String> queryPara,
                       String reqUrl) throws BizException {
        T result = null;
        Gson json = new Gson();
        try {
            ResponseEntity e = this.restTemplate.getForEntity(reqUrl, responseClazz, queryPara);
            result = (T) e.getBody();
        } catch (HttpClientErrorException arg7) {
            logger.info("-------------------------------------------------{}",
                arg7.getStatusText());
            logger.info("-------------------------------------------------{}",
                arg7.getStatusCode());
            logger.info("response={}", arg7.getResponseBodyAsString());
            result = (T) json.fromJson(arg7.getResponseBodyAsString(), responseClazz);
        } catch (HttpServerErrorException arg8) {
            logger.info("-------------------------------------------------{}",
                arg8.getStatusText());
            logger.info("-------------------------------------------------{}",
                arg8.getStatusCode());
            logger.info("response={}", arg8.getResponseBodyAsString());
            result = (T) json.fromJson(arg8.getResponseBodyAsString(), responseClazz);
        } catch (Exception arg9) {
            throw new BizException(arg9);
        }

        return result;
    }
}
