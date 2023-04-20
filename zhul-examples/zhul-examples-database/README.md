# zhul-examples-database

## 项目说明

本项目演示如何集成数据库中间件。

## 示例

### 接入准备

1. 接入前需先部署Nacos配置中心

2. 在配置中心创建如下配置：

```
# Database config
spring.shardingsphere.datasource.names=master-0,slave-0,slave-1

spring.shardingsphere.datasource.master-0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.master-0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.master-0.url=jdbc:mysql://127.0.0.1:3306/xxxx?useUnicode=true&serverTimezone=UTC&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull&&useSSL=false
spring.shardingsphere.datasource.master-0.username=root
spring.shardingsphere.datasource.master-0.password=root
spring.shardingsphere.datasource.master-0.max-active=5
spring.shardingsphere.datasource.master-0.initial-size=2
spring.shardingsphere.datasource.master-0.max-wait=60000
spring.shardingsphere.datasource.master-0.min-idle=1
spring.shardingsphere.datasource.master-0.time-between-eviction-runs-millis=60000
spring.shardingsphere.datasource.master-0.min-evictable-idle-time-millis=300000
spring.shardingsphere.datasource.master-0.validation-query=SELECT 'x'
spring.shardingsphere.datasource.master-0.test-while-idle=true
spring.shardingsphere.datasource.master-0.test-on-borrow=false
spring.shardingsphere.datasource.master-0.test-on-return=false
spring.shardingsphere.datasource.master-0.pool-prepared-statements=true
spring.shardingsphere.datasource.master-0.max-open-prepared-statements=20

spring.shardingsphere.datasource.slave-0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.slave-0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.slave-0.url=jdbc:mysql://127.0.0.1:3306/xxxx?useUnicode=true&serverTimezone=UTC&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull&&useSSL=false
spring.shardingsphere.datasource.slave-0.username=root
spring.shardingsphere.datasource.slave-0.password=root
spring.shardingsphere.datasource.slave-0.max-active=5
spring.shardingsphere.datasource.slave-0.initial-size=2
spring.shardingsphere.datasource.slave-0.max-wait=60000
spring.shardingsphere.datasource.slave-0.min-idle=1
spring.shardingsphere.datasource.slave-0.time-between-eviction-runs-millis=60000
spring.shardingsphere.datasource.slave-0.min-evictable-idle-time-millis=300000
spring.shardingsphere.datasource.slave-0.validation-query=SELECT 'x'
spring.shardingsphere.datasource.slave-0.test-while-idle=true
spring.shardingsphere.datasource.slave-0.test-on-borrow=false
spring.shardingsphere.datasource.slave-0.test-on-return=false
spring.shardingsphere.datasource.slave-0.pool-prepared-statements=true
spring.shardingsphere.datasource.slave-0.max-open-prepared-statements=20

spring.shardingsphere.datasource.slave-1.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.slave-1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.slave-1.url=jdbc:mysql://127.0.0.1:3306/xxxx?useUnicode=true&serverTimezone=UTC&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull&&useSSL=false
spring.shardingsphere.datasource.slave-1.username=root
spring.shardingsphere.datasource.slave-1.password=root
spring.shardingsphere.datasource.slave-1.max-active=5
spring.shardingsphere.datasource.slave-1.initial-size=2
spring.shardingsphere.datasource.slave-1.max-wait=60000
spring.shardingsphere.datasource.slave-1.min-idle=1
spring.shardingsphere.datasource.slave-1.time-between-eviction-runs-millis=60000
spring.shardingsphere.datasource.slave-1.min-evictable-idle-time-millis=300000
spring.shardingsphere.datasource.slave-1.validation-query=SELECT 'x'
spring.shardingsphere.datasource.slave-1.test-while-idle=true
spring.shardingsphere.datasource.slave-1.test-on-borrow=false
spring.shardingsphere.datasource.slave-1.test-on-return=false
spring.shardingsphere.datasource.slave-1.pool-prepared-statements=true
spring.shardingsphere.datasource.slave-1.max-open-prepared-statements=20

spring.shardingsphere.rules.readwrite-splitting.data-sources.zhul-ds.static-strategy.write-data-source-name=master-0
spring.shardingsphere.rules.readwrite-splitting.data-sources.zhul-ds.static-strategy.read-data-source-names=slave-0,slave-1
spring.shardingsphere.rules.readwrite-splitting.data-sources.zhul-ds.load-balancer-name=round_robin
spring.shardingsphere.rules.readwrite-splitting.load-balancers.round_robin.type=ROUND_ROBIN

spring.shardingsphere.props.sql-show=true

# Mybatis config
mybatis-plus.mapper-locations=classpath*:/mapper/*Mapper.xml
mybatis-plus.global-config.id-type=2
mybatis-plus.global-config.field-strategy=2
mybatis-plus.global-config.db-column-underline=true
mybatis-plus.configuration.map-underscore-to-camel-case=true
```

3.执行如下脚本：

[初始化脚本](src/main/resources/sql/master-slave-schema.sql)

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
public class ZhulExamplesDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhulExamplesDatabaseApplication.class, args);
	}

}
```

3. 修改 `bootstrap.properties` 文件

```
# 应用名
spring.application.name=zhul-examples-database

# 启用开关|Nepxion组件
zhul.cloud.nepxion.enabled=true

# 启用开关|配置中心组件
zhul.cloud.config.enabled=true

# 启用开关|注册发现组件
zhul.cloud.discovery.enabled=true

# 启用开关|数据库组件
zhul.cloud.database.enabled=true

# 启用开关|消息组件
zhul.cloud.stream.enabled=false

# 启用开关|缓存组件
zhul.cloud.cache.enabled=false

# 启用开关|分布式锁组件
zhul.cloud.lock.enabled=false

# 启用开关|防护组件
zhul.cloud.protector.enabled=false

# 启用开关|分布式事务组件
zhul.cloud.transaction.enabled=false


```

修改 `application.properties` 文件

```
server.port=8010
```

*框架层面已内置了最佳配置

### 应用启动

跟原生的Spring Boot应用程序启动方式一致

### 示例验证

```
POST  http://localhost:8010/v1/orders
GET   http://localhost:8010/v1/orders
GET   http://localhost:8010/v1/orders/1
```

### 最佳实践
[读写分离](docs/读写分离.md)

[数据分片](docs/数据分片.md)