package com.smelser.oozie.authorization;

import com.smelser.oozie.data.OozieClientFactory;
import com.smelser.oozie.utilities.ServiceLocator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * Created by psmelser on 16-07-05.
 *
 * @author paul.smelser@esignlive.com
 */
public class CookieAuthorizationFilter implements Filter {

    private final AuthorizationCookie authorizationCookie;
    private final OozieClientFactory factory;
    private final ServiceLocator serviceLocator;

    public CookieAuthorizationFilter(AuthorizationCookie authorizationCookie,
                                     OozieClientFactory factory,
                                     ServiceLocator serviceLocator){
        this.authorizationCookie = authorizationCookie;
        this.factory = factory;
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        if(nonNull(cookies)){
            for (Cookie ck : cookies) {
                if ("_oc".equals(ck.getName())) {
                    UserLogin auth = authorizationCookie.getAuthFromCookie(ck.getValue());
                    serviceLocator.register(factory.create(auth.getUrl(), auth.getUsername(), auth.getPassword()));
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
