package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.domain.Track;

public interface ISpotifyService {
  Track playTrack(Long id);
}
