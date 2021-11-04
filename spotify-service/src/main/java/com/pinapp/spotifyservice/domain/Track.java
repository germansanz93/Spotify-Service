package com.pinapp.spotifyservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Track {
  private Long id;
  private String name;
  private Long idArtist;
  private Long idAlbum;
  private Long reproductions;
  private Long duration; //Duration in ms
}
