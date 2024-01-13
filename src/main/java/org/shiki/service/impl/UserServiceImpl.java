package org.shiki.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.nimbusds.jose.JOSEException;
import org.shiki.entity.User;
import org.shiki.entity.dto.UserDTO;
import org.shiki.exception.PasswordException;
import org.shiki.mapper.UserMapper;
import org.shiki.service.RedisService;
import org.shiki.service.UserService;
import org.shiki.utils.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    RedisService redisService;


    @Override
    public Map<String, Object> login(String username, String password) throws JOSEException {
        User user = userMapper.queryByUsernameAndPassword(username, SecureUtil.md5(password));
        if (user == null) {
            throw new PasswordException();
        }
        UserDTO userDTO = new UserDTO(user);

        String token = JWTUtil.generateToken(Map.of(
                "id", user.getId(),
                "user", userDTO
        ));
        redisService.setHash("token", "token" + user.getId(), token);
        return Map.of(
                "token", token,
                "user", userDTO
        );
    }
}
