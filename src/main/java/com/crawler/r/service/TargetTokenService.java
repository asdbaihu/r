package com.crawler.r.service;

import com.crawler.r.entity.TargetToken;
import com.crawler.r.jpa.TargetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetTokenService {

    @Autowired
    TargetTokenRepository targetTokenRepository;

    public List<TargetToken> findAllList(){
        return targetTokenRepository.findAll();
    }

    public TargetToken findById(Long id){
        return targetTokenRepository.findOne(id);
    }

    public TargetToken updateTar(TargetToken token){
        return targetTokenRepository.saveAndFlush(token);
    }

}


