package com.zy.service;

import com.zy.entity.UserInfo;

public interface IUserService {
    UserInfo findByUserName(String userName);
}
