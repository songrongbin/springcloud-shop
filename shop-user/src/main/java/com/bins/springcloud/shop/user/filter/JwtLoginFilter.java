package com.bins.springcloud.shop.user.filter;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.user.dto.LoginDto;
import com.bins.springcloud.shop.user.security.CustomUserDetails;
import com.bins.springcloud.shop.user.security.JwtTokenUtil;
import com.bins.springcloud.shop.user.vo.LoginVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException, ServletException {
        LoginDto user = new ObjectMapper().readValue(req.getInputStream(), LoginDto.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        String jwt = JwtTokenUtil.generateToken(authentication);
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("token", jwt);
        PrintWriter out = resp.getWriter();
        ResultVo<LoginVo> result = new ResultVo<LoginVo>();
        LoginVo vo = new LoginVo();
        vo.setToken(jwt);
        vo.setUserId(JwtTokenUtil.getUserId(jwt));
        result.isOk(vo);
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
        String tokenStr = JwtTokenUtil.TOKEN_PREFIX + jwt;
        resp.setHeader("token", tokenStr);
    }

    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse resp, AuthenticationException failed) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ResultVo<LoginVo> result = new ResultVo<LoginVo>();
        result.isFail("登录失败!");
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
