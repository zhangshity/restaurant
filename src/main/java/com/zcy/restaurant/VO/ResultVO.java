package com.zcy.restaurant.VO;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @ Author: chunyang.zhang
 * @ Description:   http请求返回的 最外层对象
 * @ Date: Created in 11:34 2018/5/18
 * @ Modified: By:
 */
@Data                              //忘了加注解，无getset方法。导致无法访问返回VO对象，500错误

//@JsonInclude(JsonInclude.Include.NON_NULL) //不往前端返回值为 null 字段,方便前端处理
public class ResultVO<T> {

    //错误码
    private Integer code;

    //提示信息
    private String msg;


    //具体返回内容
    //(返回的是data对象，用泛型)
    private T data;
}
