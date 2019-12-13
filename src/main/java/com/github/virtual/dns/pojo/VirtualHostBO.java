package com.github.virtual.dns.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 创建时间为 下午3:33 2019/12/13
 * 项目名称 virtual-dns-spring-boot-starter
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VirtualHostBO implements Serializable {

    private static final long serialVersionUID = 8779238901928101451L;

    private String host;

    private Set<String> ips;

    private Long expiration;

}
