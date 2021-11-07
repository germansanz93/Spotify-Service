package com.pinapp.spotifyservice.exception;

public class ArtistNotExistException extends RuntimeException{

  public ArtistNotExistException(String message){
    super(message);
  }

}
