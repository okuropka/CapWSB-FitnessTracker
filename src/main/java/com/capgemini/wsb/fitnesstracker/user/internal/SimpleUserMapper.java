package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.SimpleUser;
import org.springframework.stereotype.Component;

@Component
class SimpleUserMapper {

    SimpleUserDto toDto(SimpleUser user) {
        return new SimpleUserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName());
    }

    SimpleUser toEntity(SimpleUserDto userDto) {
        return new SimpleUser(
                        userDto.firstName(),
                        userDto.lastName());
    }

}
