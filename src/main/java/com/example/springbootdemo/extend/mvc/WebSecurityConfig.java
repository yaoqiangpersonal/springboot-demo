package com.example.springbootdemo.extend.mvc;


import com.example.springbootdemo.extend.security.LoginAuthenticationProvider;
import com.example.springbootdemo.extend.security.MyPasswordEncoder;
import com.example.springbootdemo.extend.security.MyUsernamePasswordAuthenticationFilter;
import com.example.springbootdemo.extend.security.UrlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;


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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UrlUserService urlUserService;
    @Autowired
    private SessionRegistry sessionRegistry;

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
                .addFilter(new MyUsernamePasswordAuthenticationFilter())
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry)
                .and()
                .and()
                .httpBasic()
                .and()
                //通过formlogin方法登录，并设置登录url
                .formLogin().loginPage("/login")
                //指定登录成功后跳转到/index页面
                .defaultSuccessUrl("/user/success")
                //指定登录失败后跳转到/login?error页面
                //.failureUrl("/login")
                .permitAll()
                .and()
                //开启cookie储存用户信息，并设置有效期为14天，指定cookie中的密钥
                .rememberMe().tokenValiditySeconds(1209600).key("mykey")
                .and()
                .logout()
                //指定登出的url
                .logoutUrl("/logout")
                .permitAll();
    }

    /**
     * 配置校验方式 和 密码策略 关闭异常处理
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(
                LoginAuthenticationProvider
                        .custom(urlUserService,new MyPasswordEncoder())
                        .setHideUserNotFoundExceptions(false)
                        .build()
        );
    }

    @Bean
    public SessionRegistry getSessionRegistry(){
        SessionRegistry sessionRegistry=new SessionRegistryImpl();
        return sessionRegistry;
    }
}