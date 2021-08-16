package com.boot.mongodb.repository.impl;

import com.boot.mongodb.entity.ImgInfo;
import com.boot.mongodb.repository.ImgInfoRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

//@Service
@Repository
public class ImgInfoRepositoryImpl implements ImgInfoRepository {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveImg(ImgInfo imgInfo) {
        mongoTemplate.save(imgInfo) ;
    }

    @Override
    public ImgInfo findByImgTitle(String imgTitle) {
        Query query=new Query(Criteria.where("imgTitle").is(imgTitle));
        return mongoTemplate.findOne(query,ImgInfo.class);
    }

    @Override
    public long updateImgInfo(ImgInfo imgInfo) {
        Query query = new Query(Criteria.where("imgId").is(imgInfo.getImgId()));
        Update update= new Update().set("imgTitle", imgInfo.getImgTitle()).set("imgUrl", imgInfo.getImgUrl());
        UpdateResult result = mongoTemplate.updateFirst(query,update,ImgInfo.class);
        return result.getMatchedCount();
    }

    @Override
    public void deleteById(Integer imgId) {
        Query query = new Query(Criteria.where("imgId").is(imgId));
        mongoTemplate.remove(query,ImgInfo.class);
    }
}
