package org.shiki.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import org.shiki.service.RedisService;
import org.shiki.utils.JWTUtil;
import org.shiki.utils.ResData;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class LoginFilter extends OncePerRequestFilter {
    private final String[] freeURI = {"/user/login", "/book/queryAll"};
    @Resource
    RedisService redisService;


    private void handleUnlogin(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(ResData.failed(1002, "未登录")));
    }


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String requestURI = request.getRequestURI();
        if (StrUtil.containsAny(requestURI, freeURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("token");
        if (token == null || !JWTUtil.verifyToken(token)) {
            handleUnlogin(response);
            return;
        }


        Map<String, Object> userInfo = JWTUtil.parseToken(token);
        Object redisToken = redisService.getHash("token", "token" + userInfo.get("id"));
        if (redisToken == null || !redisToken.toString().equals(token)) {
            handleUnlogin(response);
            return;
        }

        //给token续期
        redisService.setHash("token", "token" + userInfo.get("id"), token);

        filterChain.doFilter(request, response);
    }
}
