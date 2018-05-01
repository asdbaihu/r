package com.crawler.r.service;

import com.crawler.r.entity.TokenTransfers;
import com.crawler.r.jpa.TokenTransfersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by hcj on 2018/4/25.
 */
@Service
public class TokenTransfersService {

    @Autowired
    TokenTransfersRepository transfersRepository;

    /**
     * 批量插入
     * @param transferses
     */
    public void saveList(List<TokenTransfers> transferses){
        transfersRepository.save(transferses);
    }

    public List<TokenTransfers> findList(){
        return transfersRepository.findAll();
    }

    /**
     * 查询 flag标记为0 的数据，
     * @return
     */
    public List<TokenTransfers> findByFlag(){
       return transfersRepository.findByFlag();
    }

    /**
     * 批量删除
     * @param list
     */
    public void deleteList(List<TokenTransfers> list){
        transfersRepository.deleteInBatch(list);
    }

    public void update(TokenTransfers transfers){
        transfersRepository.saveAndFlush(transfers);
    }

    public TokenTransfers getByIdLimit(Long id){
        return transfersRepository.getByIdLimit(id);
    }

    public TokenTransfers getByIdAndDate(Long id,String date,String froms,String to){
        return transfersRepository.getByIdAndDate(id,date,froms,to);
    }

    public void save(TokenTransfers tokenTransfers){
        transfersRepository.saveAndFlush(tokenTransfers);
    }
}
