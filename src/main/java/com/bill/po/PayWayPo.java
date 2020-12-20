package com.bill.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-19-2020 18:48:33
 * @description :  code实体类
 * @since :  v1.0
 */
@Entity
@Data
@ToString
@Table(name = "pay_way")
public class PayWayPo {

    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // code名称
    private String wayName;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
