package com.wynk.dao;

import com.wynk.datastore.InMemoryDataStore;
import com.wynk.entities.db.UserDbEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    public UserDbEntity getById(final String id) {
        return InMemoryDataStore.USER_DB_ENTITY_MAP.get(id);
    }
}
