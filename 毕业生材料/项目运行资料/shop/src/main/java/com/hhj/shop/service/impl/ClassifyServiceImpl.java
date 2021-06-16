package com.hhj.shop.service.impl;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.ClassifyRepository;
import com.hhj.shop.repository.GoodsRepository;
import com.hhj.shop.service.ClassifyService;
import com.hhj.shop.util.DateUtil;
import com.hhj.shop.util.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    private ClassifyRepository classifyRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    protected static Logger log = LoggerFactory.getLogger(ClassifyServiceImpl.class);


    @Override
    public ResultVO<ClassifyDTO> delete(Integer id) {
        ClassifyDTO classifyDTO = new ClassifyDTO();
        classifyDTO.setId(id);
        int result = classifyRepository.delete(id);
        if (result > 0)
            return new ResultVO<>(ResultCode.SUCCESS, classifyDTO);
        else
            return new ResultVO<>(ResultCode.ERROR, null);
    }

    @Override
    public ResultVO<String> insert(Classify classify) {
        int count = classifyRepository.findByNameWithCount(classify.getClsName());
        System.out.println(count);
        if (count == 0) {
            Long startTs = System.currentTimeMillis() / 1000; // 当前时间戳
            classify.setCreatetime(String.valueOf(startTs));
            int result = classifyRepository.insert(classify);
            if (result > 0)
                return new ResultVO<>(ResultCode.SUCCESS, null);
            else
                return new ResultVO<>(ResultCode.ERROR, null);
        } else
            return new ResultVO<>(ResultCode.CLASSIFY_FAIL, null);
    }

    @Override
    public ResultVO<String> update(Classify classify) {
        Long startTs = System.currentTimeMillis() / 1000; // 当前时间戳
        classify.setUpdatetime(String.valueOf(startTs));
        int result = classifyRepository.update(classify);
//        List<Goods> goodsIdList=goodsRepository.findGoodsWithClassify(classify.getId());
//        for (Goods goods:goodsIdList ) {
//            if (classify.getType()==1)
//                goods.setType(1);
//            else goods.setType(0);
//        }
//        log.info(goodsIdList.toString());
//        goodsRepository.batchUpdate(goodsIdList);
        if (result > 0)
            return new ResultVO<>(ResultCode.SUCCESS, null);
        else
            return new ResultVO<>(ResultCode.ERROR, null);
    }

    @Override
    public ResultVO<Classify> findById(Integer id) {
        Classify classify = classifyRepository.findById(id);
        if (classify != null) {
            return new ResultVO<>(ResultCode.SUCCESS, classify);
        } else
            return new ResultVO<>(ResultCode.CLASSIFY_FAIL, null);
    }

    @Override
    public ResultDTO findPageWithResult(PageDTO pageDTO) {
        String keywords = pageDTO.getKeywords();
        String startTime = pageDTO.getStartTime();
        String endTime = pageDTO.getEndTime();
        if (startTime != null && startTime != "" && endTime != null && endTime != "") {
            pageDTO.setStartTime(DateUtil.date2TimeStamp(startTime, "yyyy-MM-dd"));
            endTime = endTime + " " + "23:59:59";
            System.out.println(endTime);
            pageDTO.setEndTime(DateUtil.date2TimeStamp(endTime, "yyyy-MM-dd HH:mm:ss"));
            System.out.println(pageDTO.getEndTime());
        }
        if (keywords != null && keywords != "") {
            pageDTO.setKeywords('%' + keywords + '%');
        }
        System.out.println(keywords);

        //共多少个类型
        int totalCount = classifyRepository.findPageWithCount(pageDTO);
        //计算共多少页
        int pageSize;
        log.info(pageDTO.getStatus());
        if (pageDTO.getStatus() == null && pageDTO.getStatus() == "") {
            pageSize = pageDTO.getLimit();
        } else {
            pageSize = totalCount;
            pageDTO.setLimit(totalCount);
        }
        pageDTO.setPage((pageDTO.getPage() - 1) * pageSize);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        System.out.println(pageDTO);
        List<Classify> classifyList = classifyRepository.findPageWithResult(pageDTO);
        for (Classify classify : classifyList) {
            String createDate = DateUtil.timeStamp2Date(classify.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
            String updateDate = DateUtil.timeStamp2Date(classify.getUpdatetime(), "yyyy-MM-dd HH:mm:ss");
            classify.setCreatetime(createDate);
            classify.setUpdatetime(updateDate);
        }
        ResultDTO resultDTO = new ResultDTO();
        //resultDTO.setData(classifyList);
        //ResultPageVO<List<resultDTO>> listResultPageVO=new ResultPageVO<>(resultDTO);
        System.out.println(resultDTO);
        if (classifyList.size() != 0) {
            resultDTO.setCode(0);
            log.info("总数：" + totalCount);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("请求成功");
            resultDTO.setData(classifyList);
            return resultDTO;
        } else {
            resultDTO.setCode(5000);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("未知错误");
            return resultDTO;
        }
    }


    @Override
    public ResultVO<String> batchUpdate(BatchDTO batchDTO) {
        List<Classify> classifyList = new ArrayList<>();
        Long startTs = System.currentTimeMillis() / 1000; // 当前时间戳
        for (Integer i : batchDTO.getIds()) {
            Classify classify = new Classify();
            classify.setId(i);
            classify.setUpdatetime(String.valueOf(startTs));
            classify.setType(batchDTO.getType());
            classifyList.add(classify);
            //修改商品状态
            Goods goods = new Goods();
            goods.setClsId(classify.getId());
            List<Goods> goodsIdList = goodsRepository.findGoodsWithClassify(goods);
            BatchDTO batchDTO1 = new BatchDTO();
            if (goodsIdList.size() != 0) {
                for (Goods goods1 : goodsIdList) {
                    if (classify.getType() == 1)
                        goods1.setType(1);
                    else goods1.setType(0);
                }
                log.info(goodsIdList.toString());
                goodsRepository.batchUpdate(goodsIdList);
            }
        }
        classifyRepository.batchUpdate(classifyList);
        return new ResultVO<>(ResultCode.SUCCESS, null);
    }

    /*
     * 文件导入（批量导入）
     * */
    @Override
    public String ajaxUploadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        /*MultipartFile file = multipartRequest.getFile("file");*/
        List<Classify> classifyList = new ArrayList<>();
        //   System.out.println("得到数据文件");
        if (file.isEmpty()) {
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //   System.out.println("加载流");
        List<List<Object>> listob = null;
        try {
            //   System.out.println("加载流");
            listob = new ExcelUtils().getBankListByExcel(in, file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            //    System.out.println("listob,zize是："+listob.size());
            List<Object> lo = listob.get(i);
            //    System.out.println("遍历" + listob.get(i));
            Classify vo = new Classify();
            //     Student j = null;

            //     System.out.println("lo.size是："+lo.size());

            if (lo.size() == 3) {    //无ID这一列
                //     System.out.println("无ID");
                vo.setClsName(String.valueOf(lo.get(0)));
                vo.setSynopsis(String.valueOf(lo.get(1)));
                if (String.valueOf(lo.get(2)).equals("是"))
                    vo.setType(1);
                else
                    vo.setType(0);
            } else if (lo.size() == 4) {   //有ID这一列
                //      System.out.println("有ID");
                vo.setClsName(String.valueOf(lo.get(1)));
                vo.setSynopsis(String.valueOf(lo.get(2)));
                if (String.valueOf(lo.get(3)).equals("是"))
                    vo.setType(1);
                else
                    vo.setType(0);
            }
            Long startTs = System.currentTimeMillis() / 1000; // 当前时间戳
            vo.setCreatetime(String.valueOf(startTs));

            System.out.println("vo是: " + vo.toString());
            classifyList.add(vo);
            //studentMapper.addStudent(vo);

        }
        classifyRepository.batchInsert(classifyList);
        return "success";
    }

    @Override
    public ResultVO<List<ClassifyAllDTO>> findAllClassify() {
        List<Classify> classifyList = classifyRepository.findAllClassify();
        List<ClassifyAllDTO> classifyAllDTOList = new ArrayList<>();
        for (Classify classify : classifyList) {
            ClassifyAllDTO classifyAllDTO = new ClassifyAllDTO();
            classifyAllDTO.setId(classify.getId());
            classifyAllDTO.setClsName(classify.getClsName());
            classifyAllDTOList.add(classifyAllDTO);
        }
        return new ResultVO<>(ResultCode.SUCCESS, classifyAllDTOList);
    }


}
