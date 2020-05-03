package com.wynk.utilities;

import com.wynk.entities.response.Response;
import org.springframework.http.HttpStatus;

public class ControllerUtility {

    public static Response getBadRequestResponse() {
        return new Response("failed", "Invalid parameters", HttpStatus.BAD_REQUEST.value());
    }
}
