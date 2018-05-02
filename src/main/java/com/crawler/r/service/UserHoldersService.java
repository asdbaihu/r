package com.crawler.r.service;

import com.crawler.r.entity.TokenUser;
import com.crawler.r.entity.UserHolders;
import com.crawler.r.jpa.TokenUserRepository;
import com.crawler.r.jpa.UserHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHoldersService {

    @Autowired
    UserHoldersRepository holdersRepository;

    public void save(UserHolders holders){
        holdersRepository.saveAndFlush(holders);
    }


    public UserHolders findByTargetIdAndUAddress(Long id,String address){
        return holdersRepository.findByTargetIdAndUAddress(id,address);
    }

    /**
     * 查询为处理数据
     * @return
     */
    public List<UserHolders> selectByFlag(){
       return holdersRepository.selectByFlag();
    }


    public List<UserHolders> getListHolders(){
        return holdersRepository.getListHolders();
    }
}


