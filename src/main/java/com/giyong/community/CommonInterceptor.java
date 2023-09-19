package com.giyong.community;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class CommonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (isNotLogin(request)) {
            response.sendRedirect("/admin/login");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private static boolean isNotLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("adminId") == null) {
            return true;
        }
        return false;
    }
}
