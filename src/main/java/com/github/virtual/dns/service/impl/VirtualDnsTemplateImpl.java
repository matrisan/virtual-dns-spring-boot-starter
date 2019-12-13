package com.github.virtual.dns.service.impl;

import com.github.virtual.dns.pojo.VirtualHostBO;
import com.github.virtual.dns.service.VirtualDnsTemplate;
import com.google.common.collect.Sets;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.joor.Reflect;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 创建时间为 下午3:50 2019/12/13
 * 项目名称 virtual-dns-spring-boot-starter
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Service
public class VirtualDnsTemplateImpl implements VirtualDnsTemplate {

    private static final long ABOUT_YEAR = 3600 * 24 * 1000L * 365;

    private static final long EXPIRATION = ABOUT_YEAR * 10;

    @Override
    public Set<VirtualHostBO> getAllVirtualDns() {
        final Set<VirtualHostBO> set = Sets.newHashSet();
        Object cache = Reflect.onClass(InetAddress.class).get("addressCache");
        Map<String, Object> map = Reflect.on(cache).get("cache");
        map.forEach((k, v) -> {
            InetAddress[] addresses = Reflect.on(v).get("addresses");
            Set<String> stringSet = Sets.newHashSet(addresses).stream()
                    .map(InetAddress::getHostAddress)
                    .collect(Collectors.toSet());
            VirtualHostBO virtualHost = new VirtualHostBO();
            virtualHost.setHost(k);
            virtualHost.setIps(stringSet);
            virtualHost.setExpiration(Reflect.on(v).get("expiration"));
            set.add(virtualHost);
        });
        return set;
    }

    @Override
    public VirtualHostBO saveVirtualDns(@NotNull VirtualHostBO virtualHost) {
        String host = virtualHost.getHost();
        String[] ips = virtualHost.getIps().toArray(new String[0]);
        // 获取缓存信息并生产新的虚拟 DNS 数据
        Object cache = Reflect.onClass(InetAddress.class).get("addressCache");
        Map<String, Object> map = Reflect.on(cache).get("cache");
        Object object = createCacheEntry(host, ips);
        map.put(host, object);
        // 根据返回的数据提取元素填充 VirtualHost
        InetAddress[] addresses = Reflect.on(object).get("addresses");
        Long expiration = Reflect.on(object).get("expiration");
        Set<String> hostName = new HashSet<>();
        Set<String> ipAddress = new HashSet<>();
        for (InetAddress address : addresses) {
            String hostname = address.getHostName();
            String hostAddress = address.getHostAddress();
            hostName.add(hostname);
            ipAddress.add(hostAddress);
        }
        assert 1 == hostName.size();
        VirtualHostBO virtualHost1 = new VirtualHostBO();
        virtualHost1.setHost(hostName.iterator().next());
        virtualHost1.setIps(ipAddress);
        virtualHost1.setExpiration(expiration);
        return virtualHost1;
    }

    @Override
    public Collection<VirtualHostBO> saveAllVirtualDns(@NotNull Collection<VirtualHostBO> virtualHosts) {
        return virtualHosts.stream().map(this::saveVirtualDns).collect(Collectors.toSet());
    }

    @Override
    public VirtualHostBO updateVirtualDns(VirtualHostBO virtualHost) {
        return saveVirtualDns(virtualHost);
    }

    @Override
    public void deleteVirtualDns(String host) {
        Object cache = Reflect.onClass(InetAddress.class).get("addressCache");
        Map<String, Object> map = Reflect.on(cache).get("cache");
        map.remove(host);
    }

    @Override
    public void clearAll() {
        Object cache = Reflect.onClass(InetAddress.class).get("addressCache");
        Map<String, Object> map = Reflect.on(cache).get("cache");
        map.clear();
    }

    @SneakyThrows(UnknownHostException.class)
    private Object createCacheEntry(String host, @NotNull String[] ips) {
        long expiration = System.currentTimeMillis() + EXPIRATION;
        InetAddress[] addresses = new InetAddress[ips.length];
        for (int i = 0; i < ips.length; i++) {
            addresses[i] = InetAddress.getByAddress(host, InetAddress.getByName(ips[i]).getAddress());
        }
        return Reflect.onClass("java.net.InetAddress$CacheEntry").create(addresses, expiration).get();
    }


}
