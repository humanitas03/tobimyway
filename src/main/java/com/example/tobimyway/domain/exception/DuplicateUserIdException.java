/**
 * =============================================================== File name :
 * DuplicateUserIdException.java Created by injeahwang on 2021-06-27
 * ===============================================================
 */
package com.example.tobimyway.domain.exception;

public class DuplicateUserIdException extends RuntimeException{
  public DuplicateUserIdException(Throwable cause) {
    super(cause);
  }

  public DuplicateUserIdException(String message, Throwable cause){
    super(message, cause);
  }

  public DuplicateUserIdException(String message){
    super(message);
  }


}
