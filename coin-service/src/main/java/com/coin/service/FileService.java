package com.coin.service;

import java.io.InputStream;

public interface FileService {

    String upload(InputStream inputStream, String fileName, long fileSize, String contentType);
}
