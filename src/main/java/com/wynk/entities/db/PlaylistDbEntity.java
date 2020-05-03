package com.wynk.entities.db;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class PlaylistDbEntity {
    private String id;
    private String userId;
    private Set<String> songIds;
}
