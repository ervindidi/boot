package com.zy.dao;

import com.zy.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface UserInfoDao extends BaseRepository<UserInfo,Integer>{
    UserInfo findByUserTelephone(String userTelephone);
}
