package com.zy.service.impl;

import com.zy.dao.GoldDetailDao;
import com.zy.dao.IntegralDetailDao;
import com.zy.dao.LotteryDetailDao;
import com.zy.dao.UserInfoDao;
import com.zy.entity.GoldDetail;
import com.zy.entity.IntegralDetail;
import com.zy.entity.LotteryDetail;
import com.zy.entity.UserInfo;
import com.zy.service.*;
import com.zy.util.ResultUtil;
import com.zy.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@Service
public class LotteryDetailService extends BaseService<LotteryDetail,Integer> implements ILotteryDetailService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private LotteryDetailDao lotteryDetailDao;
    @Autowired
    private IntegralDetailDao integralDetailDao;
    @Autowired
    private GoldDetailDao goldDetailDao;

    @Transactional(rollbackFor = Exception.class)
    public HashMap<String,Object> lottery(HttpServletRequest request, String token){
        HashMap<String,String> map = TokenUtil.verify(token);
        Integer user_id = new Integer( map.get("id") );

        String[] gifts = {"1金豆","2金豆","3金豆","5金豆","10金豆","20金豆","50金豆","100金豆"};
        Integer[] golds = {1,2,3,5,10,20,50,100};
        Double[] prob = {0.4,0.25,0.15,0.1,0.05,0.025,0.015,0.01};//  qiwang 4.6
        int index = 0;//获奖编号
        double sum = 0;
        double flag = Math.random();
        for(int i = 0; i < prob.length; i++){
            sum+=prob[i];
            if(flag < sum) {
                index = i;
                break;
            }
        }
        String msg = "消耗100积分，获得奖品："+gifts[index];
        UserInfo userInfo = userInfoDao.findById(user_id).get();
        if(userInfo.getIntegral() < 100){
            return ResultUtil.getResult(request,"-2","积分不足");
        }

        LotteryDetail lotteryDetail = new LotteryDetail().setTime(new Date()).
                setUser_id(user_id).setLottery_type("1").setLottery_msg(msg);
        IntegralDetail integralDetail = new IntegralDetail().
                setIntegral(userInfo.getIntegral()-100).setIntegral_msg(msg).setIntegral_type("2")
                .setUser_id(userInfo.getId()).setTime(new Date());
        GoldDetail goldDetail = new GoldDetail().setGold(userInfo.getGold()+golds[index])
                .setGold_msg(msg).setGold_type("1").setTime(new Date()).setUser_id(userInfo.getId())
                .setUser_id(userInfo.getId());
        userInfo.setIntegral(userInfo.getIntegral()-100).setGold(userInfo.getGold()+golds[index]);

        lotteryDetailDao.save(lotteryDetail);
        integralDetailDao.save(integralDetail);
        goldDetailDao.save(goldDetail);
        userInfoDao.save(userInfo);
        return ResultUtil.getResult(request,"0",msg,index);
    }
}
