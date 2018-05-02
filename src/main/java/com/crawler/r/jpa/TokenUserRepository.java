package com.crawler.r.jpa;

import com.crawler.r.entity.TokenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

/**
 * TargetToken jpa 接口层
 */
public interface TokenUserRepository extends JpaRepository<TokenUser, Long>, Serializable {

    @Query(value = "select tu from TokenUser tu where tu.address=:address",nativeQuery = false)
    TokenUser findByAddress(@Param("address") String addess);

    @Query(value = "select tu from TokenUser tu where tu.address=:address and tu.tokenType is not null",nativeQuery = false)
    TokenUser findByAddressAndNotNull(@Param("address") String addess);

}
