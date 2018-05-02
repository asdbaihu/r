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

    @Query(value = "select * from token_transfers where token_id =:id and transfers_date=:date and from_token=:froms and to_token=:to",nativeQuery = true)
    TokenTransfers getByIdAndDate(@Param("id")Long id,@Param("date")String date,@Param("froms")String froms,@Param("to")String to);

    /**
     * 获取今天的信息
     * @param tid
     * @return
     */
    @Query(value = "select * from token_transfers where token_id=:tid and TO_DAYS(create_date) = TO_DAYS(NOW())",nativeQuery = true)
    List<TokenTransfers> findListByTid(@Param("tid")Long tid);


    /**
     * 获取昨天的信息
     */
    @Query(value = "select * from token_transfers where  token_id=:tid and TO_DAYS(NOW()) - TO_DAYS(create_date) = 1",nativeQuery = true)
    List<TokenTransfers> findListByTidYesToday(@Param("tid")Long tid);



}
