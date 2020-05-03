package com.wynk.entities.db;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class SongDbEntity {
    private String id;
    private String name;
    private Set<String> artists;
}
