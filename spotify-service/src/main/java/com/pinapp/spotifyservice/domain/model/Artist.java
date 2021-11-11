package com.pinapp.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artist")
public class Artist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_artist")
  protected Long idArtist;
  @Column(name = "name")
  protected String name;
  @Column(name = "genre")
  protected String genre;
  @Column(name = "image")
  protected String image;
}
