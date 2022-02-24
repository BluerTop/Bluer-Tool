# Bluer工具库

## :monkey: 介绍
一个好用的 Java 工具库，建议下载源码 编译 打包 部署到自己的maven私服

## :shipit: 使用

- 修改maven的settings.xml文件
~~~xml
<mirrors>
  <!-- 私服 -->
    <mirror>
        <id>bluer-nexus-public</id>
        <mirrorOf>*</mirrorOf>
        <name>bluer Nexus releases Repository</name>
        <url>http://bluer.top:8001/repository/maven-public/</url>
    </mirror>
    <!-- 阿里云 -->
    <mirror>
        <id>aliyun</id>
        <mirrorOf>*</mirrorOf>
        <name>阿里云公共仓库</name>
        <url>https://maven.aliyun.com/repository/public</url>
    </mirror>
</mirrors>
~~~
- 在项目的pom文件中引入依赖
~~~xml
<dependency>
  <groupId>top.bluer</groupId>
  <artifactId>bluer-fis</artifactId>
  <version>0.0.1-RELEASE</version>
</dependency>
~~~


## :cow: 功能 （持续更新中...）
- [bluer-fis：文件上传/下载](https://gitee.com/BluerTop/bluer-tool/blob/master/bluer-fis/README.md)

