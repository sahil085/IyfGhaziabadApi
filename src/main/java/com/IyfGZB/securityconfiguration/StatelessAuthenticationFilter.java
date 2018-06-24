package com.IyfGZB.securityconfiguration;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatelessAuthenticationFilter extends OncePerRequestFilter {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StatelessAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (!allowURL(request.getRequestURL())) {
                Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
                if(authentication instanceof AnonymousAuthenticationProvider || !authentication.isAuthenticated())
                {
                    response.sendRedirect("/home");
                }
            }
            chain.doFilter(request, response);
        } catch (Exception authEx) {
            logger.error("ErrorMessage = {} ", authEx.getMessage(), authEx);
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authEx.getMessage());
        }
    }

    public boolean allowURL(StringBuffer requestURL) {
        if (StringUtils.isEmpty(requestURL))
            return false;
        String url = requestURL.toString();
        if (url.contains("/home"))
            return true;
        else if (url.contains(".html"))
            return true;
        else if (url.contains(".png"))
            return true;
        else if (url.contains(".css"))
            return true;
        else if (url.contains(".js"))
            return true;
        else if (url.contains("jpg"))
            return true;
        else if (url.contains("jpeg"))
            return true;
        else if (url.contains("gif"))
            return true;
        else if (url.contains("/login"))
            return true;
        else if (url.contains("/register"))
            return true;

        return false;
    }
}
