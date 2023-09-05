package com.sangeng.handler.exception;

import com.sangeng.domain.ResponseResult;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 曼迪卡尔多
 * @ClassName GlobalExceptionHandler
 * @description: TODO
 * @date 2023年07月28日
 * @version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息
        log.error("出现异常!{}",e);
        //从异常对象中获取提示信息 封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());


    }
    @ExceptionHandler(Exception.class)
    public ResponseResult ExceptionHandler(Exception e){
        //打印异常信息
        log.error("出现异常!{}",e);
        //从异常对象中获取提示信息 封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,e.getMessage());


    }
}
