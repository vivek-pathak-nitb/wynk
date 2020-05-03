package com.wynk.controller;

import com.google.common.base.Joiner;
import com.wynk.dao.ArtistDao;
import com.wynk.dao.PlaylistDao;
import com.wynk.dao.UserDao;
import com.wynk.entities.db.ArtistDbEntity;
import com.wynk.entities.db.UserDbEntity;
import com.wynk.entities.request.FollowArtistRequest;
import com.wynk.entities.request.UnFollowArtistRequest;
import com.wynk.entities.response.FollowerCountResponse;
import com.wynk.entities.response.PopularArtistResponse;
import com.wynk.entities.response.Response;
import com.wynk.utilities.ControllerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller for artist resource.
 */
@Controller
@RequestMapping("/wynk/artist")
public class ArtistController {

    private static final String FOLLOW_ARTIST_FORMAT = "%s started following %s";
    private static final String UN_FOLLOW_ARTIST_FORMAT = "%s stopped following %s";

    private final ArtistDao artistDao;
    private final UserDao userDao;
    private final PlaylistDao playlistDao;

    @Autowired
    public ArtistController(final ArtistDao artistDao,
                            final UserDao userDao,
                            final PlaylistDao playlistDao) {
        this.artistDao = artistDao;
        this.userDao = userDao;
        this.playlistDao = playlistDao;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/follow")
    @ResponseBody
    public Response followArtist(@RequestBody final FollowArtistRequest followArtistRequest) {
        final String userId = followArtistRequest.getUser();
        final List<String> artists = followArtistRequest.getArtist();

        if (userId == null || userId.isEmpty() || artists == null || artists.isEmpty()) {
            return ControllerUtility.getBadRequestResponse();
        }

        try {
            final UserDbEntity userDbEntity = userDao.getById(userId);
            userDbEntity.getFollows().addAll(artists);

            Set<String> songs = new HashSet<>();
            for (final String artist : artists) {
                final ArtistDbEntity artistDbEntity = artistDao.getById(artist);
                artistDbEntity.getFollowers().add(userId);
                songs.addAll(artistDbEntity.getSongs());
            }

            for (String playlistId : userDbEntity.getPlaylists()) {
                playlistDao.getById(playlistId).getSongIds().addAll(songs);
            }

            final String message = String.format(FOLLOW_ARTIST_FORMAT, userId, Joiner.on(",").join(artists));
            return new Response("ok", message, HttpStatus.OK.value());
        } catch (final Exception ex) {
            return ControllerUtility.getBadRequestResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/unfollow")
    @ResponseBody
    public Response unFollowArtist(@RequestBody final UnFollowArtistRequest unFollowArtistRequest) {
        final String userId = unFollowArtistRequest.getUser();
        final List<String> artists = unFollowArtistRequest.getArtist();

        if (userId == null || userId.isEmpty() || artists == null || artists.isEmpty()) {
            return ControllerUtility.getBadRequestResponse();
        }

        try {
            final UserDbEntity userDbEntity = userDao.getById(userId);
            userDbEntity.getFollows().removeAll(artists);

            Set<String> songs = new HashSet<>();
            for (final String artist : artists) {
                final ArtistDbEntity artistDbEntity = artistDao.getById(artist);
                artistDbEntity.getFollowers().remove(userId);
                songs.addAll(artistDbEntity.getSongs());
            }

            for (String playlistId : userDbEntity.getPlaylists()) {
                playlistDao.getById(playlistId).getSongIds().removeAll(songs);
            }
            final String message = String.format(UN_FOLLOW_ARTIST_FORMAT, userId, Joiner.on(",").join(artists));
            return new Response("ok", message, HttpStatus.OK.value());
        } catch (final Exception ex) {
            return ControllerUtility.getBadRequestResponse();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/follow/count")
    @ResponseBody
    public Response getFollowerCount(@RequestParam("artist") String artist) {
        if (artist == null || artist.isEmpty()) {
            return ControllerUtility.getBadRequestResponse();

        }

        try {
            final ArtistDbEntity artistDbEntity = artistDao.getById(artist);
            return new FollowerCountResponse(artist, artistDbEntity.getFollowers().size());
        } catch (final Exception ex) {
            return ControllerUtility.getBadRequestResponse();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/follow/popular")
    @ResponseBody
    public Response getPopularArtistResponse() {
        try {
            int max = Integer.MIN_VALUE;
            ArtistDbEntity res = null;
            for (ArtistDbEntity artist : artistDao.getAll()) {
                if (artist.getFollowers().size() > max) {
                    max = artist.getFollowers().size();
                    res = artist;
                }
            }

            if (res == null) {
                return ControllerUtility.getBadRequestResponse();
            }

            return new PopularArtistResponse(res.getName());
        } catch (final Exception ex) {
            return ControllerUtility.getBadRequestResponse();
        }
    }
}
