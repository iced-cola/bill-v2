package com.bill.controller;

import com.bill.constant.ResultEnum;
import com.bill.dto.BillConditionDto;
import com.bill.po.BillPo;
import com.bill.service.BillService;
import com.bill.util.ResultUtil;
import com.bill.vo.BillPage;
import com.bill.vo.Result;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  12-20-2020 14:44:55
 * @description :  api
 * @since :  v1.0
 */
@RestController
@RequestMapping(value = "/bill")
public class BillController {

    @Resource
    private BillService billService;

    @GetMapping(value = "/payWay/list")
    public Result<Object> listPayWay() {
        try {
            return billService.listPayWay();
        } catch (Exception e) {
            return ResultUtil.failed(ResultEnum.FAILED.getCode(), e.getMessage());
        }
    }

    @GetMapping(value = "/usage/list")
    public Result<Object> listUsage() {
        try {
            return billService.listUsage();
        } catch (Exception e) {
            return ResultUtil.failed(ResultEnum.FAILED.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "/list")
    public Result<Object> billList(@RequestBody BillConditionDto conditionDto,
                                   @RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            return billService.listBill(conditionDto, pageable);
        } catch (Exception e) {
            return ResultUtil.failed(ResultEnum.FAILED.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "/create")
    public Result<Object> create(@RequestBody BillPo billPo) {
        try {
            return billService.create(billPo);
        } catch (Exception e) {
            return ResultUtil.failed(ResultEnum.FAILED.getCode(), e.getMessage());
        }
    }
}
