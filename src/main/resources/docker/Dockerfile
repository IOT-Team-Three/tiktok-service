# 使用Ubuntu作为基础镜像
FROM ubuntu:22.04

# 设置环境变量
ENV DEBIAN_FRONTEND=noninteractive
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$PATH:$JAVA_HOME/bin

# 更换为国内Ubuntu源（阿里云），提高apt-get速度
RUN echo "deb http://mirrors.aliyun.com/ubuntu/ jammy main restricted universe multiverse" > /etc/apt/sources.list && \
    echo "deb http://mirrors.aliyun.com/ubuntu/ jammy-security main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb http://mirrors.aliyun.com/ubuntu/ jammy-updates main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb http://mirrors.aliyun.com/ubuntu/ jammy-proposed main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb http://mirrors.aliyun.com/ubuntu/ jammy-backports main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb-src http://mirrors.aliyun.com/ubuntu/ jammy main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb-src http://mirrors.aliyun.com/ubuntu/ jammy-security main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb-src http://mirrors.aliyun.com/ubuntu/ jammy-updates main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb-src http://mirrors.aliyun.com/ubuntu/ jammy-proposed main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb-src http://mirrors.aliyun.com/ubuntu/ jammy-backports main restricted universe multiverse" >> /etc/apt/sources.list

# 更新系统并安装必要的依赖（使用--no-install-recommends减少安装时间和镜像大小）
RUN apt-get update && apt-get install -y --no-install-recommends \
    openjdk-17-jdk \
    mysql-server \
    redis-server \
    supervisor \
    wget \
    curl \
    unzip \
    git \
    build-essential \
    ffmpeg \
    && rm -rf /var/lib/apt/lists/*

COPY src/main/resources/ollama-linux-amd64.tar /tmp/

# 安装Ollama
RUN mkdir /tmp/ollama \
    && tar -xvf /tmp/ollama-linux-amd64.tar -C /tmp/ollama \
    && cp -r /tmp/ollama /usr/local/bin/ \
    && rm -rf /tmp/ollama

# 创建必要的目录
RUN mkdir -p /opt/app \
    && mkdir -p /tmp/run \
    && mkdir -p /tmp/log/supervisord \
    && mkdir -p /dev/log/supervisord

# 复制Spring Boot应用jar包
COPY common/target/common-0.0.1-SNAPSHOT.jar /opt/app/app.jar

# 复制supervisord配置文件
COPY src/main/resources/docker/supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# 复制数据库初始化脚本
COPY other/tiktok-database.sql /tmp/tiktok-database.sql

# 配置MySQL
RUN service mysql start && \
    mysql -u root -p -h localhost < /tmp/tiktok-database.sql

# 配置Redis（可选，根据需要调整）
RUN sed -i 's/bind 127.0.0.1/bind 0.0.0.0/' /etc/redis/redis.conf

# 暴露端口
EXPOSE 8080 3306 6379 11434

# 启动supervisord
CMD ["/usr/bin/supervisord", "-c", "/etc/supervisor/conf.d/supervisord.conf"]