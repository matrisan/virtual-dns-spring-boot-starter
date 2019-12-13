package com.github.virtual.dns.service;

import com.github.virtual.dns.pojo.VirtualHostBO;

import java.util.Collection;
import java.util.Set;

/**
 * <p>
 * 创建时间为 下午3:46 2019/12/13
 * 项目名称 virtual-dns-spring-boot-starter
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public interface VirtualDnsTemplate {

    /**
     * 获取所有的虚拟 DNS
     *
     * @return Set
     */
    Set<VirtualHostBO> getAllVirtualDns();

    /**
     * 保存单个 DNS 信息
     *
     * @param virtualHost 虚拟 DNS 信息
     * @return VirtualHostBO
     */
    VirtualHostBO saveVirtualDns(VirtualHostBO virtualHost);

    /**
     * 保存很多 虚拟 DNS 信息
     *
     * @param virtualHosts 很多 DNS 信息
     * @return Collection
     */
    Collection<VirtualHostBO> saveAllVirtualDns(Collection<VirtualHostBO> virtualHosts);

    /**
     * 更新单个 DNS 信息
     *
     * @param virtualHost 虚拟 DNS 信息
     * @return VirtualHostBO
     */
    VirtualHostBO updateVirtualDns(VirtualHostBO virtualHost);

    /**
     * 删除单个域名的 DNS
     *
     * @param host 域名
     */
    void deleteVirtualDns(String host);

    /**
     * 清除所有的 DNS 信息
     */
    void clearAll();

}
