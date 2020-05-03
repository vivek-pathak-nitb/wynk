package com.wynk.entities.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class PlaylistDbEntity {
    private String id;
    private Set<String> songIds;
}
