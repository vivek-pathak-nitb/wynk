package com.wynk.controller;

import com.google.common.collect.Lists;
import com.wynk.dao.PlaylistDao;
import com.wynk.dao.UserDao;
import com.wynk.entities.db.UserDbEntity;
import com.wynk.entities.response.PlaylistSongResponse;
import com.wynk.entities.response.Response;
import com.wynk.utilities.ControllerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/wynk/playlist")
public class PlaylistController {

    private final UserDao userDao;
    private final PlaylistDao playlistDao;

    @Autowired
    public PlaylistController(final UserDao userDao,
                              final PlaylistDao playlistDao) {
        this.userDao = userDao;
        this.playlistDao = playlistDao;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @ResponseBody
    public Response getSongs(@RequestParam("user") final String user) {
        if (user == null || user.isEmpty()) {
            return ControllerUtility.getBadRequestResponse();
        }

        try {
            final UserDbEntity userDbEntity = userDao.getById(user);
            Set<String> songs = new HashSet<>();
            for (final String playlist : userDbEntity.getPlaylists()) {
                songs.addAll(playlistDao.getById(playlist).getSongIds());
            }
            return new PlaylistSongResponse(Lists.newArrayList(songs));
        } catch (final Exception ex) {
            return ControllerUtility.getBadRequestResponse();
        }
    }

}
