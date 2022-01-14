package com.joolun.framework.config;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.joolun.common.filter.RepeatableFilter;
import com.joolun.common.filter.XssFilter;
import com.joolun.common.utils.StringUtils;

/**
 * Filter配置
 *
 * @author ruoyi
 */
@Configuration
public class FilterConfig
{
    /**
     * 过滤开关
     * @return .
     */
    @Value("${xss.enabled}")
    private String enabled;

    /**
     * excludes 排除链接
     * @return .
     */
    @Value("${xss.excludes}")
    private String excludes;

    /**
     * 匹配链接
     * @return .
     */
    @Value("${xss.urlPatterns}")
    private String urlPatterns;
    //@SuppressWarnings(value=”unchecked”)。注释可以附加在package, class, method, field等上面，相当于给它们添加了额外的辅助信息，我们可以通过反射机制编程实现对这些元数据的访问。
    // 如果没有外部解析工具等对其加以解析和处理的情况，本身不会对Java的源代码或class文件等产生任何影响，也不会对它们的执行产生任何影响。
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean xssFilterRegistration()
    {
        //FilterRegistrationBean SpringBoot的过滤器
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //设置调度程序类型
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        //设置过滤器
        registration.setFilter(new XssFilter());
        //split() 方法用于把一个字符串分割成字符串数组。
        registration.addUrlPatterns(StringUtils.split(urlPatterns, ","));
        registration.setName("xssFilter");
        //setOrder 设定顺序。HIGHEST_PRECEDENCE 最高优先权
        registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("excludes", excludes);
        initParameters.put("enabled", enabled);
        //设置初始化参数
        registration.setInitParameters(initParameters);
        return registration;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean someFilterRegistration()
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RepeatableFilter());
        registration.addUrlPatterns("/*"); //添加URL模式
        registration.setName("repeatableFilter");
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);//最低优先级
        return registration;
    }

}
