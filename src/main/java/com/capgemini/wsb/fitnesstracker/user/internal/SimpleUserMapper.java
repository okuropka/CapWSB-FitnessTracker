package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.SimpleUser;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class SimpleUserMapper {

    SimpleUserDto toDto(User user) {
        return new SimpleUserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName());
    }

}
