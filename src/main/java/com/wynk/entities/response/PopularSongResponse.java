package com.wynk.entities.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularSongResponse extends Response {

    private final String song;

    public PopularSongResponse(final String song) {
        super(null, null, 200);
        this.song = song;
    }
}
