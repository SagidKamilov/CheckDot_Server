package com.cityfine.checkdot_server.dto;

import lombok.Data;

@Data
public class RequestClaimDTO {
    private Long userId;
    private String heading;
    private String description;
    private String path_image;
    private String address;
    private int rating;
}
