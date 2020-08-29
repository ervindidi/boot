package com.zy.handler;

import com.zy.entity.UserInfo;
import com.zy.service.IUserService;
import com.zy.util.ResultUtil;
import com.zy.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class UserHandler {
    @Autowired
    private IUserService userService;

    @PostMapping("/login/{userTelephone}/{userPwd}/{imageCode}")
    public HashMap<String,Object> login(@PathVariable String userTelephone,
                                        @PathVariable String userPwd, @PathVariable String imageCode, HttpSession session){
        if(userTelephone == null || userTelephone.isEmpty()){
            return ResultUtil.getResult("-1","手机号码不能为空");
        }
        if(userPwd == null || userPwd.isEmpty()){
            return ResultUtil.getResult("-2","密码不能为空");
        }
        if(imageCode == null || imageCode.isEmpty()){
            return ResultUtil.getResult("-3","验证码不能为空");
        }
        if(!userTelephone.matches(".{11}")){
            return ResultUtil.getResult("-4","手机号码必须是11位");
        }
        if(!userPwd.matches(".{6,18}")){
            return ResultUtil.getResult("-5","密码必须是6-18位");
        }
        if(!imageCode.matches(".{4}")){
            return ResultUtil.getResult("-6","验证码必须是4位");
        }

        String sessionImageCode = (String)session.getAttribute("imageCode");
        System.out.println(session.getId()+"============"+imageCode + "---------"+sessionImageCode);
        if(!imageCode.equalsIgnoreCase(sessionImageCode)){
            return ResultUtil.getResult("-7","验证码错误");
        }
        UserInfo info = userService.findByUserTelephone(userTelephone);
        if(info == null){
            return ResultUtil.getResult("-8","用户不存在");
        }
        if(!userPwd.equals(info.getUserPwd())){
            return ResultUtil.getResult("-9","密码不正确");
        }
        String tokenInfo = TokenUtil.sign(info);
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("tokenInfo",tokenInfo);
        data.put("userName",info.getUserName());
        data.put("userNike",info.getUserNick());
        return ResultUtil.getResult("1","登陆成功",data);
    }
}
