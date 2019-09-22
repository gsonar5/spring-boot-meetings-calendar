package com.traveloka.calender.utils;

public final class ApiConstants {

  public static final String API_EMPLOYEES = "/api/employees";
  public static final String API_EMPLOYEES_BY_EMPLOYEE_ID = "/api/employees/{employeeId}";

  public static final String API_MEETINGS = "/api/meetings";
  public static final String API_MEETINGS_EMPLOYEE_EMP_ID = "/api/meetings/employee/{empId}";
  public static final String API_MEETINGS_ROOMS_NEEDED = "/api/meetings/roomsNeeded";

  public static final String API_MEETING_ROOMS = "/api/meetingRooms";
  public static final String API_MEETING_ROOMS_ROOM_ID = "/api/meetingRooms/{roomId}";

  public static final String START_DATE_IS_AFTER_END_DATE_MSG = "Start date is equal or after end date";

  private ApiConstants() {
  }
}