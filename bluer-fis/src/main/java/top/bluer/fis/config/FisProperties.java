package top.bluer.fis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @program: Bluer-Tool
 * @description: 上传文件 本地 属性类
 * @author: bluer
 * @date: 2022-02-18 12:33
 * codes: 扁鹊
 **/
@Data
@ConfigurationProperties(prefix = "fis")
public class FisProperties implements Serializable {
    /**
     * 是否开启配置
     */
    private boolean deploy;
    /**
     * 上传类型：local（本地）| ali-oss （阿里云）| minio-oss（Minio）| qin-oss（七牛云）
     */
    private String type;
    /**
     * 上传路径（本地+阿里云+Minio+七牛云）
     */
    private String path;
    /**
     * 阿里云 oss 配置
     * - ak
     * - sk
     * - endpoint
     * - bucketName
     *
     * Minio oss 配置
     * - url
     * - ak
     * - sk
     * - bucketName
     *
     * 七牛云 oss 配置
     * - 外链link
     * - ak
     * - sk
     * - bucketName
     */
    private ArrayList<String> oss;
}
