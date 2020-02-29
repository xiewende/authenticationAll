package com.course.authentication.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @create 2019-12-28  22:13
 */
@Service
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求处理之前进行调用（Controller方法调用之前）,返回true才会继续往下执行，返回false取消当前请求
        boolean isAccess = false;
        System.out.println("进入到拦截器！！！！");

        String teacher = (String)redisTemplate.opsForValue().get("LoginUser");
        //已经登陆
        if(teacher!=null){
            System.out.println("有用户登陆了！！！");
            redisTemplate.opsForValue().set("LoginUser", teacher,30*60, TimeUnit.SECONDS);
            isAccess = true;
        }else {
            System.out.println("没有用户登陆了！！！");
        }
        return isAccess;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
