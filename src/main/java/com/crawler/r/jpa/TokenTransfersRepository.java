package com.crawler.r.jpa;

import com.crawler.r.entity.TokenTransfers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by hcj on 2018/4/25.
 */
public interface TokenTransfersRepository extends JpaRepository<TokenTransfers,Long>,Serializable {
}
