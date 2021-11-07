package com.pinapp.spotifyservice.controller.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
public class ArtistRequest {
  @Positive(message = "idArtist field must be positive")
  private Long idArtist;
  @NotBlank(message = "name field cannot be blank nor null")
  private String name;
  @NotBlank(message = "genre field cannot be blank nor null")
  private String genre;
  private String image;

}
