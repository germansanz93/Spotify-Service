package com.pinapp.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {
  private Long idAlbum;
  private Long idArtist;
  private String name;
}
