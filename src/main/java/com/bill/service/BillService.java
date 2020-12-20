package com.bill.service;

import com.bill.dto.BillConditionDto;
import com.bill.dto.BillDto;
import com.bill.po.BillPo;
import com.bill.po.PayWayPo;
import com.bill.po.UsageTypePo;
import com.bill.vo.BillPage;
import com.bill.vo.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-19-2020 19:44:44
 * @description :  账单服务接口
 * @since :  v1.0
 */
public interface BillService {

    /**
     * 创建一条账单记录
     *
     * @param billPo 账单实体
     * @return 账单Dto
     */
    Result<Object> create(BillPo billPo);

    /**
     * 按条件检索账单数据
     *
     * @param conditionDto 检索条件
     * @return 结果集
     */
    Result<BillPage<BillDto>> list(BillConditionDto conditionDto, Pageable pageable);

    /**
     * 修改一条账单记录
     *
     * @param billPo 账单实体
     */
    Result<Object> modify(BillPo billPo);

    /**
     * 删除一条账单记录
     *
     * @param id 主键id
     */
    Result<Object> delete(String id);

    /**
     * 批量删除账单记录
     *
     * @param idList id集合
     */
    Result<Object> delete(List<String> idList);

    /**
     * 创建新的支付方式
     *
     * @param payWayPo 支付方式
     * @return 支付方式
     */
    Result<Object> createPayWay(PayWayPo payWayPo);

    /**
     * 初始化页面下拉选项
     *
     * @return 所有的支付方式集合
     */
    Result<Object> listPayWay();

    /**
     * 创建新的账单用途
     *
     * @param usageTypePo 用途
     * @return 用途
     */
    Result<Object> createUsage(UsageTypePo usageTypePo);

    /**
     * 初始化页面下拉选项
     *
     * @return 所有的用途区分的集合
     */
    Result<Object> listUsage();

    /**
     * 按条件查找账单
     *
     * @param conditionDto 检索条件
     * @return 账单分页
     */
    Result<Object> listBill(BillConditionDto conditionDto, Pageable pageable);

}
