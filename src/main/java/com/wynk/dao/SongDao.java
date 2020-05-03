package com.wynk.dao;

import com.google.common.collect.Sets;
import com.wynk.datastore.InMemoryDataStore;
import com.wynk.entities.db.SongDbEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SongDao {

    public SongDbEntity createSong(final String name,
                                   final Set<String> artists,
                                   final int rank) {
        final SongDbEntity songDbEntity = new SongDbEntity(name, name, artists, rank);
        InMemoryDataStore.SONG_MAP.put(name, songDbEntity);
        return songDbEntity;
    }

    public Set<SongDbEntity> getAll() {
        return Sets.newHashSet(InMemoryDataStore.SONG_MAP.values());
    }

    public void updateRank(final Set<String> ids,
                           final int order) {
        for (final String id : ids) {
            final SongDbEntity songDbEntity = InMemoryDataStore.SONG_MAP.get(id);
            songDbEntity.setRank(songDbEntity.getRank() + order);
        }
    }
}
