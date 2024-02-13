package com.coin.web.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.req.FileReq;
import com.coin.resp.MyResp;
import com.coin.resp.file.UploadResp;
import com.coin.service.FileService;
import com.coin.service.constant.CodeCons;
import com.coin.service.util.ImageUtil;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileService fileService;
    @Resource
    private MinioClient minioClient;
    @Value("${io.bucket}")
    private String bucket;

    @RequestMapping("/download")
    public void download(FileReq req, HttpServletResponse response) {
        if (ObjectUtil.isNull(req) || StrUtil.isBlank(req.getFileName())) {
            logger.error("download-file:null-err");
            return;
        }
        InputStream imageStream = null;
        OutputStream toClient = null;
        try {
            GetObjectArgs args = GetObjectArgs.builder().bucket(bucket).object(req.getFileName()).build();
            StatObjectArgs stat = StatObjectArgs.builder().bucket(bucket).object(req.getFileName()).build();
            long objectLength = minioClient.statObject(stat).size();
            response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(objectLength));
            String fileName = req.getFileName();
            response.setCharacterEncoding("UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            if (fileName.contains(".xlsx")) {
                response.setContentType("application/vdn.ms-excel");
            }
            imageStream = minioClient.getObject(args);
            byte[] buffer = new byte[4096];
            int bytesRead;
            toClient = new BufferedOutputStream(response.getOutputStream());
            while ((bytesRead = imageStream.read(buffer)) != -1) {
                toClient.write(buffer, 0, bytesRead);
            }
            imageStream.close();
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            logger.error("download-file-error:{},{}", req.getFileName(), e.getMessage());
        } finally {
            if (toClient != null) {
                try {
                    toClient.flush();
                    toClient.close();
                } catch (IOException ignored) {
                }
            }
            if (imageStream != null) {
                try {
                    imageStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    @RequestMapping("/playVideo")
    public void playVideo(FileReq req, HttpServletRequest request, HttpServletResponse response) {
        OutputStream toClient = null;
        try {
            GetObjectArgs args = GetObjectArgs.builder().bucket(bucket).object(req.getFileName()).build();
            StatObjectArgs stat = StatObjectArgs.builder().bucket(bucket).object(req.getFileName()).build();
            long objectLength = minioClient.statObject(stat).size();
            response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(objectLength));
            response.setCharacterEncoding("UTF-8");
            response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
            response.setContentType("video/mp4");
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            long pos = 0, last = objectLength - 1, sum = 0;
            String range = request.getHeader("Range");
            if (null != range) {
                try {
                    String numRang = range.replaceAll("bytes=", "");
                    String[] strRange = numRang.split("-");
                    if (strRange.length == 2) {
                        pos = Long.parseLong(strRange[0].trim());
                        last = Long.parseLong(strRange[1].trim());
                    } else {
                        pos = Long.parseLong(numRang.replaceAll("-", "").trim());
                    }
                } catch (NumberFormatException e) {
                    pos = 0;
                }
            }
            logger.info("File-range=" + range + ", pos,last=[" + pos + "," + last);
            long rangLength = last - pos + 1;
            response.setHeader("Content-Range", "bytes " + pos + "-" + last + "/" + objectLength);
            InputStream videoStream = minioClient.getObject(args);
            long skip = videoStream.skip(pos);
            if (skip > 0) {
                logger.info("bytes skipped:{}", skip);
            }
            toClient = new BufferedOutputStream(response.getOutputStream());
            byte[] b = new byte[4096];
            int length;
            while (sum < rangLength) {
                length = videoStream.read(b, 0, ((rangLength - sum) <= b.length ? ((int) (rangLength - sum)) : b.length));
                sum = sum + length;
                toClient.write(b, 0, length);
            }
            videoStream.close();
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            logger.error("download-file:{}, err:{}", req.getFileName(), e.getMessage());
        } finally {
            if (toClient != null) {
                try {
                    toClient.flush();
                    toClient.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public MyResp<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = fileService.upload(file.getInputStream(), file.getOriginalFilename(), file.getSize(), file.getContentType());
        return new MyResp<>(CodeCons.SUCCESS, "上传成功", fileName);
    }

    /**
     * 上传图片
     */
    @RequestMapping("/uploadImage")
    public MyResp<Map<String, UploadResp>> uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = fileService.upload(file.getInputStream(), file.getOriginalFilename(), file.getSize(), file.getContentType());
        Map<String, UploadResp> result = new HashMap<>();
        UploadResp uploadResp = new UploadResp();
        uploadResp.setPath(fileName);
        uploadResp.setUrl(ImageUtil.completeImageUrl(fileName));
        result.put("image", uploadResp);
        return new MyResp<>(CodeCons.SUCCESS, "上传图片成功", result);
    }

    /**
     * 上传视频
     */
    @RequestMapping("/uploadVideo")
    public MyResp<Map<String, UploadResp>> uploadVideo(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = fileService.upload(file.getInputStream(), file.getOriginalFilename(), file.getSize(), file.getContentType());
        Map<String, UploadResp> result = new HashMap<>();
        UploadResp uploadResp = new UploadResp();
        uploadResp.setPath(fileName);
        uploadResp.setUrl(ImageUtil.completeImageUrl(fileName));
        result.put("video", uploadResp);
        return new MyResp<>(CodeCons.SUCCESS, "上传视频成功", result);
    }
}