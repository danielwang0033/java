package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文件上传下载请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class FileReq extends CommonReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;

    private String videoPath;
}

