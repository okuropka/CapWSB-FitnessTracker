package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    private final SimpleUserMapper simpleUserMapper;

    @GetMapping     // http://localhost:8080/v1/users
    public List<SimpleUserDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(simpleUserMapper::toDto)
                .toList();
    }

    @GetMapping("/getBy/id/{id}")   // http://localhost:8080/v1/users/getBy/id/10
    public UserDto getUserById(@PathVariable("id") Long id) {
        if (userService.getUser(id).isPresent()) {
            return userMapper.toDto(userService.getUser(id).get());
        } else {
            throw new UserNotFoundException(id);
        }
    }

    // '{null, "imie", "nazwisko", "1999-09-09", "nowy.mail@poczta.ua}' "localhost:8080/v1/users/addUser"
    @PostMapping("/addUser")
    public User addUser(@RequestBody UserDto userDto) {
        System.out.println("User with e-mail: " + userDto.email() + " passed to the request");
        return userService.createUser(userMapper.toEntity(userDto));
    }

    @Transactional
    @DeleteMapping("/delete/{id}")  // http://localhost:8080/v1/users/delete/10
    public void deleteUser(@PathVariable("id") Long id) {
        if (userService.getUser(id).isPresent()) {
            userService.deleteUser(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @GetMapping("/search/email")    // http://localhost:8080/v1/users/search/email?emailFragment=ill
    public List<EmailUserDto> searchUserByEmailFragment(@RequestParam String emailFragment) {
        return userService.findUserByEmailFragment(emailFragment).stream().map(EmailUserMapper::toDto).toList();
    }

    @GetMapping("/search/olderThan")      // http://localhost:8080/v1/users/search/olderThan?age=48
    public List<UserDto> searchUserOlderThan(@RequestParam int age) {
        return userService.findUsersOlderThan(age).stream().map(userMapper::toDto).toList();
    }

    @PatchMapping("/update/byEmail/{id}")      // http://localhost:8080/v1/users/update/byEmail/3?email=odavis@domain2.com
    public void updateUser(@PathVariable Long id, @RequestParam String email ) {
        Optional<User> optionalUser = userService.getUser(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(email);
            userService.updateUser(user);
        }
        else { throw new UserNotFoundException(id); }
    }

}