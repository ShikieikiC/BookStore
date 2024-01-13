package org.shiki.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.ParseException;

public class UserContext {
    public static Integer getUserId() throws ParseException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Math.toIntExact((Long) JWTUtil.parseToken(requestAttributes.getRequest().getHeader("token")).get("id"));
    }
}
