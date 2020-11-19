package com.my.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class CharacterEncodingFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletRequest.setCharacterEncoding("UTF-8");
        chain.doFilter(httpServletRequest,httpServletResponse);
        httpServletResponse.setContentType("text/html;charset=UTF-8");
    }

    public void destroy() {
    }

}
