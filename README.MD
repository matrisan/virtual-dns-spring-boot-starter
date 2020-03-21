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
首先启动一个服务
```
@GetMapping("/date1")
public Date getDate() {
    return new Date();
}
```


初始化数据
```
@Component
public class InitVirtualDnsData implements CommandLineRunner {

    @Resource
    private VirtualDnsTemplate virtualDnsTemplate;

    @Override
    public void run(String... args) throws Exception {
        Set<String> sets = new HashSet<>();
        sets.add("172.28.16.156");
        VirtualHostBO virtualHostBO = VirtualHostBO.builder()
                .host("www.ssd.com")
                .ips(sets)
                .expiration(999999999L)
                .build();
        virtualDnsTemplate.saveVirtualDns(virtualHostBO);
    }
}
```

调用接口
```
@RestController
public class TestController {
    
    @GetMapping("/test")
    public String getString(){
        return new RestTemplate().getForObject("http://www.ssd.com:6500/date1", String.class);
    }

}
```






