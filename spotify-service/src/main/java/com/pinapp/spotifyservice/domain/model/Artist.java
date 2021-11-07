package com.pinapp.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artist")
public class Artist {
  @Id
  @Column(name = "id_artist")
  private Long idArtist;
  @Column(name = "name")
  private String name;
  @Column(name = "genre")
  private String genre;
  @Column(name = "image")
  private String image;
  @Column(name = "reproductions")
  private Long reproductions;
}
