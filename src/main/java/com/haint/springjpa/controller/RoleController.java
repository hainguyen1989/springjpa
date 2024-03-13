package com.haint.springjpa.controller;

import com.haint.springjpa.entity.Role;
import com.haint.springjpa.entity.User;
import com.haint.springjpa.service.RoleService;
import com.haint.springjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Role> findAll(){
        return roleService.findAll();
    }

    @GetMapping("/{id}/users")
    public List<User> findUserByRoleId(@PathVariable("id") Long id){
        return userService.findByRoleId(id);
    }
}
