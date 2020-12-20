package com.bill.constant;

import lombok.Getter;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-19-2020 20:20:06
 * @description :  异常枚举
 * @since :  v1.0
 */
@Getter
public enum ExceptionEnum {

    PARAM_ERROR(10, "参数不正确");


    private Integer code;

    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
