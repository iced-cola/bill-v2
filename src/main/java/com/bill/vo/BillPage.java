package com.bill.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-21-2020 22:52:56
 * @description :  分页类
 * @since :  v1.0
 */
@Data
@NoArgsConstructor
public class BillPage<T> {

    private Integer count;

    private Integer totalPages;

    private List<T> dataList;

    public BillPage(Integer count, Integer totalPages, List<T> dataList) {
        this.count = count;
        this.totalPages = totalPages;
        this.dataList = dataList;
    }
}
