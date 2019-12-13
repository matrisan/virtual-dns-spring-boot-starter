虚拟 VIRTUAL-DNS


# 使用方法,在 POM 中加入如下依赖
```
<dependency>
    <groupId>com.github</groupId>
    <artifactId>virtual-dns-spring-boot-starter</artifactId>
    <version>0.0.1</version>
</dependency>
```

# 配置文件中的配置
使用下面配置文件生效,默认为true
```
com.github.virtual.dns.enabled=true
```

# 在代码中使用
```
@Resource
private VirtualDnsTemplate virtualDnsTemplate;
```







