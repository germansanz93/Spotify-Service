package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.domain.model.Track;

public interface ISpotifyService {

  Track playTrack(Long id);

}
