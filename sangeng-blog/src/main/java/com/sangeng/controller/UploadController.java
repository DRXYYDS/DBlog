package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.UploadService;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 曼迪卡尔多
 * @ClassName UploadController
 * @description: TODO
 * @date 2023年07月29日
 * @version: 1.0
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
