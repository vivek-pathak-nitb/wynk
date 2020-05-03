package com.wynk.datastore;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.wynk.entities.db.ArtistDbEntity;
import com.wynk.entities.db.PlaylistDbEntity;
import com.wynk.entities.db.SongDbEntity;
import com.wynk.entities.db.UserDbEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This is our in-memory data store holding dummy values. This is get initialized at the application start up and is
 * consumed by respective dao.
 */
public class InMemoryDataStore {

    public static final Map<String, SongDbEntity> SONG_MAP = new HashMap<>();
    public static final Map<String, ArtistDbEntity> ARTIST_DB_ENTITY_MAP = new HashMap<>();
    public static final Map<String, PlaylistDbEntity> PLAYLIST_DB_ENTITY_MAP = new HashMap<>();
    public static final Map<String, UserDbEntity> USER_DB_ENTITY_MAP = new HashMap<>();

    private static final String USER_1 = "u1";
    private static final String USER_2 = "u2";
    private static final String USER_3 = "u3";
    private static final String ARTIST_1 = "a1";
    private static final String ARTIST_2 = "a2";
    private static final String SONG_1 = "s1";
    private static final String SONG_2 = "s2";
    private static final String SONG_3 = "s3";
    private static final String PLAY_LIST_1 = "p1";
    private static final String PLAY_LIST_2 = "p2";
    private static final String PLAY_LIST_3 = "p3";

    public static void createDummyData() {
        // Creating songs
        createSongs();

        // Creating artists
        final ArtistDbEntity artist1 = createArtist(ARTIST_1, ARTIST_1, Sets.newHashSet(SONG_1, SONG_3),
                Sets.newHashSet(USER_1, USER_2, USER_3));
        final ArtistDbEntity artist2 = createArtist(ARTIST_2, ARTIST_2, Sets.newHashSet(SONG_1, SONG_2, SONG_3),
                Sets.newHashSet(USER_2));
        ARTIST_DB_ENTITY_MAP.put(ARTIST_1, artist1);
        ARTIST_DB_ENTITY_MAP.put(ARTIST_2, artist2);

        // Create Playlist
        createPlaylist(artist1, artist2);

        // Creating user
        createUsers();
    }

    private static void createSongs() {
        final SongDbEntity song1 = createSong(SONG_1, SONG_1, Sets.newHashSet(ARTIST_1, ARTIST_2), 3);
        final SongDbEntity song2 = createSong(SONG_2, SONG_2, Sets.newHashSet(ARTIST_2), 1);
        final SongDbEntity song3 = createSong(SONG_3, SONG_3, Sets.newHashSet(ARTIST_1, ARTIST_2), 3);
        SONG_MAP.put(SONG_1, song1);
        SONG_MAP.put(SONG_2, song2);
        SONG_MAP.put(SONG_3, song3);
    }

    private static void createUsers() {
        final UserDbEntity user1 = createUser(USER_1, USER_1, Sets.newHashSet(ARTIST_1), Sets.newHashSet(PLAY_LIST_1));
        final UserDbEntity user2 = createUser(USER_2, USER_2, Sets.newHashSet(ARTIST_1, ARTIST_2), Sets.newHashSet(PLAY_LIST_2));
        final UserDbEntity user3 = createUser(USER_3, USER_3, Sets.newHashSet(ARTIST_1), Sets.newHashSet(PLAY_LIST_3));
        USER_DB_ENTITY_MAP.put(USER_1, user1);
        USER_DB_ENTITY_MAP.put(USER_2, user2);
        USER_DB_ENTITY_MAP.put(USER_3, user3);
    }

    private static void createPlaylist(final ArtistDbEntity artist1,
                                       final ArtistDbEntity artist2) {
        final PlaylistDbEntity playlist1 = new PlaylistDbEntity(PLAY_LIST_1, Sets.newHashSet(artist1.getSongs()));
        final PlaylistDbEntity playlist2 = new PlaylistDbEntity(PLAY_LIST_2,
                Sets.newHashSet(Iterables.concat(artist1.getSongs(), artist2.getSongs())));
        final PlaylistDbEntity playlist3 = new PlaylistDbEntity(PLAY_LIST_3, Sets.newHashSet(artist1.getSongs()));
        PLAYLIST_DB_ENTITY_MAP.put(PLAY_LIST_1, playlist1);
        PLAYLIST_DB_ENTITY_MAP.put(PLAY_LIST_2, playlist2);
        PLAYLIST_DB_ENTITY_MAP.put(PLAY_LIST_3, playlist3);
    }

    private static SongDbEntity createSong(final String id,
                                           final String name,
                                           final Set<String> artists,
                                           final int rank) {
        return new SongDbEntity(id, name, artists, rank);
    }

    private static ArtistDbEntity createArtist(String id,
                                               String name,
                                               Set<String> songs,
                                               Set<String> followers) {
        return new ArtistDbEntity(id, name, songs, followers);
    }

    private static UserDbEntity createUser(final String id,
                                           final String name,
                                           final Set<String> follows,
                                           final Set<String> playlists) {
        return new UserDbEntity(id, name, follows, playlists);
    }
}
