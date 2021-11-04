package com.pinapp.spotifyservice.controller.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class AlbumRequest {

  @NotNull(message = "idArtist field cannot be null")
  private Long idArtist;

  @NotNull(message = "name field cannot be null")
  private String name;

}
