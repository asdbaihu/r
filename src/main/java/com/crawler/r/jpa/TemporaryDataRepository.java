package com.crawler.r.jpa;

import com.crawler.r.entity.TemporaryData;
import com.crawler.r.entity.TokenTransfers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

/**
 * Created by hcj on 2018/4/29.
 */
public interface TemporaryDataRepository extends JpaRepository<TemporaryData, Long>, Serializable {

    @Query(value = "SELECT * FROM temporary_data tt WHERE tt.token_id=:id ORDER BY tt.transfers_date DESC LIMIT 1",nativeQuery = true)
    TemporaryData getByIdLimit(@Param("id")Long id);

}
