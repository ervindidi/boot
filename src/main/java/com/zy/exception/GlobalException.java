package com.zy.exception;

import com.alibaba.fastjson.JSONObject;
import com.zy.handler.UserHandler;
import com.zy.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@ControllerAdvice
public class GlobalException {
    private static final Logger log = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 全局异常处理
     * @param request
     * @param response
     * @param e
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    public void ExceptionHandler(HttpServletRequest request,
                                             HttpServletResponse response, Exception e) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //请求uri
        String uri = request.getRequestURI();
        // 异常信息
        String emsg = e.getMessage();
        if( !(e instanceof MyException) ){
            emsg = "未知错误";
        }
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("uri",uri);
        data.put("msg",emsg);

        log.info("异常请求 : "+uri + "   异常信息 : "+emsg);

        HashMap<String,Object> map = ResultUtil.getResult("-101","系统异常",data);
        String jsonString =  JSONObject.toJSONString(map);
        response.getWriter().println(jsonString);
    }

}
