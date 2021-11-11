package com.pinapp.spotifyservice.exception;

public class TrackExistException extends RuntimeException {
  public TrackExistException(String message) {
    super(message);
  }
}
