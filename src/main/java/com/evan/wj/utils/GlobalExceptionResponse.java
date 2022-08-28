package com.evan.wj.utils;

import com.alibaba.fastjson.JSON;
import com.evan.wj.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionResponse {

    @ExceptionHandler(value = MyException.class)
    public Result errorHandler(MyException e) {
        System.out.print(JSON.toJSONString(e));
        return new Result(e.getCode(), e.getMsg());
    }

}
