package com.wynk.entities.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Response {
    private String status;
    private String message;
    private int code;
}
