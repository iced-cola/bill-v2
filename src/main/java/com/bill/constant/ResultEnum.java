package com.bill.constant;

import lombok.Getter;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-21-2020 17:23:07
 * @description :  返回状态
 * @since :  v1.0
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "操作成功"),

    FAILED(400, "操作失败");


    private final Integer code;

    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
