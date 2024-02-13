package com.coin.service.impl;

import com.coin.service.FileService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.BizUtil;
import com.coin.service.util.DateUtil;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Resource
    private MinioClient minioClient;
    @Value("${io.bucket}")
    private String bucket;

    @Override
    public String upload(InputStream inputStream, String fileName, long fileSize, String contentType) {
        try {
            int index = fileName.indexOf(".");
            String suffix = fileName.substring(index);
            // 示例: 2023/10/20231025151849539OCv.jpeg
            String newFileName = DateUtil.getTodayStr(DateUtil.year_format)
                    + "/" + DateUtil.getTodayStr(DateUtil.month_format)
                    + "/" + DateUtil.getTodayStr(DateUtil.ms_dt_format) + BizUtil.getStringRandom(3, 0) + suffix;
            logger.info("file-upload-fileName={}, newFileName={}", fileName, newFileName);
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(newFileName)
                    .stream(inputStream, fileSize, -1)
                    .contentType(contentType)
                    .build());
            return newFileName;
        } catch (Exception e) {
            logger.error("file-upload-error", e);
            throw new BizException(CodeCons.ERROR, "上传失败");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
}
