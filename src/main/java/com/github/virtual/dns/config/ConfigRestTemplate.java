//package com.github.virtual.dns.config;
//
//import org.apache.http.Header;
//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
//import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.message.BasicHeader;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.http.converter.FormHttpMessageConverter;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
//import org.springframework.web.client.DefaultResponseErrorHandler;
//import org.springframework.web.client.RestTemplate;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * <p>
// * 创建时间为 下午3:32 2019/12/13
// * 项目名称 virtual-dns-spring-boot-starter
// * </p>
// *
// * @author 石少东
// * @version 0.0.1
// * @since 0.0.1
// */
//
//@Configuration
//public class ConfigRestTemplate {
//
//
//    @Bean
//    public RestTemplate getRestTemplate() {
//        // 长连接保持 99 天
//        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager(1, TimeUnit.MINUTES);
//
//        // 总连接数
//        poolingConnectionManager.setMaxTotal(1000);
//
//        // 同路由的并发数
//        poolingConnectionManager.setDefaultMaxPerRoute(100);
//        HttpClientBuilder httpClientBuilder = HttpClients.custom();
//        httpClientBuilder.setConnectionManager(poolingConnectionManager);
//
//        // 重试次数，默认是3次，没有开启
//        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(10, true));
//
//        // 保持长连接配置，需要在头添加Keep-Alive
//        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
//
//        // 获取内容转换器
//        List<HttpMessageConverter<?>> messageConverters = getHttpMessageConverters();
//
//        // 获取请求头
//        List<Header> headers = getHeaders();
//        httpClientBuilder.setDefaultHeaders(headers);
//        HttpClient httpClient = httpClientBuilder.build();
//        RestTemplate restTemplate = new RestTemplate(messageConverters);
//
//        // httpClient连接配置，底层是配置RequestConfig
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        restTemplate.setRequestFactory(clientHttpRequestFactory);
//        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
//        return restTemplate;
//    }
//
//    @NotNull
//    private List<HttpMessageConverter<?>> getHttpMessageConverters() {
//        // 添加内容转换器
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        messageConverters.add(new FormHttpMessageConverter());
//        messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
//        messageConverters.add(new MappingJackson2HttpMessageConverter());
//        return messageConverters;
//    }
//
//    @NotNull
//    private List<Header> getHeaders() {
//        // 设置请求头
//        List<Header> headers = new ArrayList<>();
//        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
//        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
//        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
//        headers.add(new BasicHeader("Connection", "Keep-Alive"));
//        headers.add(new BasicHeader("X-CSRF-TOKEN", "e35405bf-24f9-4464-b80d-9f9312c5d831"));
//        headers.add(new BasicHeader("Cookie", "SESSION=OGZjMzA1ZGYtZDBjYy00NzYxLWE2NjAtNDYzMWIzZGExNDhh"));
//        return headers;
//    }
//
//
//}
