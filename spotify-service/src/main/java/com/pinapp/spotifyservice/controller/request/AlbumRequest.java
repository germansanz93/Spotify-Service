package com.pinapp.spotifyservice.controller.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Builder
@Data
public class AlbumRequest {

  @Positive(message = "idAlbum must be positive")
  private Long idAlbum;

  @Positive(message = "idArtist must be positive")
  @NotNull(message = "idArtist field cannot be null")
  private Long idArtist;

  @NotBlank(message = "name field cannot be null nor blank")
  private String name;

}
