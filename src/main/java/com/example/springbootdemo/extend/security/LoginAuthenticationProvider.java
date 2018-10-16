package com.example.springbootdemo.extend.security;

import javafx.util.Builder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Auther: yq
 * @Date: 2018-09-07 11:06
 * @Description:
 */
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

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

        @Override
        public LoginAuthenticationProvider build() {
            return new LoginAuthenticationProvider(this);
        }
    }

    private LoginAuthenticationProvider(LoginAuthenticationProviderBuilder builder) {
        super();
        setPasswordEncoder(builder.encoder);
        setHideUserNotFoundExceptions(builder.hideUserNotFoundExceptions);
        setUserDetailsService(builder.userDetailsService);
    }

    public static LoginAuthenticationProviderBuilder custom(UserDetailsService userDetailsService,PasswordEncoder encoder){
        return new LoginAuthenticationProviderBuilder(userDetailsService,encoder);
    }

}
