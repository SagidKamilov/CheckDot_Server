package com.cityfine.checkdot_server.service.impl;

import com.cityfine.checkdot_server.exceptions.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cityfine.checkdot_server.dto.ResponseUserDTO;
import com.cityfine.checkdot_server.entity.User;
import com.cityfine.checkdot_server.exceptions.NotFoundException;
import com.cityfine.checkdot_server.repository.UserRepository;
import com.cityfine.checkdot_server.service.UserService;
import com.cityfine.checkdot_server.dto.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private ConverterDTO convertToDTO;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ConverterDTO convertToDTO) {
        this.userRepository = userRepository;
        this.convertToDTO = convertToDTO;
    }


    @Override
    public ResponseUserDTO createUser(RequestUserDTO requestUserDTO) {
        if (userRepository.existsByName(requestUserDTO.getName())) {
            throw new UserExistsException("Пользователь с таким логином уже существует");
        }

        User newUser = convertToDTO.ConverterToEntityUserRequest(requestUserDTO);
        User savedUser = userRepository.save(newUser);
        return convertToDTO.ConverterToDTOUserResponse(savedUser);
    }

    @Override
    public ResponseUserDTO existsByUser(RequestUserDTO requestUserDTO) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(
                requestUserDTO.getName(), requestUserDTO.getPassword());
        if (userOptional.isPresent()){
            return convertToDTO.ConverterToDTOUserResponse(userOptional.get());
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public User getUserByID(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new NotFoundException();
        }
    }

    public List<ResponseUserDTO> getAllUsers(){
        List<User> allUsers = userRepository.findAll();
        List<ResponseUserDTO> allUsersDTO = new ArrayList<>();
        for (User user : allUsers){
            ResponseUserDTO dto = convertToDTO.ConverterToDTOUserResponse(user);
            allUsersDTO.add(dto);
        }
        return allUsersDTO;
    }
}
