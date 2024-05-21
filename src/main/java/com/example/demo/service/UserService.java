package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    private final Map<UUID, User> users =
            new HashMap<>();

    public User findUser(UUID id) {
        return this.users.get(id);
    }

    public void addUser(User user) {
        this.users.put(user.getId(), user);
    }

    public int countUser() {
        return this.users.size();
    }

    public List<User> getUserList() {
        return this.users.values().stream().toList();
    }

    public User deleteUser(UUID id) {
        User user = this.users.get(id);
        this.users.remove(id);
        return user;
    }
}
