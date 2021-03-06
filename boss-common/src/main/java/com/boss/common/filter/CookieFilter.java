package com.boss.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Description: Cookie设置HttpOnly，Secure，Expire属性
 * @Author boss yan
 * @Date 2018/10/24 21:57
 */
@WebFilter(filterName = "cookieFilter", urlPatterns = "/*")
public class CookieFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            Cookie cookie = cookies[0];
            if (cookie != null) {
	            	/*cookie.setMaxAge(3600);
	            	cookie.setSecure(true);
	            	resp.addCookie(cookie);*/

                //Servlet 2.5不支持在Cookie上直接设置HttpOnly属性
                String value = cookie.getValue();
                StringBuilder builder = new StringBuilder();
                builder.append("JSESSIONID=" + value + "; ");
                builder.append("Secure; ");
                builder.append("HttpOnly; ");
                builder.append("Path=/; ");
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.HOUR, 1);
                Date date = cal.getTime();
                Locale locale = Locale.CHINA;
                SimpleDateFormat sdf =
                        new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", locale);
                builder.append("Expires=" + sdf.format(date));
                resp.setHeader("Set-Cookie", builder.toString());
            }
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}