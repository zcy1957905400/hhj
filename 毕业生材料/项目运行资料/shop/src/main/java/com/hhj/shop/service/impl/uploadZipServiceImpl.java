package com.hhj.shop.service.impl;

import com.hhj.shop.dto.ClassifyAllDTO;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.ClassifyRepository;
import com.hhj.shop.repository.GoodsRepository;
import com.hhj.shop.service.ClassifyService;
import com.hhj.shop.service.uploadZipService;
import com.hhj.shop.util.Constants;
import com.hhj.shop.util.ExcelUtils;
import com.hhj.shop.util.FileUtils;
import com.hhj.shop.util.ReadFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class uploadZipServiceImpl implements uploadZipService {

    private String imgStorePath=ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/admin/upload";

    protected static Logger log = LoggerFactory.getLogger(uploadZipServiceImpl.class);

    @Autowired
    private ClassifyRepository classifyRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public ResultVO<String> uploadImgZip(MultipartFile zipFile) {
        // 1. 参数校验
        if (Objects.isNull(zipFile)) {
            return new ResultVO<>(ResultCode.ERROR,null);
        }
        String fileContentType = zipFile.getContentType();

        if (!Constants.CONTENT_TYPE_ZIP.equals(fileContentType)
                && !Constants.CONTENT_TYPE_ZIP_COMPRESSED.equals(fileContentType)
                ) {
            return new ResultVO<>(ResultCode.ERROR,null);
        }

        // 2. 保存
        //将压缩包保存在指定路径
        String filePath = imgStorePath + File.separator + zipFile.getName();
        //保存到服务器
        boolean saveFile = FileUtils.saveFile(zipFile, filePath);
        if (!saveFile) {
            return new ResultVO<>(ResultCode.ERROR,null);
        }
        String destPath = imgStorePath + File.separator;
        // 4. 解压,并删除zip包
        boolean unPackZip = FileUtils.unPackZip(new File(filePath), "", destPath, true);
        log.info(zipFile.getOriginalFilename());
        String openFileName=FileUtils.getExtensionName(zipFile.getOriginalFilename());
        log.info(openFileName);
        if (unPackZip) {
            try {
                String readFile=ReadFile.readFile(imgStorePath+"//"+openFileName);
                log.info(readFile);
                InputStream myInputStream=null;
                File myfile=new File(readFile);
                log.info(myfile.getName());
                try {
                    myInputStream = new FileInputStream(new File(readFile));
                    //   System.out.println("加载流");
                    List<List<Object>> listob = null;
                    try {
                        //   System.out.println("加载流");
                        listob = new ExcelUtils().getBankListByExcel(myInputStream,myfile.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    List<Goods> goodsList=new ArrayList<>();
                    List<Classify> classifyList=new ArrayList<>();
                    classifyList=classifyRepository.findAllClassify();
                    //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
                    for (int i = 0; i < listob.size(); i++) {
                        //    System.out.println("listob,zize是："+listob.size());
                        List<Object> lo = listob.get(i);
                        //    System.out.println("遍历" + listob.get(i));
                        Goods vo = new Goods();
                        vo.setGoodName(String.valueOf(lo.get(0)));
                        vo.setSpec(String.valueOf(lo.get(1)));
                        for (Classify classify:classifyList) {
                            if (classify.getClsName().equals(String.valueOf(lo.get(2)))){
                                vo.setClsId(classify.getId());
                            }
                        }
                        log.info("id:"+vo.getClsId());
                        vo.setPrice(BigDecimal.valueOf(Long.valueOf(String.valueOf(lo.get(3)))));
                        vo.setInventory(Integer.valueOf(String.valueOf(lo.get(4))));
                        vo.setPhotos("static/admin/upload/"+openFileName+"/"+String.valueOf(lo.get(5)));
                        if (String.valueOf(lo.get(6)).equals("是"))
                            vo.setCarousel(1);
                        else
                            vo.setCarousel(0);
                        if (String.valueOf(lo.get(7)).equals("是"))
                            vo.setType(1);
                        else
                            vo.setType(0);
                        vo.setParticulars("<img src='static/admin/upload/images/"+lo.get(8)+"' alt='undefined'>");

                        Long startTs = System.currentTimeMillis()/1000; // 当前时间戳
                        vo.setCreatetime(String.valueOf(startTs));
                        vo.setSales(0);
                        log.info("vo是: " + vo.toString());
                        goodsList.add(vo);
                        //studentMapper.addStudent(vo);
                    }
                    goodsRepository.batchInsert(goodsList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new ResultVO<>(ResultCode.SUCCESS,null);

        } else {
            return new ResultVO<>(ResultCode.ERROR,null);
        }

    }



    /**
     * @Title: 图片路径
     * @MethodName: getUserImageUrl
     * @param originalFilename
     * @Return java.lang.String
     * @Exception
     * @Description:
     *
     * @author: 王延飞
     */
//    private String readUserImageUrl(String originalFilename) {
//        // http://localhost:8989/image/11
//        StringBuilder imageUrl = new StringBuilder("http://");
//        String hostAddress = null;
//        try {
//            hostAddress = Inet4Address.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            //log.info("【图片路径】获取失败:{}", e);
//            return null;
//        }
//        String imageUrlPath = imageUrl.append(hostAddress)
//                .append(":").append("8089")
//                .append("/image/").append(originalFilename)
//                .toString().trim();
//        return imageUrlPath;
//
//    }
}
