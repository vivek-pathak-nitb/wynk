package com.wynk.entities.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PlaylistSongResponse extends Response {

    private final List<String> songs;

    public PlaylistSongResponse(final List<String> songs) {
        super(null, null, 200);
        this.songs = songs;
    }
}
