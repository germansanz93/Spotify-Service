package com.pinapp.spotifyservice.exception;

public class AlbumExistException extends RuntimeException {

  public AlbumExistException(String message) {
    super(message);
  }
}
