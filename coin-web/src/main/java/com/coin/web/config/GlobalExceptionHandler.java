package com.coin.web.config;

import com.coin.resp.MyResp;
import com.coin.service.constant.CodeCons;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MultipartException.class)
    @ResponseBody
    public MyResp<String> handleBusinessException(MaxUploadSizeExceededException ex) {
        if (ex.getCause().getCause() instanceof SizeLimitExceededException) {
            return new MyResp<>(CodeCons.ERROR, "请求参数数据大小超过限制");
        }
        return null;
    }
}
