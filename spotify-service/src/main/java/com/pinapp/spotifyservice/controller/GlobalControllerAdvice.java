package com.pinapp.spotifyservice.controller;

import com.pinapp.spotifyservice.exception.AlbumNotExistException;
import com.pinapp.spotifyservice.exception.ArtistNotExistException;
import com.pinapp.spotifyservice.exception.TrackNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseBody
@ControllerAdvice
public class GlobalControllerAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String,String> handlerValidationException(MethodArgumentNotValidException ex){
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ArtistNotExistException.class)
  public Map<String, String> handlerValidationException(ArtistNotExistException ex){
    Map<String, String> errors = new HashMap<>();
    String fieldName = "Error: ";
    errors.put(fieldName, ex.getMessage());
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(TrackNotExistException.class)
  public Map<String, String> handlerValidationException(TrackNotExistException ex){
    Map<String, String> errors = new HashMap<>();
    String fieldName = "Error: ";
    errors.put(fieldName, ex.getMessage());
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(AlbumNotExistException.class)
  public Map<String, String> handlerValidationException(AlbumNotExistException ex){
    Map<String, String> errors = new HashMap<>();
    String fieldName = "Error: ";
    errors.put(fieldName, ex.getMessage());
    return errors;
  }

}
