package com.cityfine.checkdot_server.service;

import com.cityfine.checkdot_server.dto.RequestUserDTO;
import com.cityfine.checkdot_server.dto.ResponseUserDTO;
import com.cityfine.checkdot_server.entity.User;

public interface UserService {

    ResponseUserDTO createUser(RequestUserDTO requestUserDTO);

    ResponseUserDTO existsByUser(RequestUserDTO requestUserDTO);

    User getUserByID(Long userId);
}
