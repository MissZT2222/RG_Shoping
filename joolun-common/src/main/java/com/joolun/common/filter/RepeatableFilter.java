package com.joolun.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import com.joolun.common.utils.StringUtils;

/**
 * Repeatable 过滤器
 * 
 * @author ruoyi
 */
public class RepeatableFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        ServletRequest requestWrapper = null;
        //instanceof用来测试一个对象是否为一个类的实例
        //equalsAnyIgnoreCase方法比较字符串与给定的数组中任何一个字符串相等就返回true
        //request.getContentType()获取本次请求过程中，页面的编码格式
        //StringUtils.equalsAnyIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE) 对比编码格式是否为application/json
        if (request instanceof HttpServletRequest && StringUtils.equalsAnyIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE))
        {
            requestWrapper = new RepeatedlyRequestWrapper((HttpServletRequest) request, response);
        }
        if (null == requestWrapper)
        {
            //chain.doFilter将请求转发给过滤器链下一个filter,如果没有filter那就是你请求的资源
            chain.doFilter(request, response);
        }
        else
        {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy()
    {

    }
}
