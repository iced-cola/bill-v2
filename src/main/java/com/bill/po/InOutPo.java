package com.bill.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  12-19-2020 20:45:21
 * @description :  收支分类
 * @since :  v1.0
 */
@Entity
@Data
@ToString
@Table(name = "in_out")
public class InOutPo {

    @Id
    private Integer id;

    private String type;

    private Date createTime;

    private Date updateTime;

    public InOutPo(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public InOutPo(Integer id, String type, Date createTime, Date updateTime) {
        this.id = id;
        this.type = type;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public InOutPo() {}
}
