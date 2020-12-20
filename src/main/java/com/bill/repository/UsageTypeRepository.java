package com.bill.repository;

import com.bill.po.UsageTypePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  10-22-2020 08:12:12
 * @description :  usage type repository
 * @since :  v1.0
 */
@Repository
public interface UsageTypeRepository extends JpaRepository<UsageTypePo, Integer> {

}
