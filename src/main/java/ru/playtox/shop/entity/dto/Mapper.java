package ru.playtox.shop.entity.dto;

import org.springframework.stereotype.Component;
import ru.playtox.shop.entity.User;
import ru.playtox.shop.entity.dto.UserDTO;

@Component
public class Mapper {

    public UserDTO fromUser(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getRole()
        );
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(user.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }
}
