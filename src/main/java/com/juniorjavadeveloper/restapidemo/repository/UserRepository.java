package com.juniorjavadeveloper.restapidemo.repository;

import com.juniorjavadeveloper.restapidemo.exception.UserNotFoundException;
import com.juniorjavadeveloper.restapidemo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    List<User> users = new ArrayList<>();

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id))
                .findFirst().orElseThrow(() ->
                        new UserNotFoundException("User not found"));
    }
}
