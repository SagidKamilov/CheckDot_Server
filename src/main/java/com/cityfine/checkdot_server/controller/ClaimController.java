package com.cityfine.checkdot_server.controller;

import com.cityfine.checkdot_server.dto.RequestClaimDTO;
import com.cityfine.checkdot_server.dto.ResponseClaimDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cityfine.checkdot_server.exceptions.NotFoundException;
import com.cityfine.checkdot_server.service.impl.CheckDotServiceImpl;
import com.cityfine.checkdot_server.service.impl.UserServiceImpl;
import com.cityfine.checkdot_server.entity.Claim;

import java.util.List;

@RestController
@RequestMapping("api/v1/claim")
public class ClaimController {

    CheckDotServiceImpl checkDotService;
    UserServiceImpl userService;

    public ClaimController(CheckDotServiceImpl checkDotService, UserServiceImpl userService){
        this.checkDotService = checkDotService;
        this.userService = userService;
    }

    @PostMapping("{userId}")
    public ResponseEntity<ResponseClaimDTO> createNewClaim(@PathVariable("userId") Long userId, @RequestBody RequestClaimDTO requestClaimDTO){
        try{
            ResponseClaimDTO createdResponseClaimDTO = checkDotService.createClaim(userId, requestClaimDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdResponseClaimDTO);
        } catch (NotFoundException notFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("{claimId}")
    public ResponseEntity<ResponseClaimDTO> getClaimById(@PathVariable("claimId") Long claimId){
        try{
            ResponseClaimDTO foundClaim = checkDotService.getClaimById(claimId);
            return ResponseEntity.status(HttpStatus.CREATED).body(foundClaim);
        } catch (NotFoundException notFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("{claimId}/uprating")
    public ResponseEntity<ResponseClaimDTO> upRatingClaim(@PathVariable("claimId") Long claimId){
        try{
            ResponseClaimDTO newRatingClaim = checkDotService.upRatingClaim(claimId);
            return ResponseEntity.status(HttpStatus.OK).body(newRatingClaim);
        } catch (NotFoundException notFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("{claimId}/downrating")
    public ResponseEntity<ResponseClaimDTO> downRatingClaim(@PathVariable("claimId") Long claimId){
        try{
            ResponseClaimDTO newRatingClaim = checkDotService.downRatingClaim(claimId);
            return ResponseEntity.status(HttpStatus.OK).body(newRatingClaim);
        } catch (NotFoundException notFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseClaimDTO>> getAllClaimByRating() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(checkDotService.getAllClaimByRating());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<ResponseClaimDTO>> getAllClaimByUser(@PathVariable("userId") Long userId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(checkDotService.getClaimListByUserId(userId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("{claimId}")
    public ResponseEntity<ResponseClaimDTO> updateClaim(@PathVariable("claimId") Long claimId, @RequestBody RequestClaimDTO requestClaimDTO){
        try{
            ResponseClaimDTO newClaim = checkDotService.updateClaim(claimId, requestClaimDTO);
            return ResponseEntity.status(HttpStatus.OK).body(newClaim);
        } catch (NotFoundException notFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

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
