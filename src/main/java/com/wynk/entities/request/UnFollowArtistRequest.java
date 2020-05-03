package com.wynk.entities.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UnFollowArtistRequest {
    private String user;
    private List<String> artist;
}
