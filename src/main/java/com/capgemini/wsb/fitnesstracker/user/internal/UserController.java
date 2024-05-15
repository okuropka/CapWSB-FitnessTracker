package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
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

    //@GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }
    @GetMapping
    public List<SimpleUserDto> getAllSimpleUsers() {
        return userService.findAllSimpleUsers()
                .stream()
                .map(simpleUserMapper::toDto)
                .toList();
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        if (userService.getUser(id).isPresent()) { return userMapper.toDto(userService.getUser(id).get()); }
        else { throw new UserNotFoundException(id); }
    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto) {
        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");
        return userService.createUser(userMapper.toEntity(userDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        if (userService.getUser(id).isPresent()) { userService.deleteUser(userService.getUser(id).get()); }
        else { throw new UserNotFoundException(id); }
    }

    @GetMapping("/search/email")
    public List<EmailUserDto> searchUserByEmailFragment(@RequestParam String emailFragment) {
        return userService.findUserByEmailFragment(emailFragment).stream().map(EmailUserMapper::toDto).toList();
    }

    @GetMapping("/search/age")
    public List<UserDto> searchUserOlderThan(@RequestParam int age) {
        return userService.findUsersOlderThan(age).stream().map(userMapper::toDto).toList();
    }

    /*
    @PatchMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestParam String fieldName,
            @RequestParam String fieldValue
    ) {
        Optional<User> optionalUser = userService.getUser(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            switch (fieldName) {
                case "firstName":
                    user.setFirstName(fieldValue);
                    break;
                case "lastName":
                    user.setLastName(fieldValue);
                    break;
                case "email":
                    user.setEmail(fieldValue);
                    break;
                default:
                    return ResponseEntity.badRequest().build();
            }

            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok().body(userMapper.toDto(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     */
}