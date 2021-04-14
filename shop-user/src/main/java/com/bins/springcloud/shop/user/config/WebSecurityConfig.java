package com.bins.springcloud.shop.user.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.bins.springcloud.shop.user.filter.JwtFilter;
import com.bins.springcloud.shop.user.filter.JwtLoginFilter;
import com.bins.springcloud.shop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Resource
    private DataSource dataSource;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	// 配置springSecurity相关信息
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// 释放静态资源，指定资源拦截规则，指定自定义认证页面，指定退出认证配置，csrf配置
		http.authorizeRequests()
				.antMatchers("/login")
				.permitAll() // 释放上面的资源，不需要认证即可访问
				.anyRequest()
				.authenticated() // 表示其他请求资源需要认证之后才能访问
			.and()
				.rememberMe()//记住我功能
				.userDetailsService(userService)//设置用户业务
				.tokenRepository(persistentTokenRepository())//设置持久化token
				.tokenValiditySeconds(24 * 60 * 60)//记住登录1天(24小时 *60分钟 * 60秒)
			.and() // csrf配置（csrf功能默认开启，如果不需要关闭，下面都不用配置了）
				.addFilterBefore(new JwtLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
				.csrf()
				.disable(); // 关闭csrf防护
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
