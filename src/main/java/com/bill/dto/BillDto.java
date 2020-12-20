package com.bill.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-19-2020 19:55:29
 * @description :  账单dto
 * @since :  v1.0
 */
@Data
public class BillDto {

    // id
    private String billId;

    // 账单日期
    private String billDate;

    // 账单金额
    private BigDecimal amount;

    private String amountString;

    // 支出类型
    private Integer inOut;

    private String inOutName;

    // 支付方式
    private Integer payWay;

    private String payWayName;

    // 资金用途
    private Integer usageType;

    private String usageTypeName;

    // 账单详情
    private String detail;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
