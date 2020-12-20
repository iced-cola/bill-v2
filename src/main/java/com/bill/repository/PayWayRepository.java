package com.bill.repository;

import com.bill.po.PayWayPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-22-2020 08:11:19
 * @description :  pay way repository
 * @since :  v1.0
 */
@Repository
public interface PayWayRepository extends JpaRepository<PayWayPo, Integer> {

}
