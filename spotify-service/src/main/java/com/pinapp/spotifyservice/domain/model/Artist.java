package com.pinapp.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class Artist {
  private Long idArtist;
  private String name;
  private String genre;
  private String image;
  private Long reproductions;
}
