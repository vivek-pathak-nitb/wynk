package com.wynk.entities.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ArtistDbEntity {
    private String id;
    private String name;
    // Set of song id's.
    private Set<String> songs;
    // Set of user id's.
    private Set<String> followers;
}
