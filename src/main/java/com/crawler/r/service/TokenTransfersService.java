package com.crawler.r.service;

import com.crawler.r.entity.TokenTransfers;
import com.crawler.r.jpa.TokenTransfersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hcj on 2018/4/25.
 */
@Service("transfersService")
public class TokenTransfersService {

    @Autowired
    TokenTransfersRepository transfersRepository;

    public void save(List<TokenTransfers> transferses){
        transfersRepository.save(transferses);
    }

}
