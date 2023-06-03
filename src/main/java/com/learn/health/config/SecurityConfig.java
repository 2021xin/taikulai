package com.learn.health.config;

import com.learn.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @Data 2022/11/26
 * @Time 11:04
 * @Author Yan Taixin
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private MyFilter filter;
    @Autowired
    private MyAccessDecisionManager accessDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager);
                        o.setSecurityMetadataSource(filter);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/pages/index.html")
                .permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/img/**","/plugins/**","/login.html","/register/**");
    }
}
