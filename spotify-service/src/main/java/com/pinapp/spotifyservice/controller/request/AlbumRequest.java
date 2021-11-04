package com.pinapp.spotifyservice.controller.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Builder
@Data
public class AlbumRequest {

  private Long idAlbum;

  @NotNull(message = "idArtist field cannot be null")
  private Long idArtist;

  @NotBlank(message = "name field cannot be null or blank")
  private String name;

}
