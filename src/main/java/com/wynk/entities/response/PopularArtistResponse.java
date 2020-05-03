package com.wynk.entities.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularArtistResponse extends Response {

    private final String artist;

    public PopularArtistResponse(final String artist) {
        super(null, null, 200);
        this.artist = artist;
    }
}
