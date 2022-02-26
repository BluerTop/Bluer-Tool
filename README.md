<p align="center">
	<img alt="logo" src="https://moment-back.oss-cn-hangzhou.aliyuncs.com/picture/system/Zany%20Face.png" width="150" height="150">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">Bluer-Tool v0.0.1</h1>
<h3 align="center">一个简单好用的 Java 工具库，可以让秃头小伙伴们更专注核心业务！！！</h3>
<h4 align="center">持续更新中....</h4>

## :shipit: 使用

- 修改maven的settings.xml文件（不要慌，我的私服代理了阿里云的maven仓库）
~~~xml
<mirrors>
    <!-- bluer私服 -->
    <mirror>
        <id>bluer-nexus-public</id>
        <mirrorOf>*</mirrorOf>
        <name>bluer Nexus public Repository</name>
        <url>https://bluer.top/nexus/repository/maven-public/</url>
    </mirror>
</mirrors>
~~~
- 在项目的pom文件中引入相关依赖
~~~xml
<dependency>
  <groupId>top.bluer</groupId>
  <artifactId>bluer-fis</artifactId>
  <version>0.0.1</version>
</dependency>
~~~


## :cow: 功能 （持续更新中...）
- [bluer-fis：文件上传/下载](https://gitee.com/BluerTop/bluer-tool/blob/master/bluer-fis/README.md)
- [bluer-result：全局结果处理](https://gitee.com/BluerTop/bluer-tool/blob/master/bluer-result/README.md)


