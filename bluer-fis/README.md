# Bluer-Fis

## 说明

- 本地文件上传、删除
- 阿里云 oss 文件上传、删除
- Minio oss 文件上传、删除

## Maven 依赖

~~~xml
<dependency>
    <groupId>top.bluer</groupId>
    <artifactId>bluer-fis</artifactId>
    <version>x.x.x</version>
</dependency>
~~~

## 使用

- 本地文件

~~~yaml
# fis 配置
fis:
  # 是否开启 fis
  deploy: true
  # local（本地）、ali-oss（阿里云oss）
  type: local
  # 上传路径（本地+阿里云适用）
  path: file/images/
~~~

- 阿里云 oss
~~~yaml
# fis 配置
fis:
  # 是否开启 fis
  deploy: true
  # local（本地）、ali-oss（阿里云oss）、minio-oss（Minio oss）、qin-oss（七牛云 oss）
  type: ali-oss
  # 上传路径（本地+阿里云+Minio适用+七牛云）
  path: file/images/
  # 阿里云oss配置
  oss:
    - 你的AK                           # ak
    - 你的SK                           # sk
    - oss-cn-hangzhou.aliyuncs.com    # endpoint
    - 你的bucket名称                   # bucketName
~~~

- Minio oss
~~~yaml
# fis 配置
fis:
  # 是否开启 fis
  deploy: true
  # local（本地）、ali-oss（阿里云oss）、minio-oss（Minio oss）、qin-oss（七牛云 oss）
  type: minio-oss
  # 上传路径（本地+阿里云+Minio适用+七牛云）
  path: file/images/
  # Minio oss配置
  oss:
    - http://ip:port/       # url
    - minioadmin            # ak
    - minioadmin            # sk
    - 你的bucket名称         # bucketName
~~~

- 七牛云 oss
~~~yaml
# fis 配置
fis:
  # 是否开启 fis
  deploy: true
  # local（本地）、ali-oss（阿里云oss）、minio-oss（Minio oss）、qin-oss（七牛云 oss）
  type: qin-oss
  # 上传路径（本地+阿里云+Minio适用+七牛云）
  path: file/images/
  # Minio oss配置
  oss:
    - http://xxx.clouddn.com/        # 外链link
    - 你的AK                         # ak
    - 你的SK                         # sk
    - 你的bucket名称                  # bucketName
~~~

## 关闭

~~~yaml
# fis 配置
fis:
  # 是否开启 fis
  deploy: false
~~~
