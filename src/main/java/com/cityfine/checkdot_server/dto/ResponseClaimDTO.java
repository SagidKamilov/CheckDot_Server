package com.cityfine.checkdot_server.dto;

import com.cityfine.checkdot_server.entity.Claim;
import lombok.Data;

@Data
public class ResponseClaimDTO {
    private Long claimId;
    private Long userId;
    private String heading;
    private String description;
    private String path_image;
    private String address;
    private int rating;
}
