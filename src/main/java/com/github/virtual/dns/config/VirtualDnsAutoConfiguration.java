package com.github.virtual.dns.config;

import com.github.virtual.dns.service.VirtualDnsTemplate;
import com.github.virtual.dns.service.impl.VirtualDnsTemplateImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 创建时间为 下午4:45 2019/12/13
 * 项目名称 virtual-dns-spring-boot-starter
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Configuration
@EnableConfigurationProperties
public class VirtualDnsAutoConfiguration {

    /**
     * 创建虚拟 DNS Template
     *
     * @return VirtualDnsTemplate
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "com.github.virtual.dns", value = "enabled", matchIfMissing = true)
    public VirtualDnsTemplate virtualDnsTemplate() {
        return new VirtualDnsTemplateImpl();
    }

}
