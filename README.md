# Zhul【烛龙】企业级云原生微服务框架

## 简介
Zhul【烛龙】企业级云原生微服务基础架构，围绕Discovery【探索】框架打造，基于Spring Cloud Discovery服务注册发
现、Ribbon负载均衡、Feign和RestTemplate调用等组件全方位增强的企业级云原生微服务开源解决方案，面向企业级生产需求精雕
细琢，赋能和助力企业快速搭建基础架构的底层云原生微服务框架，有效的降低企业的落地成本。整个架构体系打造，遵循最严格的
Maven对称结构和规范，最严格的命名格式，给予使用者最舒适的使用体验

## 主要功能

  + **服务限流降级**：支持阿里巴巴Sentinel熔断限流降级权限中间件
  + **服务注册与发现**：支持阿里巴巴Nacos、Eureka、Consul和Zookeeper四个服务注册发现中心
  + **分布式配置管理**：支持阿里巴巴Nacos、携程Apollo和Redis三个远程配置中心
  + **消息驱动能力**：基于 Spring Cloud Stream 为微服务应用构建消息驱动能力
  + **分布式事务**：支持阿里巴巴Seata，使用 @GlobalTransactional 注解， 高效并且对业务零侵入地解决分布式事务问题
  + **分布式追踪**：支持OpenTracing和OpenTelemetry规范下的调用链中间件，Jaeger、SkyWalking和Zipkin等
  + **分布式数据库**：支持Sharding数据中间件，实现读写分离、分库分表等
  + **分布式缓存**：支持Redis、Memcached中间件实现分布式缓存
  + **分布式锁**：支持Redis、Zookeeper中间件实现分布式锁
    
## 应用功能

  + 支持蓝绿灰度发布、路由过滤、流量权重、限流、熔断、降级、隔离、监控、追踪等企业生产级功能
  + 支持动态域名、双云双活和SET单元化的配置，支持跨云的服务注册和配置读取（例如，阿里云上的微服务想要注册到华为云上
    的Nacos注册中心，或者跨云读取Apollo配置中心的配置，通过DevOps修改相关配置驱动即可）。一套SDK体系可以同时适配和
    运行在不同的云上
  + 支持动态配置上实现同类型的组件的快速切换（例如，Eureka注册中心切换到Consul，Apollo配置中心切换到Nacos
    等），但同类型组件不可并存使用（例如，Eureka和Consul注册中心不可同时并存，Apollo和Nacos配置中心不可同时并存等）
  + 支持框架层面实现每个组件对四个环境（DEV | FAT | UAT | PRO）的内置最佳配置，遵循全局公共配置和局部环境配置相结合的
    方式，遵循“约定大于配置”的策略，业务层面实现微量配置甚至零配置。框架集成人员可以内置定制化的配置，业务开发人员在
    业务层可以把内置的配置覆盖掉，达到扩展性强、灵活使用的目的
  + 支持业务开发人员使用该框架的时候，对其尽最大可能屏蔽一切跟Spring Cloud和中间件有关的代码书写、配置参数、环境地址
    等，零Spring Cloud经验的业务开发人员也可以快速上手研发基于Spring Cloud微服务技术栈的业务服务，最大程度上减轻业务人
    员的压力
  

## 如何构建

使用方式是将本项目 clone 到本地，然后执行以下命令：

	mvn clean install -DskipTests

执行完毕后，项目将被安装到本地 Maven 仓库。

## 如何使用

### 引入依赖

如果需要使用已发布的版本，在 `dependencyManagement` 中添加如下配置。

```
  <properties>
    <zhul.version>1.3.0</zhul.version>
  </properties>
```

	<dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.zhul.cloud</groupId>
                <artifactId>zhul-dependencies</artifactId>
                <version>${zhul.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

然后在 `dependencies` 中添加自己所需使用的依赖即可使用。

### 引入Jar

① 微服务引入
````
<dependency>
    <groupId>com.zhul.cloud</groupId>
    <artifactId>zhul-framework-starter-service</artifactId>
    <version>${zhul.version}</version>
</dependency>
````

② 微应用引入
````
<dependency>
    <groupId>com.zhul.cloud</groupId>
    <artifactId>zhul-framework-starter-api</artifactId>
    <version>${zhul.version}</version>
</dependency>
````

③ Spring Cloud Gateway网关引入
````
<dependency>
    <groupId>com.zhul.cloud</groupId>
    <artifactId>zhul-framework-starter-gateway</artifactId>
    <version>${zhul.version}</version>
</dependency>
````

### 添加注解

下面注解封装了标准Spring Boot、Spring Cloud、服务注册发现等多个注解，可以有效的降低业务使用成本

① 微服务引入
````
@EnableZhulService
public class ZhulApplication {
public static void main(String[] args) {
new SpringApplicationBuilder(ZhulApplication.class).run(args);
  }
}
````

② 微应用引入
````
@EnableZhulApplication
public class ZhulApplication {
public static void main(String[] args) {
new SpringApplicationBuilder(ZhulApplication.class).run(args);
  }
}
````

③ Spring Cloud Gateway网关引入
````
@EnableZhulGateway
public class ZhulApplication {
public static void main(String[] args) {
new SpringApplicationBuilder(ZhulApplication.class).run(args);
  }
}
````

### 添加配置

由于大量配置已经内置到框架里，除了服务名和端口号之外，业务层原则上不需要再添加Spring Cloud和中间件其它配置，当然也可
以覆盖掉默认内置的配置。内置配置文件支持Spring标准占位符用法

*小诀窍：为了避免引起业务层的配置无法覆盖掉默认内置的配置，请在bootstrap.properties定义想要覆盖的配置

### 应用启动

跟原生的Spring Boot应用程序启动方式一致


## 演示 Demo

为了演示如何使用，Zhul项目包含了一个子模块`zhul-examples`。此模块中提供了演示用的 example ，您可以阅读对应的 example 工程下的 readme 文档，根据里面的步骤来体验。

Example 列表：

[Database Example](zhul-examples/zhul-examples-database/README.md)

[Cache Example](zhul-examples/zhul-examples-cache/README.md)

[Config Example](zhul-examples/zhul-examples-config/README.md)

[Log Example](zhul-examples/zhul-examples-log/README.md)

[Transaction Example](zhul-examples/zhul-examples-transaction/README.md)

[Stream Example](zhul-examples/zhul-examples-stream/README.md)