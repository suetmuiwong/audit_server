FROM docker.gf.com.cn/gf-jdk8
 
# Set the timezone.
RUN echo "Asia/Shanghai" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata
RUN locale-gen zh_CN.UTF-8 &&\
 DEBIAN_FRONTEND=noninteractive dpkg-reconfigure locales
RUN locale-gen zh_CN.UTF-8
ENV LANG zh_CN.UTF-8
ENV LANGUAGE zh_CN:zh
ENV LC_ALL zh_CN.UTF-8
ENV JAVA_OPTS "-Xms4096m -Xmx4096m -XX:PermSize=64m -XX:MaxPermSize=512m"

# Make logs dir
RUN mkdir -p /opt/IbsAudit/logs

#expose 
EXPOSE 22
EXPOSE 8080

# Add the jar and run
ADD IbsAudit.jar /opt/IbsAudit/IbsAudit.jar
WORKDIR /opt/IbsAudit

ENV mysqlUsername=""
ENV mysqlPassword=""
ENV mysqlUrl=""
ENV serverPort=""
ENV serverName=""
# ENV redisDatabase=""
ENV redisPassword=""
ENV redisTimeout=""
# ENV redisSentinelMaster=""
# ENV redisSentinelNodes=""
ENV redisClusterNodes=""
ENV redisClusterMaxRedirects=""

# 
#ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -Xbootclasspath/a: -jar IbsAudit.jar  --spring.datasource.druid.first.username=${mysqlUsername} --spring.datasource.druid.first.url=${mysqlUrl} --spring.datasource.druid.first.password=${mysqlPassword} --server.port=${serverPort} --server.servlet.context-path=${serverName}
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS IbsAudit.jar --spring.datasource.druid.first.username=$mysqlUsername --spring.datasource.druid.first.url=$mysqlUrl --spring.datasource.druid.first.password=$mysqlPassword --server.port=$serverPort --server.servlet.context-path=$serverName --spring.redis.password=$redisPassword --spring.redis.timeout=$redisTimeout --spring.redis.cluster.nodes=$redisClusterNodes --spring.redis.cluster.max-redirects=$redisClusterMaxRedirects"]
