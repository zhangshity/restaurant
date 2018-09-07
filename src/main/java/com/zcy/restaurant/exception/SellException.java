package com.zcy.restaurant.exception;

import com.zcy.restaurant.enums.ExceptionEnum;

/**
 * @ Author: chunyang.zhang
 * @ Description:  自定义的项目 异常 （返回用枚举列出，并打印输出）
 * @ Date: Created
 * @ Modified: By:
 */
public class SellException extends RuntimeException {

    private Integer code;

    //private String Message;

    public SellException(ExceptionEnum exceptionEnum) { //传入结果不存在 枚举

        //this.Message = exceptionEnum.getMessage();
        super(exceptionEnum.getMessage());//返回错误消息

        this.code = exceptionEnum.getCode();//返回错误码
    }


    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
