package com.wynk.entities.db;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ArtistDbEntity {
    private String id;
    private String name;
    // Set of song id's.
    private Set<String> songs;
    // Set of user id's.
    private Set<String> followers;
}
