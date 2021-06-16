package com.hhj.shop.global;

import com.hhj.shop.global.APIException;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@ControllerAdvice
//如果返回的为json数据或其它对象，添加该注解
@ResponseBody
public class ExceptionControllerAdvice{

//    public String MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
//        // 从异常对象中拿到ObjectError对象
//        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
//        // 然后提取错误提示信息进行返回
//        return objectError.getDefaultMessage();
//    }
//
//    //自定义的全局异常
//    @ExceptionHandler(APIException.class)
//    public String APIExceptionHandler(APIException e) {
//        return e.getMsg();
//    }

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        // 注意哦，这里传递的响应码枚举
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 注意哦，这里传递的响应码枚举
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }


}
