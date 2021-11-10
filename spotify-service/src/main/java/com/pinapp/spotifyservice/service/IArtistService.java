package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.model.Artist;

import java.util.List;

public interface IArtistService {

  List<Artist> getArtists();

  Artist getArtist(Long id);

  List<Artist> getTopArtists(int limit);

  Artist createArtist(ArtistRequest request);

  Artist updateArtist(ArtistRequest request);

  Artist deleteArtist(Long id);

}
