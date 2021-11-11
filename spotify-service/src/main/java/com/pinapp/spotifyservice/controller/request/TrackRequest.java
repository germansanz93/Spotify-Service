package com.pinapp.spotifyservice.controller.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@Data
public class TrackRequest {
  @Positive
  private Long id;
  @NotBlank(message = "name field cannot be blank nor null")
  private String name;
  @Positive(message = "idArtist must be positive")
  @NotNull(message = "idArtist field cannot be null")
  private Long idArtist;
  @Positive(message = "idAlbum must be positive")
  @NotNull(message = "idAlbum field cannot be null")
  private Long idAlbum;
  @Positive(message = "duration must be positive")
  @NotNull(message = "duration field cannot be null")
  private Long duration;
}
