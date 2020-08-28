package com.zy.config;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.zy.util.TokenUtil;

@Component
public class TokenInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{

        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        response.setCharacterEncoding("utf-8");

        String token = request.getParameter("token");
        if(token != null){
            boolean result = TokenUtil.verify(token);
            if(result){
                System.out.println("通过拦截器");
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try{
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("msg","认证失败，未通过拦截器");
            map.put("code","-8");

            JSONObject obj = new JSONObject(map);
            response.getWriter().append(obj.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }

        return false;

    }

}