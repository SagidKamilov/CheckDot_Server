package com.cityfine.checkdot_server.controller;

import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cityfine.checkdot_server.dto.ResponseUserDTO;
import com.cityfine.checkdot_server.dto.RequestUserDTO;
import com.cityfine.checkdot_server.service.impl.UserServiceImpl;
import com.cityfine.checkdot_server.exceptions.UserExistsException;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity<ResponseUserDTO> createUser(@RequestBody RequestUserDTO requestUserDTO){
        try{
            ResponseUserDTO responseUserDTO = userService.createUser(requestUserDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseUserDTO);
        } catch (UserExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("check")
    public ResponseEntity<ResponseUserDTO> checkUser(@RequestBody RequestUserDTO requestUserDTO){
        ResponseUserDTO responseUserDTO = userService.existsByUser(requestUserDTO);
        if (responseUserDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseUserDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("getall")
    public ResponseEntity<List<ResponseUserDTO>> getAllUsers(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
