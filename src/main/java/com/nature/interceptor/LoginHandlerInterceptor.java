package com.nature.interceptor;

import com.nature.pojo.system.Admin;
import com.nature.util.DirectoryTools;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录 拦截器
 * springboot
 * LoginHandlerInterceptor
 * Author: 竺志伟
 * Date:   2017-03-30 14:06
 */
public class LoginHandlerInterceptor implements HandlerInterceptor
{
    private static Logger logger = Logger.getLogger(LoginHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object o) throws Exception
    {
        logger.info(httpServletRequest.getRemoteAddr() + ":" + httpServletRequest.getRequestURI());
        String requestURI = httpServletRequest.getRequestURI();
        if(requestURI.equals("/webAdmin/") || requestURI.equals("/webAdmin/login") || requestURI.equals
                ("/webAdmin/loginOut"))
        {
            return true;
        }

        Admin userInfo = (Admin) httpServletRequest.getSession().getAttribute(DirectoryTools.SESSION_LOGIN_USER);
        if(userInfo != null)
        {
            return true;
        }
        else
        {
            httpServletResponse.setContentType("text/html;charset=UTF-8");
            httpServletResponse.getWriter()
                    .write("<style>.tip{margin:10px auto;border-left:solid #009688 5px;padding:20px;background-color:#e2e2e2;}.tip strong{color:#FF5722;}.tip i{color:#1E9FFF;}</style>");
            httpServletResponse.getWriter()
                    .write("<div class='tip'>对不起！您无访问 【<strong>" + httpServletRequest.getRequestURI() +
                            "</strong>】 的权限，可能是因为您 <i>登录超时</i> 或您 <i>未登录</i> ，页面将在3秒后自动跳转到首页。。。</div>");
            httpServletResponse.setHeader("Refresh", "3;url=/");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception
    {

    }
}
