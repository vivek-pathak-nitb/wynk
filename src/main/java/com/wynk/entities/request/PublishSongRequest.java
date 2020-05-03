package com.wynk.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PublishSongRequest {

    @JsonProperty("song")
    private String song;

    @JsonProperty("artists")
    private List<String> artists;
}
