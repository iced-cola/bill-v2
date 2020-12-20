package com.bill.service.impl;

import com.bill.constant.ExceptionEnum;
import com.bill.constant.ResultEnum;
import com.bill.dto.BillConditionDto;
import com.bill.dto.BillDto;
import com.bill.exception.BillException;
import com.bill.po.BillPo;
import com.bill.po.InOutPo;
import com.bill.po.PayWayPo;
import com.bill.po.UsageTypePo;
import com.bill.repository.BillRepository;
import com.bill.repository.InOutRepository;
import com.bill.repository.PayWayRepository;
import com.bill.repository.UsageTypeRepository;
import com.bill.service.BillService;
import com.bill.util.ResultUtil;
import com.bill.vo.BillPage;
import com.bill.vo.Result;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-19-2020 20:04:11
 * @description :  账单Service功能实现
 * @since :  v1.0
 */
@Service
@Slf4j
public class BillServiceImpl implements BillService {

    private static final String BILL_ID_START_NO = "-001";

    @Resource
    private BillRepository billRepository;

    @Resource
    private InOutRepository inOutRepository;

    @Resource
    private UsageTypeRepository usageTypeRepository;

    @Resource
    private PayWayRepository payWayRepository;

    @Override
    public Result<Object> create(BillPo billPo) {
        // 1. 判断参数是否齐全
        if (!isValidBill(billPo)) {
            log.error("【创建账单】参数不正确, billPo={}", billPo);
            throw new BillException(ExceptionEnum.PARAM_ERROR);
        }

        // 2. 生成主键id
        billPo.setBillId(genBillId(billPo.getBillDate()));
        if (billPo.getInOut() != 1) {
            billPo.setUsageType(0);
        }

        // 3. 保存数据库
        billRepository.save(billPo);
        return ResultUtil.success(ResultEnum.SUCCESS);
    }

    @Override
    public Result<BillPage<BillDto>> list(BillConditionDto conditionDto, Pageable pageable) {
        Specification<BillPo> specification = (Specification<BillPo>) (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if (!Strings.isNullOrEmpty(conditionDto.getBillDateFrom())
                    && !Strings.isNullOrEmpty(conditionDto.getBillDateTo())) {
                predicateList.add(criteriaBuilder.between(root.get("billDate"),
                        conditionDto.getBillDateFrom(), conditionDto.getBillDateTo()));
            }

            if (!Strings.isNullOrEmpty(conditionDto.getDetail())) {
                predicateList.add(criteriaBuilder.like(root.get("detail"), "%" + conditionDto.getDetail() + "%"));
            }

            if (!CollectionUtils.isEmpty(conditionDto.getInOutList())) {
                Join<Object, Object> inOutPo = root.join("InOutPo", JoinType.LEFT);
                predicateList.add(criteriaBuilder.equal(root.get("inOut"), inOutPo.get("id")));
                predicateList.add(criteriaBuilder.and(inOutPo.get("type").in(conditionDto.getInOutList())));
            }

            if (!CollectionUtils.isEmpty(conditionDto.getPayWayList())) {
                Join<Object, Object> payWayPo = root.join("PayWayPo", JoinType.LEFT);
                predicateList.add(criteriaBuilder.equal(root.get("payWay"), payWayPo.get("id")));
                predicateList.add(criteriaBuilder.and(payWayPo.get("wayName").in(conditionDto.getPayWayList())));
            }
            if (!CollectionUtils.isEmpty(conditionDto.getUsageList())) {
                Join<Object, Object> usageTypePo = root.join("UsageTypePo", JoinType.LEFT);
                predicateList.add(criteriaBuilder.equal(root.get("usageType"), usageTypePo.get("id")));
                predicateList.add(criteriaBuilder.and(usageTypePo.get("typeName").in(conditionDto.getUsageList())));
            }

            Predicate[] array = new Predicate[predicateList.size()];
            return criteriaQuery.where(predicateList.toArray(array)).getRestriction();
        };

        Page<BillPo> billPoPage = billRepository.findAll(specification, pageable);
        List<BillDto> billDtoList = convertBill(billPoPage.getContent());
        BillPage<BillDto> billDtoPage = new BillPage<>();
        billDtoPage.setCount(billPoPage.getNumberOfElements());
        billDtoPage.setTotalPages(billPoPage.getTotalPages());
        billDtoPage.setDataList(billDtoList);
        return ResultUtil.success(billDtoPage);
    }

    /**
     * 实体类与表单之间转换
     *
     * @param billPoList 实体类
     * @return 表单数据
     */
    private List<BillDto> convertBill(List<BillPo> billPoList) {
        List<BillDto> resultList = new ArrayList<>();
        List<InOutPo> inOutPoList = inOutRepository.findAll();
        List<PayWayPo> payWayPoList = payWayRepository.findAll();
        List<UsageTypePo> usageTypePoList = usageTypeRepository.findAll();
        for (BillPo billPo : billPoList) {
            BillDto billDto = new BillDto();
            BeanUtils.copyProperties(billPo, billDto);
            billDto.setAmountString(billPo.getAmount().toString());

            for (InOutPo inOutPo : inOutPoList) {
                if (billDto.getInOut().equals(inOutPo.getId())) {
                    billDto.setInOutName(inOutPo.getType());
                    break;
                }
            }

            for (PayWayPo payWayPo : payWayPoList) {
                if (billDto.getPayWay().equals(payWayPo.getId())) {
                    billDto.setPayWayName(payWayPo.getWayName());
                    break;
                }
            }

            if ("支出".equals(billDto.getInOutName())) {
                for (UsageTypePo usageTypePo : usageTypePoList) {
                    if (billDto.getUsageType().equals(usageTypePo.getId())) {
                        billDto.setUsageTypeName(usageTypePo.getTypeName());
                        break;
                    }
                }
            }

            resultList.add(billDto);
        }
        return resultList;
    }

