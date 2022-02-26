# Bluer-Fis

## 说明

- 本地文件上传、删除
- 阿里云 oss 文件上传、删除
- Minio oss 文件上传、删除
- 七牛云 oss 文件上传、删除

## Maven 依赖

~~~xml
<dependency>
    <groupId>top.bluer</groupId>
    <artifactId>bluer-fis</artifactId>
    <version>0.0.1</version>
</dependency>
~~~

## 使用

- 本地文件

~~~yaml
# fis 配置
fis:
  # 是否开启 fis
  deploy: true
  # local（本地）、ali-oss（阿里云oss）、minio-oss（Minio oss）、qin-oss（七牛云 oss）
  type: local
  # 上传路径（本地+阿里云+Minio适用+七牛云）
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

- 代码示例
~~~java
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.bluer.files.FisTemplate;

import javax.annotation.Resource;

/**
 * @program: Bluer-Tool
 * @description: fis测试
 * @author: bluer
 * @date: 2022-02-18 13:43
 * codes: 扁鹊
 **/
@RestController
@RequestMapping("/fis")
public class FisController {

    @Resource
    private FisTemplate fisTemplate;

    /**
     * @description: 上传文件
     * @date: 2022/2/24 12:57
     * @codes: 扁鹊
     * 参数：file 文件
     * 返回值：String 文件访问路径
     **/
    @PostMapping("/putFile")
    public String putFile(MultipartFile file) {
        return fisTemplate.putFile(file);
    }

    /**
     * @description: 删除文件
     * @date: 2022/2/24 12:56
     * @codes: 扁鹊
     * 参数：path 文件路径
     * 返回值：boolean 删除结果
     **/
    @DeleteMapping("/delFile")
    public boolean delFile(@RequestParam("path") String path) {
        return fisTemplate.delFile(path);
    }
}
~~~

## 关闭

~~~yaml
# fis 配置
fis:
  # 是否开启 fis
  deploy: false
~~~
