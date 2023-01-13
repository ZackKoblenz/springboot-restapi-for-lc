package com.dogpark.app.services;

import com.dogpark.app.exceptions.UserNotFoundException;
import com.dogpark.app.model.User;
import com.dogpark.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user) {
        user.setUuid(UUID.randomUUID());
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public User findUserByName(String name) {
        return userRepo.findUserByName(name)
                .orElseThrow(() -> new UserNotFoundException("User by name " + name + " was not found"));
    }

    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }
}
