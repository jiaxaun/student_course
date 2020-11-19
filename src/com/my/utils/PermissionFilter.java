package com.my.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"},initParams = {
        @WebInitParam(name = "exclude", value = "/login.jsp,/login,/validatecode.jsp,/register.jsp,/admin,/register,/register.jsp,/tRegister.jsp,/stuRegister.jsp,/noprivilige.jsp,.css,.jpg,.png,.js")
})
public class PermissionFilter implements Filter {

    public static String excludeString;

    public void init(FilterConfig config) throws ServletException {
        excludeString = config.getInitParameter("exclude");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        Object user = httpServletRequest.getSession().getAttribute("user");

        String uri = httpServletRequest.getRequestURI();
        if(isExist(uri) || uri.equals(httpServletRequest.getContextPath()+"/")){
            chain.doFilter(httpServletRequest, httpServletResponse);
        }else{
            if (user != null) {
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.sendRedirect("noprivilige.jsp");
            }
        }
    }

    public static boolean isExist(String url){
        //最后uri的结尾与exclude匹配
        String[] arr = excludeString.split(",");
        boolean flag = false;
        for (String string : arr){
            if(url.endsWith(string)){
                flag = true;
            }
        }
        return  flag;
    }

    public void destroy() {
    }

}
