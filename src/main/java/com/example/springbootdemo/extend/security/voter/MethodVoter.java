package com.example.springbootdemo.extend.security.voter;

import com.example.springbootdemo.extend.security.UrlGrantedAuthority;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Auther: yq
 * @Date: 2018-10-18 16:52
 * @Description:
 */
public class MethodVoter implements AccessDecisionVoter<Object> {

    private final static String  methodPrefix ="METHOD_";

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return configAttribute.getAttribute() != null && configAttribute.getAttribute().startsWith(methodPrefix);
    }

    @Override
    public boolean supports(Class aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {
        if(authentication == null)
            return ACCESS_DENIED;
        Iterator<ConfigAttribute> itr = collection.iterator();
        ConfigAttribute c;
        do{
            if(!itr.hasNext())
                return ACCESS_ABSTAIN;
            c = itr.next();
        }while(!supports(c));

        String allowMethod = c.getAttribute().replace(methodPrefix,"");
        Collection<? extends GrantedAuthority> list = authentication.getAuthorities();
        String canAccessMethod;
        for(GrantedAuthority ga:list){
            if(ga instanceof UrlGrantedAuthority){
                canAccessMethod = ((UrlGrantedAuthority)ga).getMethod();
                if(canAccessMethod == allowMethod || "ALL".equals(canAccessMethod))
                    return ACCESS_GRANTED;
            }
        }
        return ACCESS_DENIED;
    }

}
