package com.cityfine.checkdot_server.service.impl;

import com.cityfine.checkdot_server.dto.ConverterDTO;
import com.cityfine.checkdot_server.dto.RequestClaimDTO;
import com.cityfine.checkdot_server.dto.ResponseClaimDTO;
import com.cityfine.checkdot_server.entity.User;
import com.cityfine.checkdot_server.exceptions.NotFoundException;
import com.cityfine.checkdot_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cityfine.checkdot_server.repository.ClaimRepository;
import com.cityfine.checkdot_server.entity.Claim;
import com.cityfine.checkdot_server.service.ClaimService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CheckDotServiceImpl implements ClaimService {

    @Autowired
    UserService userService;
    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    ConverterDTO convertToDTO;

    @Autowired
    public CheckDotServiceImpl(ClaimRepository claimRepository, ConverterDTO convertToDTO, UserService userService) {
        this.claimRepository = claimRepository;
        this.convertToDTO = convertToDTO;
        this.userService = userService;
    }

    @Transactional
    @Override
    public ResponseClaimDTO createClaim(Long userId, RequestClaimDTO requestClaimDTO) {
        User findedUser = userService.getUserByID(userId);
        if (findedUser != null){
            Claim savedClaim = claimRepository.save(convertToDTO.ConverterToEntityClaimRequest(requestClaimDTO, findedUser));
            return convertToDTO.ConverterToDTOClaimResponse(savedClaim);
        } else {
            throw new NotFoundException();
        }

    }

    @Transactional(readOnly = true)
    @Override
    public ResponseClaimDTO getClaimById(Long claimId) {
        Optional<Claim> foundClaim = this.claimRepository.findById(claimId);

        if (foundClaim.isPresent()) {
            Claim claim = foundClaim.get();
            return convertToDTO.ConverterToDTOClaimResponse(claim);
        } else {
            throw new NotFoundException();
        }
    }

    @Transactional
    @Override
    public List<ResponseClaimDTO> getClaimListByUserId(Long userId) {
        List<Claim> foundClaimList = claimRepository.findAllByUserUserId(userId);
        List<ResponseClaimDTO> dtoList = new ArrayList<>();
        for (Claim claim : foundClaimList) {
            ResponseClaimDTO dto = convertToDTO.ConverterToDTOClaimResponse(claim);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseClaimDTO> getAllClaimByRating() {
        List<Claim> claimList = claimRepository.findAllByOrderByRatingDesc();
        List<ResponseClaimDTO> dtoList = new ArrayList<>();
        for (Claim claim : claimList) {
            ResponseClaimDTO dto = convertToDTO.ConverterToDTOClaimResponse(claim);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    @Override
    public ResponseClaimDTO upRatingClaim(Long claimId){
        Claim foundClaim = claimRepository.findById(claimId).orElseThrow(NotFoundException::new);
        foundClaim.setRating(foundClaim.getRating()+1);
        claimRepository.save(foundClaim);
        return convertToDTO.ConverterToDTOClaimResponse(foundClaim);
    }

    @Transactional
    @Override
    public ResponseClaimDTO downRatingClaim(Long claimId){
        Claim foundClaim = claimRepository.findById(claimId).orElseThrow(NotFoundException::new);
        foundClaim.setRating(foundClaim.getRating()-1);
        claimRepository.save(foundClaim);
        return convertToDTO.ConverterToDTOClaimResponse(foundClaim);
    }

    @Transactional
    @Override
    public ResponseClaimDTO updateClaim(Long claimId, RequestClaimDTO requestClaimDTO) {
        Optional<Claim> optionalClaim = claimRepository.findById(claimId);
        if (optionalClaim.isPresent()) {
            Claim foundClaim = optionalClaim.get();
            foundClaim.setHeading(requestClaimDTO.getHeading());
            foundClaim.setDescription(requestClaimDTO.getDescription());
            foundClaim.setAddress(requestClaimDTO.getAddress());
            Claim newClaim = claimRepository.save(foundClaim);
            return convertToDTO.ConverterToDTOClaimResponse(newClaim);
        } else {
            throw new NotFoundException();
        }
    }

    @Transactional
    @Override
    public void deleteClaim(Long claimId) {
        Optional<Claim> optionalClaim = claimRepository.findById(claimId);
        if (optionalClaim.isPresent()) {
            claimRepository.deleteById(claimId);
        } else {
            throw new NotFoundException();
        }
    }
}
