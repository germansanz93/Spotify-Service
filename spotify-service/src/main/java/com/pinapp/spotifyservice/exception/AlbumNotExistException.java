package com.pinapp.spotifyservice.exception;

public class AlbumNotExistException extends RuntimeException {

  public AlbumNotExistException(String message) {
    super(message);
  }
}
