package com.cityfine.checkdot_server.dto;

import com.cityfine.checkdot_server.entity.Claim;
import com.cityfine.checkdot_server.entity.User;

public class ConverterDTO {
    public ResponseUserDTO ConverterToDTOUserResponse(User user) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        responseUserDTO.setUserId(user.getUserId());
        responseUserDTO.setName(user.getName());
        responseUserDTO.setPassword(user.getPassword());
        return responseUserDTO;
    }

    public User ConverterToEntityUserRequest(RequestUserDTO requestUserDTO){
        User user = new User();
        user.setName(requestUserDTO.getName());
        user.setPassword(requestUserDTO.getPassword());
        return user;
    }

    public ResponseClaimDTO ConverterToDTOClaimResponse(Claim claim){
        ResponseClaimDTO newDTO = new ResponseClaimDTO();
        newDTO.setHeading(claim.getHeading());
        newDTO.setDescription(claim.getDescription());
        newDTO.setAddress(claim.getAddress());
        newDTO.setPath_image(claim.getPath_image());
        newDTO.setClaimId(claim.getClaimId());
        newDTO.setRating(claim.getRating());
        if (claim.getUser() != null){
            newDTO.setUserId(claim.getUser().getUserId());
        }
        return newDTO;
    }

    public Claim ConverterToEntityClaimRequest(RequestClaimDTO requestClaimDTO, User user){
        Claim claim = new Claim();
        claim.setHeading(requestClaimDTO.getHeading());
        claim.setDescription(requestClaimDTO.getDescription());
        claim.setAddress(requestClaimDTO.getAddress());
        claim.setPath_image(requestClaimDTO.getPath_image());
        claim.setRating(requestClaimDTO.getRating());
        if (user != null) {
            claim.setUser(user);
        }
        return claim;
    }
}
