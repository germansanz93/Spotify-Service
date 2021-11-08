package com.pinapp.spotifyservice.configuration;

import com.pinapp.spotifyservice.domain.model.Track;
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
            .name("La apariencia no es sincera")
            .idArtist(1L)
            .idAlbum(1L)
            .duration(421800L)
            .reproductions(1123L)
            .build(),
        Track.builder()
            .name("Blackened")
            .idArtist(2L)
            .idAlbum(2L)
            .duration(385200L)
            .reproductions(8213L)
            .build(),
        Track.builder()
            .name("...And justice for all")
            .idArtist(2L)
            .idAlbum(3L)
            .duration(567600L)
            .reproductions(7413L)
            .build(),
        Track.builder()
            .name("Eye of the Beholder")
            .idArtist(2L)
            .idAlbum(3L)
            .duration(375000L)
            .reproductions(1494L)
            .build(),
        Track.builder()
            .name("One")
            .idArtist(2L)
            .idAlbum(3L)
            .duration(435600L)
            .reproductions(12087L)
            .build(),
        Track.builder()
            .name("The Shortest Straw")
            .idArtist(2L)
            .idAlbum(3L)
            .duration(381000L)
            .reproductions(5413L)
            .build(),
        Track.builder()
            .name("Harvester of Sorrow")
            .idArtist(2L)
            .idAlbum(3L)
            .duration(327000L)
            .reproductions(6954L)
            .build(),
        Track.builder()
            .name("The Frayed Ends of Sanity")
            .idArtist(2L)
            .idAlbum(3L)
            .duration(445800L)
            .reproductions(3413L)
            .build(),
        Track.builder()
            .name("To Live Is to Die")
            .idArtist(2L)
            .idAlbum(3L)
            .duration(569400L)
            .reproductions(5413L)
            .build(),
        Track.builder()
            .name("Dyers Eve")
            .idArtist(3L)
            .idAlbum(4L)
            .duration(308400L)
            .reproductions(3413L)
            .build(),
        Track.builder()
            .name("Pride and Joy")
            .idArtist(3L)
            .idAlbum(4L)
            .duration(204000L)
            .reproductions(3413L)
            .build()
    );
  }
}
