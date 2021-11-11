package com.pinapp.spotifyservice.domain.mapper;

import com.pinapp.spotifyservice.domain.model.Artist;
import com.pinapp.spotifyservice.domain.model.ArtistRanked;
import com.pinapp.spotifyservice.repository.IArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ArtistRankedMapper implements Function<Artist, ArtistRanked> {

  @Autowired
  private IArtistRepository artistRepository;

  public ArtistRanked apply(Artist artist) {
    return ArtistRanked.builder()
        .idArtist(artist.getIdArtist())
        .name(artist.getName())
        .genre(artist.getGenre())
        .image(artist.getImage())
        .build();
  }
}



