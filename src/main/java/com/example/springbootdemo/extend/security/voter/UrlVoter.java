package com.example.springbootdemo.extend.security.voter;

import com.example.springbootdemo.extend.security.UrlGrantedAuthority;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Auther: yq
 * @Date: 2018-10-18 16:49
 * @Description:
 */
public class UrlVoter implements AccessDecisionVoter<Object> {

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {
        if(authentication == null)
            return ACCESS_DENIED;
        Collection<? extends GrantedAuthority> list = authentication.getAuthorities();
        String canAccessUrl;
        for(GrantedAuthority ga:list){
            if(ga instanceof UrlGrantedAuthority){
                canAccessUrl = ((UrlGrantedAuthority)ga).getPermissionUrl();
                if(match(canAccessUrl,((FilterInvocation)o).getRequest()))
                    return ACCESS_GRANTED;
            }
        }
        return ACCESS_DENIED;
    }

    private boolean match(String canAccessUrl, HttpServletRequest req){
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(canAccessUrl);
        if (matcher.matches(req)) {
            return true;
        }
        return false;
    }
}
