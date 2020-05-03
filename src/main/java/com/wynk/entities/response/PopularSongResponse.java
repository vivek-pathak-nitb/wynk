package com.wynk.entities.response;

public class PopularSongResponse extends Response {

    private final String song;

    public PopularSongResponse(final String song) {
        super(null, null, 200);
        this.song = song;
    }
}
