package com.kuaijiebao.springrestvue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findByUserId(Long id) { return userRepository.findByUserId(id); }
    public User save(User user) {
        return userRepository.save(user);
    }
    public void deleteByUserId(Long id) {
        userRepository.deleteByUserId(id);
    }


}