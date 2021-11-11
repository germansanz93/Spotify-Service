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
  @ManyToOne
  @JoinColumn(name = "id_artist")
  private Artist artist;
  @ManyToOne
  @JoinColumn(name = "id_album")
  private Album album;
  @Column(name = "reproductions")
  private Long reproductions;
  @Column(name = "duration")
  private Long duration; //Duration in ms

}
