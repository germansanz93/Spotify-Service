package com.pinapp.spotifyservice.configuration;

import com.pinapp.spotifyservice.domain.model.Artist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ArtistConfiguration {

  @Bean(name = "artists")
  public List<Artist> getArtists(){
    return Arrays.asList(
      Artist.builder()
          .name("Heroes del silencio")
          .genre("Rock")
          .image("https://phantom-marca.unidadeditorial.es/dfc731b5cc59092c5d9d8d70b10e3cad/resize/" +
              "1320/f/jpg/assets/multimedia/imagenes/2021/04/05/16176436962285.jpg")
          .reproductions(1123L)
          .build(),

      Artist.builder()
          .name("Metallica")
          .genre("Heavy metal")
          .image("https://www.futuro.cl/wp-content/uploads/2021/04/metallica-1983-mustaine-web-768x432.jpg")
          .reproductions(50400L)
          .build(),

      Artist.builder()
          .name("Stevie Ray Vaughan")
          .genre("Blues")
          .image("https://i1.wp.com/jessicakristie.com/wp-content/uploads/2012/02/StevieRayVaughan.jpg")
          .reproductions(6826L)
          .build()
    );
  }
}
