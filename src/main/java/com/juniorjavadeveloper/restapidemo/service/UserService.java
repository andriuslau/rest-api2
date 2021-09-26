package com.juniorjavadeveloper.restapidemo.service;

import com.juniorjavadeveloper.restapidemo.model.Gender;
import com.juniorjavadeveloper.restapidemo.model.User;
import com.juniorjavadeveloper.restapidemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(Long id, String firstName, String lastName,
                           Gender gender) {
        return userRepository.addUser(User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .build());
    }

    public User findUser(Long id) {
        return userRepository.getUserById(id);
    }

    public List<User> findUsers() {
        return userRepository.getUsers();
    }
}
