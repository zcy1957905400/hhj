package com.hhj.shop.service;

import com.hhj.shop.global.ResultVO;
import org.springframework.web.multipart.MultipartFile;

public interface uploadZipService {

    ResultVO<String> uploadImgZip(MultipartFile zipFile);
}
