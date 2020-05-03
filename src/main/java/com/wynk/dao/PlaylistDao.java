package com.wynk.dao;

import com.wynk.datastore.InMemoryDataStore;
import com.wynk.entities.db.PlaylistDbEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PlaylistDao {

    public PlaylistDbEntity getById(final String id) {
        return InMemoryDataStore.PLAYLIST_DB_ENTITY_MAP.get(id);
    }

    public void addSong(final String songId,
                        final Set<String> playlists) {
        for (final String playlist : playlists) {
            getById(playlist).getSongIds().add(songId);
        }
    }

    public void removeSongs(final String id,
                            final Set<String> songs) {
        getById(id).getSongIds().removeAll(songs);
    }

    public void addSongs(final String id,
                         final Set<String> songs) {
        getById(id).getSongIds().addAll(songs);
    }
}
