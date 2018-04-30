package com.crawler.r.service;

import com.crawler.r.entity.TemporaryData;
import com.crawler.r.jpa.TemporaryDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hcj on 2018/4/29.
 */
@Service
public class TemporaryDataService {

    @Autowired
    TemporaryDataRepository dataRepository;

    /**
     * 批量插入
     * @param temporaryData
     */
    public void saveList(List<TemporaryData> temporaryData){
        dataRepository.save(temporaryData);
    }

    /**
     * 获取最近时间的一条数据
     * @param id
     * @return
     */
    public TemporaryData getByIdLimit(Long id){
        return dataRepository.getByIdLimit(id);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(Long id){
        dataRepository.delete(id);
    }

    public List<TemporaryData> findList(){
        return dataRepository.findAll();
    }

    public TemporaryData findById(Long id){
        return dataRepository.findOne(id);
    }

    /**
     * 批量删除
     * @param list
     */
    public void deleteList(List<TemporaryData> list){
        dataRepository.deleteInBatch(list);
    }

}
