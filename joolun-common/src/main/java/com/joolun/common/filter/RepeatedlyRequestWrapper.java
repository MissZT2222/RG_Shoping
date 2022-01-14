package com.joolun.common.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import com.joolun.common.utils.http.HttpHelper;

/**
 * 构建可重复读取inputStream的request
 * 
 * @author ruoyi
 */
public class RepeatedlyRequestWrapper extends HttpServletRequestWrapper
{
    private final byte[] body;
    //修改父类request编码
    public RepeatedlyRequestWrapper(HttpServletRequest request, ServletResponse response) throws IOException
    {
        super(request);
        request.setCharacterEncoding("UTF-8"); //修改编码
        response.setCharacterEncoding("UTF-8");

        body = HttpHelper.getBodyString(request).getBytes("UTF-8");
    }

    @Override
    public BufferedReader getReader() throws IOException
    {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * BufferedReader类从字符输入流中读取文本并缓冲字符，以便有效地读取字符，数组和行
     * InputStreamReader类是从字节流到字符流的桥接器：它使用指定的字符集读取字节并将它们解码为字符。
     * 它使用的字符集可以通过名称指定，也可以明确指定，或者可以接受平台的默认字符集。
     * 每次调用一个InputStreamReader的read（）方法都可能导致从底层字节输入流中读取一个或多个字节。
     * 为了实现字节到字符的有效转换，可以从基础流中提取比满足当前读取操作所需的更多字节。
     * 为了获得最高效率，请考虑在BufferedReader中包装InputStreamReader
     */
    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        /**
         * 字节数组输入流在内存中创建一个字节数组缓冲区，从输入流读取的数据保存在该字节数组缓冲区中。
         * 创建字节数组输入流对象有以下几种方式。
         */
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        /**
         * 通过ServletInputStream读取http请求传入的数据
         */
        return new ServletInputStream()
        {

            @Override
            public int read() throws IOException
            {
                return bais.read();
            }

            @Override
            public boolean isFinished()
            {
                return false;
            }

            @Override
            public boolean isReady()
            {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener)
            {

            }
        };
    }
}
