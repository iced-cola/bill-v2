package com.bill.service.impl;

import com.bill.dto.BillConditionDto;
import com.bill.service.BillService;
import com.bill.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BillServiceImplTest {

    @Resource
    private BillService billService;

    @Test
    public void list() {
        Pageable pageable = PageRequest.of(0, 5);
        BillConditionDto conditionDto = new BillConditionDto();
//        conditionDto.setDetail("外卖");
        conditionDto.setInOutList(Collections.singletonList(1));
        Result<Object> result = billService.listBill(conditionDto, pageable);
        assertNotNull(result);
        log.info("result: \n{}", result);
    }
}
