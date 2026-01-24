# tiktok-service

## 项目介绍
考核项目 仿照抖音 的后端服务，基于Spring Boot框架开发，提供用户管理、视频管理、点赞、评论等核心功能。

## 技术栈

### 核心框架
- Spring Boot 3.5.10
- Java 25

### 数据库
- MySQL：关系型数据库，存储用户、视频等核心数据
- Redis：缓存中间件，用于提高系统性能

### 持久层
- MyBatis：ORM框架，简化数据库操作

### 安全认证
- Spring Security：安全框架，提供认证和授权功能
- JWT：JSON Web Token，用于无状态认证
- Pac4j CAS：单点登录集成

### 工具库
- Hutool：Java工具库，提供丰富的工具方法
- Lombok：代码生成工具，减少样板代码

### API文档
- Swagger：自动生成API文档

## 项目结构

```
tiktok-service/
├── common/          # 通用模块
├── config/          # 配置模块
├── controller/      # 控制器层
├── service/         # 服务层
├── mapper/          # 数据访问层
├── entity/          # 实体类
├── dto/             # 数据传输对象
└── pom.xml          # Maven依赖管理
```

## 快速开始

### 环境要求
- JDK 25+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

### 配置说明

1. 配置数据库连接
   修改 `config/src/main/resources/application-dev.yml` 文件中的数据库配置：
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/tiktok?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
       username: your_username
       password: your_password
   ```

2. 配置Redis连接
   修改 `config/src/main/resources/application-dev.yml` 文件中的Redis配置：
   ```yaml
   spring:
     data:
       redis:
         host: localhost
         port: 6379
         password: your_redis_password
   ```

3. 配置JWT密钥
   修改 `config/src/main/resources/application-dev.yml` 文件中的JWT配置：
   ```yaml
   jwt:
     secret: your_jwt_secret_key
     expiration: 3600000  # 过期时间，单位毫秒
   ```

### 启动项目

使用Maven命令启动项目：

```bash
mvn clean package
cd target
java -jar tiktok-service-0.0.1-SNAPSHOT.jar
```

项目将在 `http://localhost:8080/api` 启动。

## API文档

项目集成了Swagger，可通过以下地址访问API文档：

```
http://localhost:8080/api/swagger-ui.html
```

## 功能模块

### 用户管理
- 用户注册
- 用户登录
- 用户信息更新
- 用户关注/取消关注

### 视频管理
- 视频上传
- 视频列表获取
- 视频详情获取
- 视频删除

### 互动功能
- 点赞/取消点赞
- 评论
- 视频分享

## 配置文件

### 主要配置文件
- `application.yml`：主配置文件，包含通用配置
- `application-dev.yml`：开发环境配置

## 日志配置

日志级别配置在 `application-dev.yml` 文件中：

```yaml
logging:
  level:
    com.sicau: DEBUG
    org.springframework.jdbc: DEBUG
```

## 贡献指南

1. Fork 项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 许可证

本项目仅供学习和考核使用。

## 联系方式

如有问题，请联系项目负责人。
```
        