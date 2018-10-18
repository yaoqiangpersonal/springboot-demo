package com.example.springbootdemo.extend.security;


import com.example.springbootdemo.security.exception.PasswordException;
import javafx.util.Builder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Auther: yq
 * @Date: 2018-09-07 11:06
 * @Description: 认证器
 */
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {


    /**
     * 构建器
     */
    public static class LoginAuthenticationProviderBuilder implements Builder<LoginAuthenticationProvider> {

        private final UserDetailsService userDetailsService;

        private boolean hideUserNotFoundExceptions = true;

        private final PasswordEncoder encoder;

        private LoginAuthenticationProviderBuilder(UserDetailsService userDetailsService,PasswordEncoder encoder){
            this.userDetailsService = userDetailsService;
            this.encoder = encoder;
        }

        public LoginAuthenticationProviderBuilder setHideUserNotFoundExceptions(boolean hideUserNotFoundExceptions){
            this.hideUserNotFoundExceptions = hideUserNotFoundExceptions;
            return this;
        }

        //传入一个构建器，生成对象
        @Override
        public LoginAuthenticationProvider build() {
            return new LoginAuthenticationProvider(this);
        }
    }

    //接收一个构建器，有参构造
    private LoginAuthenticationProvider(LoginAuthenticationProviderBuilder builder) {
        super();
        setPasswordEncoder(builder.encoder);
        setHideUserNotFoundExceptions(builder.hideUserNotFoundExceptions);
        setUserDetailsService(builder.userDetailsService);
    }


    /**
     * 接收必要参数，创建一个构建器对象，静态方法
     *
     * @param userDetailsService    密码校验类
     * @param encoder   密码编译方法
     * @return  建造器
     */
    public static LoginAuthenticationProviderBuilder custom(UserDetailsService userDetailsService,PasswordEncoder encoder){
        return new LoginAuthenticationProviderBuilder(userDetailsService,encoder);
    }

    /**
     * 密码核对
     *
     * @param userDetails       获取到的user
     * @param authentication    生成的token
     * @throws AuthenticationException 抛出的坏凭证异常
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if (!getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
                this.logger.debug("Authentication failed: password does not match stored value");
                throw new PasswordException("密码错误");
            }
        }
    }
}
