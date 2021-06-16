package com.hhj.shop.controller;

import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.GoodsService;
import com.hhj.shop.service.uploadZipService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "导入压缩包")
@RestController
public class uploadZipController {


    @Autowired
    private uploadZipService uploadZipService;

    @PostMapping("/zip")
    public ResultVO<String> uploadImgZip(@RequestParam("file") MultipartFile zipFile) throws IOException {
        return uploadZipService.uploadImgZip(zipFile);

    }
}
