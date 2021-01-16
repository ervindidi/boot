package com.zy.service;

import com.zy.entity.LotteryDetail;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface ILotteryDetailService extends IBaseService<LotteryDetail,Integer> {
    HashMap<String,Object> lottery(HttpServletRequest request, String token);
}
