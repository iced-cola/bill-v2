package com.bill.repository;

import com.bill.po.InOutPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author :  zhulongkun20@gmail.com
 * @create :  12-19-2020 20:48:36
 * @description :  收支分类dao
 * @since :  v1.0
 */
@Repository
public interface InOutRepository extends JpaRepository<InOutPo, Integer> {
}
