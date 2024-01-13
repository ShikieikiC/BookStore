package org.shiki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shiki.entity.User;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String mobile;
    private String nickname;

    public UserDTO(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
