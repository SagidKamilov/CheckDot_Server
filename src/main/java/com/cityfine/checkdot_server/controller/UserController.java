package com.cityfine.checkdot_server.controller;

import com.cityfine.checkdot_server.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cityfine.checkdot_server.dto.ResponseUserDTO;
import com.cityfine.checkdot_server.dto.RequestUserDTO;
import com.cityfine.checkdot_server.service.impl.UserServiceImpl;
import com.cityfine.checkdot_server.exceptions.UserExistsException;

import java.util.List;
/**
 * Класс-контроллер для управления пользователями.
 */
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserServiceImpl userService;

    /**
     * Конструктор класса UserController.
     * @param userService сервис для работы с пользователями
     */
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Метод для создания нового пользователя.
     * @param requestUserDTO объект, содержащий данные нового пользователя
     * @return ResponseEntity с данными созданного пользователя и статусом 201 CREATED,
     *         или статусом 409 CONFLICT, если пользователь уже существует
     */
    @PostMapping("signup")
    public ResponseEntity<ResponseUserDTO> createUser(@RequestBody RequestUserDTO requestUserDTO){
        try{
            ResponseUserDTO responseUserDTO = userService.createUser(requestUserDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseUserDTO);
        } catch (UserExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    /**
     * Метод для проверки существования пользователя.
     * @param requestUserDTO объект, содержащий данные пользователя для проверки
     * @return ResponseEntity с данными пользователя и статусом 200 OK,
     *         или статусом 404 NOT FOUND, если пользователь не найден
     */
    @PostMapping("signin")
    public ResponseEntity<ResponseUserDTO> checkUser(@RequestBody RequestUserDTO requestUserDTO){
        ResponseUserDTO responseUserDTO = userService.existsByUser(requestUserDTO);
        if (responseUserDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseUserDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Метод для получения списка всех пользователей.
     * @return ResponseEntity с списком всех пользователей и статусом 200 OK,
     *         или статусом 500 INTERNAL SERVER ERROR, если произошла ошибка
     */
    @GetMapping("")
    public ResponseEntity<List<ResponseUserDTO>> getAllUsers(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Метод для удаления пользователя по его идентификатору.
     * @param userId идентификатор пользователя для удаления
     * @return ResponseEntity с пустым телом и статусом 200 OK,
     *         или статусом 404 NOT FOUND, если пользователь не найден
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<ResponseUserDTO> deleteUser(@PathVariable("userId") Long userId){
        try{
            userService.deleteUser(userId);
            return ResponseEntity.ok().body(null);
        } catch (NotFoundException notFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
