package com.haint.springjpa.service;

import com.haint.springjpa.db.UserRepository;
import com.haint.springjpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findByRoleId(Long roleId){
        return userRepository.findUserByRolesId(roleId);
    }
}
