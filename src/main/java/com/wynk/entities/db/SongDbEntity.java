package com.wynk.entities.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class SongDbEntity {
    private String id;
    private String name;
    private Set<String> artists;
    private int rank;
}
