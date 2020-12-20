package com.bill.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-19-2020 17:40:49
 * @description :  账单实体类
 * @since :  v1.0
 */
@Data
@NoArgsConstructor
@ToString
@Entity
@DynamicUpdate
@Table(name = "bill")
public class BillPo {

    // id
    @Id
    private String billId;

    // 账单日期
    private String billDate;

    // 账单金额
    private BigDecimal amount;

    // 支出类型
    private Integer inOut;

    // 资金用途
    private Integer usageType;

    // 支付方式
    private Integer payWay;

    // 账单详情
    private String detail;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    public BillPo(String billDate, BigDecimal amount, Integer inOut, Integer usageType,
                  Integer payWay, String detail) {
        this.billDate = billDate;
        this.amount = amount;
        this.inOut = inOut;
        this.usageType = usageType;
        this.payWay = payWay;
        this.detail = detail;
    }
}
