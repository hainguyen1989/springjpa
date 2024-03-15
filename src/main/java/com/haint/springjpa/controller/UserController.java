package com.haint.springjpa.controller;

import com.haint.springjpa.entity.RequestObj;
import com.haint.springjpa.entity.ResponseObj;
import com.haint.springjpa.entity.User;
import com.haint.springjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseObj<User>> findAll() {
        List<User> result = userService.findAll();
        ResponseObj.ResponseObjBuilder<User> builder = new ResponseObj.ResponseObjBuilder<>();
        builder.setStatusCode(Integer.toString(HttpStatus.OK.value()))
                .setData(result);
        return ResponseEntity.ok(builder.build());
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseObj<User>> find(@RequestBody RequestObj requestObj) {
        ResponseObj.ResponseObjBuilder<User> builder = new ResponseObj.ResponseObjBuilder<>();

        if (requestObj == null) {
            builder.setStatusCode(Integer.toString(HttpStatus.BAD_REQUEST.value()))
                    .setMessages(List.of("No parameters found!"))
                    .build();
        } else {
            if (StringUtils.hasLength(requestObj.getUsername())) {
                User result = userService.findByUsername(requestObj.getUsername());
                if (result == null) {
                    builder.setStatusCode(Integer.toString(HttpStatus.NOT_FOUND.value()))
                            .setMessages(List.of(String.format("No user [%s] found!", requestObj.getUsername())))
                            .build();
                } else {
                    builder.setStatusCode(Integer.toString(HttpStatus.OK.value()))
                            .setData(List.of(result))
                            .build();
                }
            } else {
                builder.setStatusCode(Integer.toString(HttpStatus.BAD_REQUEST.value()))
                        .setMessages(List.of("Parameter {username} not found!"))
                        .build();
            }
        }

        return ResponseEntity.ok(builder.build());
    }
}
