package com.gxa.child.interceptor;

import com.gxa.child.pojo.Admin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取session
        HttpSession session = request.getSession();
        //2.获取admin
        Admin admin = (Admin)session.getAttribute("admin");
        //3.判断是否为空
        if (admin !=null){
            return true;
        }else {
            //4.重定向至登录页
            response.sendRedirect("/loginPage");
            return false;
        }

    }
}
