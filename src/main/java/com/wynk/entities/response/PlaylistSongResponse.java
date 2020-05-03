package com.wynk.entities.response;

import java.util.Set;

public class PlaylistSongResponse extends Response {

    private final Set<String> songs;

    public PlaylistSongResponse(final Set<String> songs) {
        super(null, null, 200);
        this.songs = songs;
    }
}
