package by.gstu.bot.learning.web.filter;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class LanguageFilter implements Filter {

    private LocaleResolver localeResolver;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(
                filterConfig.getServletContext());
        Map resolvers = wac.getBeansOfType(LocaleResolver.class);
        if (resolvers.size() == 1) {
            localeResolver = (LocaleResolver) resolvers.values().iterator().next();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (localeResolver!=null) {
            Locale locale = localeResolver.resolveLocale((HttpServletRequest) servletRequest);
            LocaleContextHolder.setLocale(locale);
        }
//        if (servletRequest.getParameter("lang") != null) {
//            RequestContextUtils.getLocaleResolver((HttpServletRequest) servletRequest).setLocale((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, Locale.ENGLISH);
//            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver((HttpServletRequest) servletRequest);
//            LocaleContextHolder.setLocale(new Locale(servletRequest.getParameter("lang")));
//            localeResolver.setLocale((HttpServletRequest)servletRequest, (HttpServletResponse) servletResponse, new Locale(servletRequest.getParameter("lang")));
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
