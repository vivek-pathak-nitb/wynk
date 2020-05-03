package com.wynk.entities.response;

public class FollowerCountResponse extends Response {

    private String artist;
    private int count;

    public FollowerCountResponse(final String artist,
                                 final int count) {
        super(null, null, 200);
        this.artist = artist;
        this.count = count;
    }
}
