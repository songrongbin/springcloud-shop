package com.bins.springcloud.shop.user.filter;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.user.security.JwtTokenUtil;
import com.bins.springcloud.shop.user.service.UserService;
import com.bins.springcloud.shop.user.vo.LoginVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JwtFilter extends GenericFilterBean {

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String jwtToken = req.getHeader("token");
        String userId = req.getHeader("userId");
        Claims claims;
        String username;
        try {
//            claims = JwtTokenUtil.getClaimsFromToken(jwtToken);
//            if (claims == null) {
//                return;
//            }
            username = JwtTokenUtil.getUsername(jwtToken);
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
            username = claims.getSubject();
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (userDetails != null) {
                //session未过期，比对时间戳是否一致，是则重新颁发token
                /* if (isSameTimestampToken(username, e.getClaims())){
                    userTokenManager.awardAccessToken(userDetails,true);
                    //直接设置响应码为201,直接返回
                    return;
                }else{
                    //时间戳不一致.无效token,无法刷新token,响应码401,前端跳转登录页
                    ResponseUtil.out(HttpStatus.UNAUTHORIZED.value(),"");
                    return;
                }*/
                servletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = servletResponse.getWriter();
                ResultVo<LoginVo> result = new ResultVo<LoginVo>();
                result.isFail("token过期!");
                out.write(new ObjectMapper().writeValueAsString(result));
                out.flush();
                out.close();
                return;
            } else {
                filterChain.doFilter(req, servletResponse);
                return;
            }
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(JwtTokenUtil.getAuthorities(jwtToken));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(req, servletResponse);
    }

//    private boolean isSameTimestampToken(String username, Claims claims){
//        Long timestamp = userService.getTokenTimestamp(username);
//        Long jwtTimestamp = (Long) claims.get(SecurityConstants.TIME_STAMP);
//        return timestamp.equals(jwtTimestamp);
//    }
}
