package com.demo.springboot.security.config;

import com.demo.springboot.security.component.JwtAuthenticationTokenFilter;
import com.demo.springboot.security.component.RestAuthenticationEntryPoint;
import com.demo.springboot.security.component.RestfulAccessDeniedHandler;
import com.demo.springboot.security.util.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommonSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }
//
//    @Bean
//    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
//        return new DynamicAccessDecisionManager();
//    }
//
//    @Bean
//    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
//        return new DynamicSecurityMetadataSource();
//    }
//
//    @Bean
//    public DynamicSecurityFilter dynamicSecurityFilter(){
//        return new DynamicSecurityFilter();
//    }
}
