package com.zy.handler;

import com.zy.entity.GoldDetail;
import com.zy.entity.IntegralDetail;
import com.zy.entity.LotteryDetail;
import com.zy.entity.UserInfo;
import com.zy.service.*;
import com.zy.service.impl.UserService;
import com.zy.util.ResultUtil;
import com.zy.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityHandler {
    @Autowired
    private ILotteryDetailService lotteryDetailService;


    //抽奖
    @RequestMapping("/lottery.action")
    public HashMap<String,Object> lottery(String token){
        return lotteryDetailService.lottery(token);
    }
}
