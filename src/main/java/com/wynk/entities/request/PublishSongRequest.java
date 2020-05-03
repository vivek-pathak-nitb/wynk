package com.wynk.entities.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PublishSongRequest {
    private String song;
    private List<String> artist;
}
