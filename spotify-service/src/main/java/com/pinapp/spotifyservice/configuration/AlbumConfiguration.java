package com.pinapp.spotifyservice.configuration;

import com.pinapp.spotifyservice.domain.model.Album;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AlbumConfiguration {

  @Bean(name = "albums")
  public List<Album> getAlbums(){
    return Arrays.asList(
        Album.builder().idArtist(1L).name("El espiritu del vino").build(),
        Album.builder().idArtist(2L).name("Ride the lightning").build(),
        Album.builder().idArtist(2L).name("... And justice for all").build(),
        Album.builder().idArtist(3L).name("Texas flood").build()
    );
  }
}
