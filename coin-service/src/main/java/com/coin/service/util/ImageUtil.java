package com.coin.service.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUtil {

    private static String env;

    private static String bucket;

    @Value("${spring.profiles.active}")
    public void setEnv(String env) {
        ImageUtil.env = env;
    }

    @Value("${io.bucket}")
    public void setBucket(String bucket) {
        ImageUtil.bucket = bucket;
    }

    public static String completeImageUrl(String shortImageUrl) {
        if (StrUtil.isNotBlank(shortImageUrl) && !shortImageUrl.startsWith("http") && !shortImageUrl.startsWith("//")) {
            String c1 = "/";
            if (shortImageUrl.startsWith("/")) {
                c1 = "";
            }
            if (env.endsWith("dev")) {
                shortImageUrl = "https://minio.bggsdght.xyz/" + bucket + c1 + shortImageUrl;
            } else if (env.endsWith("fat")) {
                shortImageUrl = "https://minio.bggsdght.xyz/" + bucket + c1 + shortImageUrl;
            } else if (env.endsWith("online")) {
                shortImageUrl = "https://minio.59994.cn/" + bucket + c1 + shortImageUrl;
            }
        }
        return shortImageUrl;
    }
}
