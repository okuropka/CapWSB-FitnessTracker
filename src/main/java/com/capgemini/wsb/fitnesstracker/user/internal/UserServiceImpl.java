package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.SimpleUser;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(final Long id) {
        log.info("Deleting User {}", userRepository.findById(id));
        userRepository.deleteAllTrainingsByUserId(id);
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<SimpleUser> findAllSimpleUsers() {
        List<User> temp = findAllUsers();
        return temp.stream().map(user -> new SimpleUser(user.getFirstName(), user.getLastName())).collect(Collectors.toList());
    }
    @Override
    public List<User> findUserByEmailFragment(String emailFragment) {
        return userRepository.findEmailUserByEmailFragment(emailFragment);
    }

    @Override
    public List<User> findUsersOlderThan(int age) {
        LocalDate maxBirthdate = LocalDate.now().minusYears(age);
        return userRepository.findUsersOlderThan(maxBirthdate);
    }

    @Override
    public User updateUser(User user){
        log.info("Updating User {}", user);
        return userRepository.save(user);
    }

}