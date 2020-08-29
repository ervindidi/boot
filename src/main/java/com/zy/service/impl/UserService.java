package com.zy.service.impl;

import com.zy.dao.UserInfoDao;
import com.zy.entity.UserInfo;
import com.zy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<UserInfo,Integer>  implements IUserService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUserTelephone(String userTelephone) {
        return userInfoDao.findByUserTelephone(userTelephone);
    }
}
