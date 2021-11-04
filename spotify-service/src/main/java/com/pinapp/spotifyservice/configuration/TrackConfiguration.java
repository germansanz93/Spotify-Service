package com.pinapp.spotifyservice.configuration;

import com.pinapp.spotifyservice.domain.Track;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TrackConfiguration {

  @Bean(name = "tracks")
  public List<Track> getTracks(){
    return Arrays.asList(
        Track.builder()
            .id(1L)
            .name("La apariencia no es sincera")
            .idArtist(1L)
            .idAlbum(1L)
            .duration(421800L)
            .reproductions(1123L)
            .build(),
        Track.builder()
            .id(2L)
            .name("For whom the bell tolls")
            .idArtist(2L)
            .idAlbum(2L)
            .duration(306000L)
            .reproductions(3213L)
            .build(),
        Track.builder()
            .id(3L)
            .name("...And justice for all")
            .idArtist(2L)
            .idAlbum(3L)
            .duration(567600L)
            .reproductions(5413L)
            .build(),
        Track.builder()
            .id(4L)
            .name("Pride and Joy")
            .idArtist(3L)
            .idAlbum(4L)
            .duration(204000L)
            .reproductions(3413L)
            .build()
    );
  }
}
