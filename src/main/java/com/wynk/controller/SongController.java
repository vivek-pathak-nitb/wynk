package com.wynk.controller;

import com.google.common.collect.Sets;
import com.wynk.dao.ArtistDao;
import com.wynk.dao.PlaylistDao;
import com.wynk.dao.SongDao;
import com.wynk.dao.UserDao;
import com.wynk.entities.db.ArtistDbEntity;
import com.wynk.entities.db.SongDbEntity;
import com.wynk.entities.request.PublishSongRequest;
import com.wynk.entities.response.PopularSongResponse;
import com.wynk.entities.response.Response;
import com.wynk.utilities.ControllerUtility;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/wynk/song")
public class SongController {

    private final SongDao songDao;
    private final ArtistDao artistDao;
    private final UserDao userDao;
    private final PlaylistDao playlistDao;

    public SongController(final SongDao songDao,
                          final ArtistDao artistDao,
                          final UserDao userDao,
                          final PlaylistDao playlistDao) {
        this.songDao = songDao;
        this.artistDao = artistDao;
        this.userDao = userDao;
        this.playlistDao = playlistDao;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/publish")
    @ResponseBody
    public Response publishSong(@RequestBody final PublishSongRequest publishSongRequest) {
        final String song = publishSongRequest.getSong();
        final List<String> artists = publishSongRequest.getArtists();
        if (song == null || song.isEmpty() || artists == null || artists.isEmpty()) {
            return ControllerUtility.getBadRequestResponse();
        }

        try {
            final Set<ArtistDbEntity> artistDbEntities = artistDao.getByIds(Sets.newHashSet(artists));
            final Set<String> followers = getFollowers(artistDbEntities);
            final SongDbEntity songDbEntity = songDao.createSong(song, Sets.newHashSet(artists), followers.size());
            updateArtist(artistDbEntities, songDbEntity);

            final Set<String> playlists = getFollowersPlaylist(followers);
            updateUserPlaylist(songDbEntity, playlists);

            return new Response("ok", "Song published against artist", HttpStatus.OK.value());
        } catch (final Exception ex) {
            return ControllerUtility.getBadRequestResponse();
        }
    }

    private void updateArtist(final Set<ArtistDbEntity> artistDbEntities,
                              final SongDbEntity songDbEntity) {
        for (final ArtistDbEntity artistDbEntity : artistDbEntities) {
            artistDbEntity.getSongs().add(songDbEntity.getId());
        }
    }

    private Set<String> getFollowers(final Set<ArtistDbEntity> artistDbEntities) {
        final Set<String> followers = Sets.newHashSet();
        for (final ArtistDbEntity artistDbEntity : artistDbEntities) {
            followers.addAll(artistDbEntity.getFollowers());
        }
        return followers;
    }

    private void updateUserPlaylist(final SongDbEntity songDbEntity,
                                    final Set<String> playlists) {
        for (final String playlist : playlists) {
            playlistDao.getById(playlist).getSongIds().add(songDbEntity.getId());
        }
    }

    private Set<String> getFollowersPlaylist(final Set<String> followers) {
        final Set<String> playlists = Sets.newHashSet();
        for (final String follower : followers) {
            playlists.addAll(userDao.getById(follower).getPlaylists());
        }
        return playlists;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/popular")
    @ResponseBody
    public Response getPopularSong() {
        try {
            final Set<SongDbEntity> songDbEntities = songDao.getAll();
            int max = Integer.MIN_VALUE;
            SongDbEntity res = null;
            for (final SongDbEntity songDbEntity : songDbEntities) {
                if (max < songDbEntity.getRank()) {
                    max = songDbEntity.getRank();
                    res = songDbEntity;
                }
            }

            if (res == null) {
                return ControllerUtility.getBadRequestResponse();
            }
            return new PopularSongResponse(res.getName());
        } catch (final Exception ex) {
            return ControllerUtility.getBadRequestResponse();
        }
    }
}
