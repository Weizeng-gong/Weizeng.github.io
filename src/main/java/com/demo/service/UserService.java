package com.demo.service;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(Long id) {
        return userMapper.findById(id);
    }

    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    public User addUser(User user) {
        userMapper.insert(user);
        return user;
    }

    public User updateUser(User user) {
        userMapper.update(user);
        return userMapper.findById(user.getId());
    }

    public boolean deleteUser(Long id) {
        User user = userMapper.findById(id);
        if (user == null) {
            return false;
        }
        userMapper.deleteById(id);
        return true;
    }
}
