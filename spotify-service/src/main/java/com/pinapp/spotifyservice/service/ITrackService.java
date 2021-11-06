package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.Track;

import java.util.List;

public interface ITrackService {

  List getTracks();

  Track getTrack(Long id);

  Track createTrack(TrackRequest request);

  Track updateTrack(TrackRequest request);

  Track deleteTrack(Long id);

  Track playTrack(Long id);

}
