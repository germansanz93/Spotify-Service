package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.TrackRequest;
import com.pinapp.spotifyservice.domain.model.Track;

import java.util.List;

public interface ITrackService {

  List getTracks();

  List getTracksByArtist(Long idArtist);

  List getArtistRankedTracks(Long idArtist);

  List<Track> getArtistRankedTracks(Long idArtist, int limit);

  List<Track> getRankedTracks(int limit);

  Track getTrack(Long id);

  Track createTrack(TrackRequest request);

  Track updateTrack(TrackRequest request);

  void deleteTrack(Long id);

  Track playTrack(Long id);

}
