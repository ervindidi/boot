package com.zy.handler;

import com.zy.entity.IChangePwd;
import com.zy.entity.IRegister;
import com.zy.entity.UserInfo;
import com.zy.exception.MyException;
import com.zy.service.IUserService;
import com.zy.util.CloudInfDemo;
import com.zy.util.NumberUtil;
import com.zy.util.ResultUtil;
import com.zy.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

@RestController
public class UserHandler {
    private static final Logger log = LoggerFactory.getLogger(UserHandler.class);
    @Autowired
    private IUserService userService;

    @PostMapping("/login/{userTelephone}/{userPwd}/{imageCode}")
    public HashMap<String,Object> login(HttpServletRequest request, @PathVariable String userTelephone,
                                        @PathVariable String userPwd, @PathVariable String imageCode, HttpSession session){
        log.info("--------------"+imageCode+"    sessionID:"+session.getId());
        if(userTelephone == null || userTelephone.isEmpty()){
            return ResultUtil.getResult(request,"-1","手机号码不能为空");
        }
        if(userPwd == null || userPwd.isEmpty()){
            return ResultUtil.getResult(request,"-2","密码不能为空");
        }
        if(imageCode == null || imageCode.isEmpty()){
            return ResultUtil.getResult(request,"-3","验证码不能为空");
        }
        if(!userTelephone.matches("\\d{11}")){
            return ResultUtil.getResult(request,"-4","手机号码必须是11位");
        }
        if(!userPwd.matches(".{6,18}")){
            return ResultUtil.getResult(request,"-5","密码必须是6-18位");
        }
        if(!imageCode.matches(".{4}")){
            return ResultUtil.getResult(request,"-6","验证码必须是4位");
        }

        String sessionImageCode = (String)session.getAttribute("imageCode");
        if(!imageCode.equalsIgnoreCase(sessionImageCode)){
            return ResultUtil.getResult(request,"-7","验证码错误");
        }
        UserInfo info = userService.findByUserTelephone(userTelephone);
        if(info == null){
            return ResultUtil.getResult(request,"-8","用户不存在");
        }
        if(!userPwd.equals(info.getUserPwd())){
            return ResultUtil.getResult(request,"-9","密码不正确");
        }
        String tokenInfo = TokenUtil.sign(info);
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("tokenInfo",tokenInfo);
        data.put("userName",info.getUserName());
        data.put("userNike",info.getUserNick());
        return ResultUtil.getResult(request,"1","登陆成功",data);
    }

    @PostMapping("/reg")
    public HashMap<String,Object> reg(HttpServletRequest request,@Validated(IRegister.class) UserInfo userInfo,
                                      BindingResult br,String imageCode, HttpSession session){
        log.info("--------------"+imageCode+"    sessionID:"+session.getId());
        if(br.hasErrors()){//参数异常
            HashMap<String,String> map = new HashMap<String,String>();
            for (FieldError fe : br.getFieldErrors()){
                map.put(fe.getField()+"Error",fe.getDefaultMessage());
            }
            return ResultUtil.getResult(request,"-1","参数异常",map);
        }
        if(imageCode == null || imageCode.isEmpty()){
            return ResultUtil.getResult(request,"-2","验证码异常");
        }
        String sessionImageCode = (String)session.getAttribute("imageCode");
        log.info(sessionImageCode+"--------------"+imageCode+"    sessionID:"+session.getId());
        if(!imageCode.equalsIgnoreCase(sessionImageCode)){
            return ResultUtil.getResult(request,"-7","验证码错误");
        }
        if(userService.findByUserTelephone(userInfo.getUserTelephone())!=null){
            return ResultUtil.getResult(request,"-3","手机号码已经被注册");
        }
        try {
            userInfo.setIntegral(0);
            userInfo.setUserMoney(0d);
            userInfo.setUserRegTime(new Date());
            userService.add(userInfo);

            return ResultUtil.getResult(request,"1","注册成功");
        }catch (Exception e){
            return ResultUtil.getResult(request,"-3","注册失败");
        }
    }

    @PostMapping("/sendCode")
    public HashMap<String,Object> sendCode(HttpServletRequest request,String userTelephone, HttpSession session){
        if(userTelephone == null || userTelephone.isEmpty()){
            return ResultUtil.getResult( request,"-1","手机号码不能为空");
        }
        if(!userTelephone.matches("\\d{11}")){
            return ResultUtil.getResult( request,"-2","手机号码必须是11位数字");
        }
        String code = NumberUtil.newCode();
        session.setAttribute("imageCode",code);
        String result = CloudInfDemo.sendSmsCode(userTelephone,code);
        return ResultUtil.getResult( request,"1","短信发送成功");
        /*
        log.info("-------userTelephone-----------code--------------"+code+"    sessionID:"+session.getId());
        if(result.equals("0") || result.contains(":0,")){
            return ResultUtil.getResult("1","短信发送成功");
        }else{
            return ResultUtil.getResult("-3","短信发送失败");
        }*/
    }

    @PostMapping("/changePwd")
    public HashMap<String,Object> changePwd(HttpServletRequest request,@Validated(IChangePwd.class) UserInfo userInfo,
                                      BindingResult br,String imageCode, HttpSession session){
        if(br.hasErrors()){//参数异常
            HashMap<String,String> map = new HashMap<String,String>();
            for (FieldError fe : br.getFieldErrors()){
                map.put(fe.getField()+"Error",fe.getDefaultMessage());
            }
            return ResultUtil.getResult(request,"-1","参数异常",map);
        }
        if(imageCode == null || imageCode.isEmpty()){
            return ResultUtil.getResult(request,"-2","验证码不能为空");
        }
        String sessionImageCode = (String)session.getAttribute("imageCode");
        log.info(sessionImageCode+"--------------"+imageCode+"    sessionID:"+session.getId());
        if(!imageCode.equalsIgnoreCase(sessionImageCode)){
            return ResultUtil.getResult(request,"-7","验证码错误");
        }
        UserInfo info = userService.findByUserTelephone(userInfo.getUserTelephone());
        if(info==null){
            return ResultUtil.getResult(request,"-3","用户不存在");
        }
        try {
            info.setUserPwd(userInfo.getUserPwd());
            userService.update(info);

            return ResultUtil.getResult(request,"1","密码修改成功");
        }catch (Exception e){
            return ResultUtil.getResult(request,"-3","密码修改失败");
        }
    }

    @RequestMapping("/test")
    public HashMap<String,Object> test(HttpServletRequest request){
        int i = 1/0;
        return ResultUtil.getResult(request,"1","请求成功");
    }

    @RequestMapping("/noTokenTest")
    public HashMap<String,Object> noTokenTest(HttpServletRequest request,String token) throws MyException {
        if(token == null){
            throw new MyException("没有token");
        }
        return ResultUtil.getResult(request,"1","请求成功");
    }

    @RequestMapping("/test2")
    public HashMap<String,Object> test2(HttpServletRequest request){
        return ResultUtil.getResult(request,"1","请求成功");
    }

    @RequestMapping("/login")
    public HashMap<String,Object> loginTest(HttpServletRequest request,String userTelephone){
        UserInfo info = userService.findByUserTelephone(userTelephone);
        String tokenInfo = TokenUtil.sign(info);
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("tokenInfo",tokenInfo);
        data.put("userName",info.getUserName());
        data.put("userNike",info.getUserNick());
        return ResultUtil.getResult(request,"1","登陆成功",data);
    }
}
