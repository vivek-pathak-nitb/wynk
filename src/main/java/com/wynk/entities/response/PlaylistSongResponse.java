package com.wynk.entities.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class PlaylistSongResponse {
    private Set<String> songs;
}
