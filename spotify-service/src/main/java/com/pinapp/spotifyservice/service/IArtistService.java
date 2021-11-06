package com.pinapp.spotifyservice.service;

import com.pinapp.spotifyservice.controller.request.ArtistRequest;
import com.pinapp.spotifyservice.domain.Artist;

import java.util.List;

public interface IArtistService {

  List<Artist> getArtists();

  Artist getArtist(Long id);

  Artist createArtist(ArtistRequest request);

  Artist updateArtist(ArtistRequest request);

  Artist deleteArtist(Long id);

}
