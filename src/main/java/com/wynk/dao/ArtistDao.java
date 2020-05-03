package com.wynk.dao;

import com.google.common.collect.Sets;
import com.wynk.datastore.InMemoryDataStore;
import com.wynk.entities.db.ArtistDbEntity;

import java.util.HashSet;
import java.util.Set;

public class ArtistDao {

    public ArtistDbEntity getById(final String id) {
        return InMemoryDataStore.ARTIST_DB_ENTITY_MAP.get(id);
    }

    public Set<ArtistDbEntity> getByIds(final Set<String> ids) {
        final Set<ArtistDbEntity> artistDbEntities = new HashSet<>();
        for (final String id : ids) {
            artistDbEntities.add(getById(id));
        }
        return artistDbEntities;
    }

    public Set<ArtistDbEntity> getAll() {
        return Sets.newHashSet(InMemoryDataStore.ARTIST_DB_ENTITY_MAP.values());
    }
}
