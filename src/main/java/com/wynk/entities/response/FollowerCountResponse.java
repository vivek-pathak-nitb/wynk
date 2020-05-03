package com.wynk.entities.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowerCountResponse {
    private String artist;
    private String count;
}
