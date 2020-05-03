package com.wynk.dao;

import com.wynk.datastore.InMemoryDataStore;
import com.wynk.entities.db.PlaylistDbEntity;

public class PlaylistDao {

    public PlaylistDbEntity getById(final String id) {
        return InMemoryDataStore.PLAYLIST_DB_ENTITY_MAP.get(id);
    }
}
