package com.example.springbootdemo.extend.mvc;


import com.example.springbootdemo.extend.security.*;
import com.example.springbootdemo.extend.security.Handler.MyAuthenticationFailureHandler;
import com.example.springbootdemo.extend.security.Handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


/**
 * <Description> <br>
 *
 * @author henley<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2017年1月13日 <br>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UrlUserService urlUserService;
    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private DataSource dataSource;

    /**
     * 权限配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterBefore(customUsernamePasswordAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/check").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry)
                .and()
                .and()
                .httpBasic()
                .and()
                //通过formlogin方法登录，并设置登录url
                //.formLogin().loginPage("/login")
                //指定登录成功后跳转到/index页面
                //.defaultSuccessUrl("/user/success")
                //指定登录失败后跳转到/login?error页面
                //.failureUrl("/login")
                //.permitAll()
                //.and()
                //开启cookie储存用户信息，并设置有效期为14天，指定cookie中的密钥
                .rememberMe().tokenValiditySeconds(1209600).key("mykey")
                .and()
                .logout()
                //指定登出的url
                .logoutUrl("/logout")
                .permitAll();
    }

    private MyUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter();
        customUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        customUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
        customUsernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        customUsernamePasswordAuthenticationFilter.setRememberMeServices(rememberMeServices());
        return customUsernamePasswordAuthenticationFilter;
    }

    /**
     * rememberServices配置
     * @return
     */
    private RememberMeServices rememberMeServices(){
        JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
        // 此处需要设置数据源，否则无法从数据库查询验证信息
        rememberMeTokenRepository.setDataSource(dataSource);
        // 此处的 key 可以为任意非空值(null 或 "")，单必须和起前面
        // rememberMeServices(RememberMeServices rememberMeServices).key(key)的值相同
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new MyRememberMeServices("INTERNAL_SECRET_KEY", urlUserService, rememberMeTokenRepository);
        // 该参数不是必须的，默认值为 "remember-me", 但如果设置必须和页面复选框的 name 一致
        rememberMeServices.setParameter("rememberMe");

        return rememberMeServices;
    }

    /**
     * 配置校验方式 和 密码策略 关闭异常处理
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(LoginAuthenticationProvider
                .custom(urlUserService,new MyPasswordEncoder())
                .setHideUserNotFoundExceptions(false)
                .build());
    }


    @Bean
    public SessionRegistry getSessionRegistry(){
        SessionRegistry sessionRegistry=new SessionRegistryImpl();
        return sessionRegistry;
    }
}