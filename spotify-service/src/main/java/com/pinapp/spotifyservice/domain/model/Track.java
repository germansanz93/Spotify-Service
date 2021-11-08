package com.pinapp.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "track")
public class Track {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "id_artist")
  private Long idArtist;
  @Column(name = "id_album")
  private Long idAlbum;
  @Column(name = "reproductions")
  private Long reproductions;
  @Column(name = "duration")
  private Long duration; //Duration in ms

}
