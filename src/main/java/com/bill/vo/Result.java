package com.bill.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-21-2020 17:18:59
 * @description :  前端用返回结果
 * @since :  v1.0
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

}
