package com.cityfine.checkdot_server.dto;

import lombok.Data;

@Data
public class RequestUserDTO {
    private Long userId;
    private String name;
    private String password;
}
