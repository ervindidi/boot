package com.zy.handler;

import com.zy.entity.UserInfo;
import com.zy.service.IUserService;
import com.zy.util.ResultUtil;
import com.zy.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserHandler {
    @Autowired
    private IUserService userService;

    @GetMapping("/login/{userName}/{userPwd}/{imageCode}/{tokenImageCode}")
    public HashMap<String,Object> login(@PathVariable String userName,
           @PathVariable String userPwd, @PathVariable String imageCode,@PathVariable String tokenImageCode){
        if(userName == null || userName.isEmpty()){
            return ResultUtil.getResult("-1","用户名不能为空");
        }
        if(userPwd == null || userPwd.isEmpty()){
            return ResultUtil.getResult("-2","密码不能为空");
        }
        if(imageCode == null || imageCode.isEmpty()){
            return ResultUtil.getResult("-3","验证码不能为空");
        }
        if(!userName.matches(".{3,12}")){
            return ResultUtil.getResult("-4","用户名必须是3-12位");
        }
        if(!userPwd.matches(".{6,18}")){
            return ResultUtil.getResult("-5","用户名必须是6-18位");
        }
        if(!imageCode.matches(".{4}")){
            return ResultUtil.getResult("-6","验证码必须是4位");
        }
        String tokenCode = TokenUtil.getImageCode(tokenImageCode);
        if(!imageCode.equalsIgnoreCase(tokenCode)){
            return ResultUtil.getResult("-7","验证码错误");
        }
        UserInfo info = userService.findByUserName(userName);
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
