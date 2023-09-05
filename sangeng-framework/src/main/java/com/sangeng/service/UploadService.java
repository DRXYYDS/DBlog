package com.sangeng.service;

import com.sangeng.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 曼迪卡尔多
 * @ClassName UploadService
 * @description: TODO
 * @date 2023年07月29日
 * @version: 1.0
 */
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
