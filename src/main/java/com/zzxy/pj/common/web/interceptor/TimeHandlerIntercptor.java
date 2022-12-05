package com.zzxy.pj.common.web.interceptor;

import com.zzxy.pj.common.web.exception.TimeHandlerException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

//自定义拦截器,实现拦截器的接口
public class TimeHandlerIntercptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //日历类
        Calendar c = Calendar.getInstance();
        //获取当前是几点
        int hour = c.get(Calendar.HOUR);
        if (hour < 18 || hour > 22){
            throw new TimeHandlerException("请在8:00~22:00访问");
        }
        return true;
    }
}
