package com.bill.exception;

import com.bill.constant.ExceptionEnum;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-19-2020 20:18:45
 * @description :  账单异常类
 * @since :  v1.0
 */
public class BillException extends RuntimeException {

    private Integer code;

    public BillException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }
}