    /**
     * 参数检查
     *
     * @param billPo 新增账单实体
     * @return 合法时返回true 不合法返回false
     */
    private boolean isValidBill(BillPo billPo) {
        return billPo != null
                && !Strings.isNullOrEmpty(billPo.getBillDate())
                && !Strings.isNullOrEmpty(String.valueOf(billPo.getAmount()))
                && !Strings.isNullOrEmpty(String.valueOf(billPo.getInOut()));
    }

    /**
     * 根据账单日期生成最大编号作为id
     *
     * @param billDate 账单日期 yyyy-MM-dd
     * @return 新记录的id
     */
    private String genBillId(String billDate) {
        String maxBillId = billRepository.findMaxIdByBillDate(billDate);
        if (Strings.isNullOrEmpty(maxBillId)) {
            maxBillId = billDate + BILL_ID_START_NO;
        } else {
            int no = Integer.parseInt(maxBillId.substring(maxBillId.length() - 3)) + 1;
            maxBillId = billDate + "-" + Strings.padStart(String.valueOf(no), 3, '0');
        }
        return maxBillId;
    }

    @Override
    public Result<Object> modify(BillPo billPo) {
        if (!isValidBill(billPo) || Strings.isNullOrEmpty(billPo.getBillId())) {
            log.error("【修改账单】参数不正确，billPo={}", billPo);
            throw new BillException(ExceptionEnum.PARAM_ERROR);
        } else {
            billRepository.save(billPo);
        }
        return ResultUtil.success(ResultEnum.SUCCESS);
    }

    @Override
    public Result<Object> delete(String id) {
        if (Strings.isNullOrEmpty(id)) {
            log.error("【删除账单】参数为空");
            throw new BillException(ExceptionEnum.PARAM_ERROR);
        }
        billRepository.deleteById(id);
        return ResultUtil.success(ResultEnum.SUCCESS);
    }

    @Override
    public Result<Object> delete(List<String> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            log.error("【删除账单】参数为空");
            throw new BillException(ExceptionEnum.PARAM_ERROR);
        }
        billRepository.deleteByIdList(idList);
        return ResultUtil.success(ResultEnum.SUCCESS);
    }

    @Override
    public Result<Object> createPayWay(PayWayPo payWayPo) {
        if (payWayPo == null || Strings.isNullOrEmpty(payWayPo.getWayName())) {
            log.error("【创建支付方式】参数为空");
            throw new BillException(ExceptionEnum.PARAM_ERROR);
        }
        payWayRepository.save(payWayPo);
        return ResultUtil.success(ResultEnum.SUCCESS);
    }

    @Override
    public Result<Object> listPayWay() {
        List<PayWayPo> all = payWayRepository.findAll();
        return ResultUtil.success(all);
    }

    @Override
    public Result<Object> createUsage(UsageTypePo usageTypePo) {
        if (usageTypePo == null || Strings.isNullOrEmpty(usageTypePo.getTypeName())) {
            log.error("【创建账单用途】参数为空");
            throw new BillException(ExceptionEnum.PARAM_ERROR);
        }
        usageTypeRepository.save(usageTypePo);
        return ResultUtil.success(ResultEnum.SUCCESS);
    }

    @Override
    public Result<Object> listUsage() {
        List<UsageTypePo> all = usageTypeRepository.findAll();
        return ResultUtil.success(all);
    }

    @Override
    public Result<Object> listBill(BillConditionDto conditionDto, Pageable pageable) {
        Specification<BillPo> specification = (Specification<BillPo>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!Strings.isNullOrEmpty(conditionDto.getBillDateFrom())
                    && !Strings.isNullOrEmpty(conditionDto.getBillDateTo())) {
                predicateList.add(criteriaBuilder.between(root.get("billDate"),
                        conditionDto.getBillDateFrom(), conditionDto.getBillDateTo()));
            }

            if (!Strings.isNullOrEmpty(conditionDto.getDetail())) {
                predicateList.add(criteriaBuilder.like(root.get("detail"), '%' + conditionDto.getDetail() + '%'));
            }

            if (!CollectionUtils.isEmpty(conditionDto.getInOutList())) {
                predicateList.add(criteriaBuilder.and(root.get("inOut").in(conditionDto.getInOutList())));
            }

            if (!CollectionUtils.isEmpty(conditionDto.getPayWayList())) {
                predicateList.add(criteriaBuilder.and(root.get("payWay").in(conditionDto.getPayWayList())));
            }

            if (!CollectionUtils.isEmpty(conditionDto.getUsageList())) {
                predicateList.add(criteriaBuilder.and(root.get("usageType").in(conditionDto.getUsageList())));
            }

            Predicate[] array = new Predicate[predicateList.size()];
            return criteriaQuery.where(predicateList.toArray(array)).getRestriction();
        };

        Page<BillPo> billPoPage = billRepository.findAll(specification, pageable);
        BillPage<BillDto> page = new BillPage<>();
        page.setDataList(convertBill(billPoPage.getContent()));
        page.setCount(billPoPage.getNumberOfElements());
        page.setTotalPages(billPoPage.getTotalPages());
        return ResultUtil.success(page);
    }
}
