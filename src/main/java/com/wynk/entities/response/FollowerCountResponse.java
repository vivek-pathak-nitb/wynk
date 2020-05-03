package com.wynk.entities.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowerCountResponse extends Response {

    private final String artist;
    private final int count;

    public FollowerCountResponse(final String artist,
                                 final int count) {
        super(null, null, 200);
        this.artist = artist;
        this.count = count;
    }
}
