package com.cityfine.checkdot_server.dto;

import lombok.Data;

@Data
public class ResponseUserDTO {
    private Long userId;
    private String name;
    private String password;
}
