package com.juniorjavadeveloper.restapidemo.service;

import com.juniorjavadeveloper.restapidemo.exception.DataExistException;
import com.juniorjavadeveloper.restapidemo.exception.UserNotFoundException;
import com.juniorjavadeveloper.restapidemo.model.User;
import com.juniorjavadeveloper.restapidemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        log.info("Creating new user in database");
        checkIfEmailExist(user.getEmail());
        checkIfUserNameExist(user.getUserName());
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        log.info("Retrieving user with id {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User with id {} not found", id);
                    throw new UserNotFoundException("User not found");
                });
    }

    public List<User> findAllUsers() {
        log.info("Retrieving user list");
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        log.info("Deleting user with id {}", id);
        if (!userRepository.existsById(id)) {
            log.warn("User with id {} not found", id);
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    private void checkIfEmailExist(String email) {
        if (userRepository.existsByEmailIgnoreCase(email)) {
            log.warn("User with email {} already exist", email);
            throw new DataExistException("User with this email already exist.");
        }
    }

    private void checkIfUserNameExist(String userName) {
        if (userRepository.existsByUserNameIgnoreCase(userName)) {
            log.warn("User with username {} already exist", userName);
            throw new DataExistException("This username isn't available. Please try another.");
        }
    }
}
