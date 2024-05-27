package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);

    void deleteUser(Long id);

    List<User> findUserByEmailFragment(String emailFragment);

    List<User> findUsersOlderThan(int age);

    User updateUser(User user);
}
