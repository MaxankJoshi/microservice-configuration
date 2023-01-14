package com.user.service.services;

import com.user.service.entities.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public User updateUser(User user);
    public User getUserById(Integer userId);
    public List<User> getAllUsers();
    public void deleteUser(Integer userId);
}
