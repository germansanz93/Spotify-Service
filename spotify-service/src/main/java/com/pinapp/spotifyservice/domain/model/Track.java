package com.pinapp.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
public class Track {
  private Long id;
  private String name;
  private Long idArtist;
  private Long idAlbum;
  private Long reproductions;
  private Long duration; //Duration in ms

}
