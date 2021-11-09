package com.juniorjavadeveloper.restapidemo.service;

import com.juniorjavadeveloper.restapidemo.exception.DataExistException;
import com.juniorjavadeveloper.restapidemo.exception.UserNotFoundException;
import com.juniorjavadeveloper.restapidemo.model.User;
import com.juniorjavadeveloper.restapidemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        checkIfEmailExist(user.getEmail());
        checkIfUserNameExist(user.getUserName());
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    private void checkIfEmailExist(String email) {
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new DataExistException("User with this email already exist.");
        }
    }

    private void checkIfUserNameExist(String userName) {
        if (userRepository.existsByUserNameIgnoreCase(userName)) {
            throw new DataExistException("This username isn't available. Please try another.");
        }
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}
