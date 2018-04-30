package com.crawler.r.jpa;

import com.crawler.r.entity.TemporaryData;
import com.crawler.r.entity.TokenTransfers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by hcj on 2018/4/25.
 */
public interface TokenTransfersRepository extends JpaRepository<TokenTransfers,Long>,Serializable {

    @Query(value = "SELECT * FROM token_transfers tt WHERE tt.flag='0'",nativeQuery = true)
    List<TokenTransfers> findByFlag();

    @Query(value = "SELECT * FROM token_transfers tt WHERE tt.token_id=:id ORDER BY tt.transfers_date DESC LIMIT 1",nativeQuery = true)
    TokenTransfers getByIdLimit(@Param("id")Long id);
}
