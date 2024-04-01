package com.cityfine.checkdot_server.controller;

import com.cityfine.checkdot_server.dto.RequestClaimDTO;
import com.cityfine.checkdot_server.dto.ResponseClaimDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cityfine.checkdot_server.exceptions.NotFoundException;
import com.cityfine.checkdot_server.service.impl.CheckDotServiceImpl;
import com.cityfine.checkdot_server.service.impl.UserServiceImpl;

import java.util.List;

/**
 * Класс-контроллер для управления заявками.
 */
@RestController
@RequestMapping("api/v1/claim")
public class ClaimController {

    CheckDotServiceImpl checkDotService;
    UserServiceImpl userService;

    /**
     * Конструктор класса ClaimController.
     * @param checkDotService сервис для работы с заявками
     * @param userService сервис для работы с пользователями
     */
    public ClaimController(CheckDotServiceImpl checkDotService, UserServiceImpl userService){
        this.checkDotService = checkDotService;
        this.userService = userService;
    }

    /**
     * Метод для создания новой заявки.
     * @param userId идентификатор пользователя, создающего заявку
     * @param requestClaimDTO объект, содержащий данные новой заявки
     * @return ResponseEntity с данными созданной заявки и статусом 201 CREATED,
     *         или статусом 404 NOT FOUND, если пользователь не найден
     */
    @PostMapping("{userId}")
    public ResponseEntity<ResponseClaimDTO> createNewClaim(@PathVariable("userId") Long userId, @RequestBody RequestClaimDTO requestClaimDTO){
        try{
            ResponseClaimDTO createdResponseClaimDTO = checkDotService.createClaim(userId, requestClaimDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdResponseClaimDTO);
        } catch (NotFoundException notFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Метод для получения заявки по её идентификатору.
     * @param claimId идентификатор заявки
     * @return ResponseEntity с данными заявки и статусом 200 OK,
     *         или статусом 404 NOT FOUND, если заявка не найдена
     */
    @GetMapping("{claimId}")
    public ResponseEntity<ResponseClaimDTO> getClaimById(@PathVariable("claimId") Long claimId){
        try{
            ResponseClaimDTO foundClaim = checkDotService.getClaimById(claimId);
            return ResponseEntity.status(HttpStatus.OK).body(foundClaim);
        } catch (NotFoundException notFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Методы для изменения рейтинга заявки

    /**
     * Метод для увеличения рейтинга заявки.
     * @param claimId идентификатор заявки
     * @return ResponseEntity с данными обновленной заявки и статусом 200 OK,
     *         или статусом 404 NOT FOUND, если заявка не найдена
     */
    @PatchMapping("{claimId}/uprating")
    public ResponseEntity<ResponseClaimDTO> upRatingClaim(@PathVariable("claimId") Long claimId){
        try{
            ResponseClaimDTO newRatingClaim = checkDotService.upRatingClaim(claimId);
            return ResponseEntity.status(HttpStatus.OK).body(newRatingClaim);
        } catch (NotFoundException notFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Метод для уменьшения рейтинга заявки.
     * @param claimId идентификатор заявки
     * @return ResponseEntity с данными обновленной заявки и статусом 200 OK,
     *         или статусом 404 NOT FOUND, если заявка не найдена
     */
    @PatchMapping("{claimId}/downrating")
    public ResponseEntity<ResponseClaimDTO> downRatingClaim(@PathVariable("claimId") Long claimId){
        try{
            ResponseClaimDTO newRatingClaim = checkDotService.downRatingClaim(claimId);
            return ResponseEntity.status(HttpStatus.OK).body(newRatingClaim);
        } catch (NotFoundException notFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    /**
     * Метод для получения списка всех заявок, отсортированных по рейтингу.
     * @return ResponseEntity со списком всех заявок и статусом 200 OK,
     *         или статусом 500 INTERNAL SERVER ERROR, если произошла ошибка
     */
    @GetMapping("")
    public ResponseEntity<List<ResponseClaimDTO>> getAllClaimByRating() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(checkDotService.getAllClaimByRating());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Метод для получения списка всех заявок, созданных пользователем.
     * @param userId идентификатор пользователя
     * @return ResponseEntity со списком заявок пользователя и статусом 200 OK,
     *         или статусом 500 INTERNAL SERVER ERROR, если произошла ошибка
     */
    @GetMapping("{userId}")
    public ResponseEntity<List<ResponseClaimDTO>> getAllClaimByUser(@PathVariable("userId") Long userId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(checkDotService.getClaimListByUserId(userId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Метод для обновления заявки.
     * @param claimId идентификатор заявки
     * @param requestClaimDTO объект, содержащий новые данные для обновления заявки
     * @return ResponseEntity с обновленными данными заявки и статусом 200 OK,
     *         или статусом 404 NOT FOUND, если заявка не найдена
     */
    @PutMapping("{claimId}")
    public ResponseEntity<ResponseClaimDTO> updateClaim(@PathVariable("claimId") Long claimId, @RequestBody RequestClaimDTO requestClaimDTO){
        try{
            ResponseClaimDTO newClaim = checkDotService.updateClaim(claimId, requestClaimDTO);
            return ResponseEntity.status(HttpStatus.OK).body(newClaim);
        } catch (NotFoundException notFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Метод для удаления заявки по её идентификатору.
     * @param claimId идентификатор заявки для удаления
     * @return ResponseEntity с пустым телом и статусом 200 OK,
     *         или статусом 404 NOT FOUND, если заявка не найдена
     */
    @DeleteMapping("{claimId}")
    public ResponseEntity<ResponseClaimDTO> deleteClaim(@PathVariable("claimId") Long claimId){
        try{
            checkDotService.deleteClaim(claimId);
            return ResponseEntity.ok().body(null);
        } catch (NotFoundException notFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
