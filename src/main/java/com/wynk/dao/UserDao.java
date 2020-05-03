package com.wynk.dao;

import com.wynk.datastore.InMemoryDataStore;
import com.wynk.entities.db.UserDbEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserDao {

    public UserDbEntity getById(final String id) {
        return InMemoryDataStore.USER_DB_ENTITY_MAP.get(id);
    }

    public void addFollows(final String id,
                           final Set<String> artists) {
        final UserDbEntity userDbEntity = getById(id);
        userDbEntity.getFollows().addAll(artists);
    }

    public void removeFollows(final String id,
                              final Set<String> artists) {
        final UserDbEntity userDbEntity = getById(id);
        userDbEntity.getFollows().remove(artists);
    }
}
