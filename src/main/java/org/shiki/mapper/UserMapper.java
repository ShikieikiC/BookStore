package org.shiki.mapper;

import org.shiki.entity.User;

public interface UserMapper {
    User queryByUsernameAndPassword(String username, String password);
}
