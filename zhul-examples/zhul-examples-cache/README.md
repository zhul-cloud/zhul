# zhul-examples-cache

## 项目说明

本项目演示如何使用 CacheProvider、LockProvider 完成分布式缓存和分布式锁对业务的集成。


## 示例

### 如何接入

1. 首先，修改 `pom.xml` 文件， 引入 zhul-framework-starter-service。

````
		<dependency>
        <groupId>com.zhul.cloud</groupId>
        <artifactId>zhul-framework-starter-service</artifactId>
		</dependency>
````

2. 添加注解

```java
@EnableZhulService
public class ZhulExamplesCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhulExamplesCacheApplication.class, args);
	}

}
```

3. 修改 `application.properties` 文件

```
spring.application.name=zhul-examples-cache
server.port=8070
```

*框架层面已内置了最佳配置

4. Bean注入

```
  @Autowired
  private CacheProvider cacheProvider;
  
  @Autowired
  private LockProvider lockProvider;
```

### 应用启动

跟原生的Spring Boot应用程序启动方式一致

### 示例验证

使用Postman或者其他工具进行示例验证。

```
POST  http://localhost:8070/v1/caches/zhangsan
GET   http://localhost:8070/v1/caches/zhangsan
```

```
POST  http://localhost:8070/v1/lock
POST  http://localhost:8070/v1/try/lock
```