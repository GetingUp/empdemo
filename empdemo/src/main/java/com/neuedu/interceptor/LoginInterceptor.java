package com.neuedu.interceptor;

import com.neuedu.entity.User;
import com.neuedu.util.CookieUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 核心控制器执行controller中方法之前执行
     * 如果此方法返回false，则控制器就不会再执行controller中的方法了
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //做一个免登录功能
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            //httpServletRequest.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(httpServletRequest,httpServletResponse);
            //获得cookie
            Cookie[] cookies = httpServletRequest.getCookies();
            Map<String,Cookie> cookieMap = CookieUtil.getCookieMap(cookies);
            Cookie userCookie = cookieMap.get("username");
            if(userCookie == null){
                String contextPath = httpServletRequest.getContextPath();
                httpServletResponse.sendRedirect( contextPath +"/user/loginView");
                return false;
            }else {
                String username = userCookie.getValue();
                User user1 = new User();
                user1.setUsername(username);
                httpSession.setAttribute("user",user1);
                return true;
            }
        }
            return true;
    }


    /**
     * 此方法在controller中方法执行之后执行并且在试图渲染之前
     * 补救措施：
     *      少存值了在这存   多存值了在这处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }


    /**
     * 在controller方法执行之后并且在试图渲染之后
     *
     * 清理工作和处理异常
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
