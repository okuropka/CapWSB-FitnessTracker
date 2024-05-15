package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.EmailUser;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
public class EmailUserMapper {

    static EmailUserDto toDto(User user) {
        return new EmailUserDto(user.getId(),
                           user.getEmail());
    }

}
