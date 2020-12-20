package com.bill.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-21-2020 23:32:22
 * @description :  用途区分
 * @since :  v1.0
 */
@Data
@Entity
@ToString
@Table(name = "usage_type")
public class UsageTypePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String typeName;

    private Date createTime;

    private Date updateTime;
}
