package com.bill.util;

import com.bill.constant.ResultEnum;
import com.bill.vo.Result;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-21-2020 17:20:48
 * @description :  返回结果前端用
 * @since :  v1.0
 */
public class ResultUtil {

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(object);
        return result;
    }

    public static <T> Result<T> success(ResultEnum resultEnum) {
        Result<T> result = new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> failed(ResultEnum resultEnum) {
        Result<T> result = new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> failed(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

}
