package com.traveloka.calender.exceptions;

public class InvalidStartEndDateException extends RuntimeException {
  public InvalidStartEndDateException(Throwable ex) {
    super(ex);
  }

  public InvalidStartEndDateException(String str) {
    super(str);
  }
}
