package com.wynk.dao;

import com.google.common.collect.Sets;
import com.wynk.datastore.InMemoryDataStore;
import com.wynk.entities.db.ArtistDbEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
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

    public void addSong(final Set<String> artists,
                        final String song) {
        final Set<ArtistDbEntity> artistDbEntities = getByIds(artists);
        for (final ArtistDbEntity artistDbEntity : artistDbEntities) {
            artistDbEntity.getSongs().add(song);
        }
    }

    public void addFollower(final Set<String> ids,
                            final String userId) {
        final Set<ArtistDbEntity> artistDbEntities = getByIds(ids);
        for (final ArtistDbEntity artistDbEntity : artistDbEntities) {
            artistDbEntity.getFollowers().add(userId);
        }
    }

    public void removeFollower(final Set<String> ids,
                               final String userId) {
        final Set<ArtistDbEntity> artistDbEntities = getByIds(ids);
        for (final ArtistDbEntity artistDbEntity : artistDbEntities) {
            artistDbEntity.getFollowers().remove(userId);
        }
    }
}
