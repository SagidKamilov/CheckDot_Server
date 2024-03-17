package com.cityfine.checkdot_server.service;

import java.util.List;

import com.cityfine.checkdot_server.dto.RequestClaimDTO;
import com.cityfine.checkdot_server.dto.ResponseClaimDTO;
import com.cityfine.checkdot_server.entity.Claim;

public interface ClaimService {
    public ResponseClaimDTO createClaim(Long userId, RequestClaimDTO requestClaimDTO);
    ResponseClaimDTO getClaimById(Long claimId);
    List<ResponseClaimDTO> getClaimListByUserId(Long userId);
    List<ResponseClaimDTO> getAllClaimByRating();
    ResponseClaimDTO upRatingClaim(Long claimId);
    ResponseClaimDTO downRatingClaim(Long claimId);
    ResponseClaimDTO updateClaim(Long claimId, RequestClaimDTO requestClaimDTO);
    void deleteClaim(Long claimId);
}
