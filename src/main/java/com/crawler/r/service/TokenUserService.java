package com.crawler.r.service;

import com.crawler.r.entity.TokenUser;
import com.crawler.r.jpa.TargetTokenRepository;
import com.crawler.r.jpa.TokenUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenUserService {

    @Autowired
    TokenUserRepository userRepository;

    public TokenUser findByAddress(String address) {
        return userRepository.findByAddress(address);
    }

    public void save(TokenUser tokenUser){
         userRepository.save(tokenUser);
    }

}


