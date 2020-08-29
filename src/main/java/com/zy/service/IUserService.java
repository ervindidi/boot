package com.zy.service;

import com.zy.entity.UserInfo;

public interface IUserService extends IBaseService<UserInfo,Integer>{
    UserInfo findByUserTelephone(String userTelephone);
}
