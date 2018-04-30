package com.crawler.r.jpa;

import com.crawler.r.entity.TargetToken;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

/**
 * TargetToken jpa 接口层
 */
public interface TargetTokenRepository extends JpaRepository<TargetToken, Long>, Serializable {

}
