package com.zy.service;

import com.zy.entity.LotteryDetail;

import java.util.HashMap;

public interface ILotteryDetailService extends IBaseService<LotteryDetail,Integer> {
    HashMap<String,Object> lottery(String token);
}
