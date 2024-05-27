package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    /**
     * Query searching users by part of email address.
     *
     * @param emailFragment
     * @return
     */
    @Query("select user from User user where lower(user.email) like lower(concat('%', :emailFragment, '%'))")
    List<User> findEmailUserByEmailFragment(@Param("emailFragment") String emailFragment);

    @Query("select user from User user where user.birthdate < :earliestBirthdate")
    List<User> findUsersOlderThan(@Param("earliestBirthdate") LocalDate earliestBirthdate);

    @Modifying
    @Query("delete from Training t where t.user.id = :userId")
    void deleteAllTrainingsByUserId(@Param("userId") Long userId);

}
