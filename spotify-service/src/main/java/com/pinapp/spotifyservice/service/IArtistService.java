package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.domain.model.ArtistRanked;

import java.util.List;

public interface IArtistService {

  List<Artist> getArtists();

  Artist getArtist(Long id);

  List<ArtistRanked> getTopArtists(int limit);

  Artist createArtist(ArtistRequest request);

  Artist updateArtist(ArtistRequest request);

  void deleteArtist(Long id);

}
