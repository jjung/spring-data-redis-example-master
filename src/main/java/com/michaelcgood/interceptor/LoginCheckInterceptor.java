package com.michaelcgood.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.michaelcgood.model.User;  
  
  
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
  
        HttpSession session  = request.getSession(false);  
  System.out.println("********************");
  System.out.println(request.getRequestURI());
        if(session == null) {  
            response.sendRedirect(request.getContextPath());  
            return false;  
        }  

        System.out.println("********************");
        User user = (User)session.getAttribute("userLoginInfo");  
  
        if (user == null) {  
            response.sendRedirect(request.getContextPath());  
            return false;             
        }  
          
        return true;  
    }  
}
