package com.zcy.restaurant.utils;

import com.zcy.restaurant.VO.ResultVO;

/**
 * @ Author: chunyang.zhang
 * @ Description: 每次 返回ResultVO 麻烦， 封装一个共用包
 * @ Date: Created in 16:59 2018/5/18
 * @ Modified: By:
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return null;
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
