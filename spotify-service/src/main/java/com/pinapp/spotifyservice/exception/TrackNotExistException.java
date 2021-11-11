package com.pinapp.spotifyservice.exception;

public class TrackNotExistException extends RuntimeException{

    public TrackNotExistException(String message){
      super(message);
    }
}
