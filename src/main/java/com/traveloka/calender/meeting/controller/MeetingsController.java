package com.traveloka.calender.meeting.controller;

import com.traveloka.calender.contract.CreateMeetingContract;
import com.traveloka.calender.exceptions.InviteeNotFoundException;
import com.traveloka.calender.meeting.persistence.Meeting;
import com.traveloka.calender.meeting.service.MeetingService;
import com.traveloka.calender.utils.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

@RestController
public class MeetingsController {

  @Autowired
  private MeetingService meetingService;

  public void setMeetingService(MeetingService meetingService) {
    this.meetingService = meetingService;
  }

  @GetMapping(ApiConstants.API_MEETINGS)
  public List<Meeting> getMeetings() {
    List<Meeting> meeting = meetingService.retrieveMeetings();
    return meeting;
  }

  @PostMapping(ApiConstants.API_MEETINGS)
  public void saveMeeting(@Valid @RequestBody CreateMeetingContract createMeetingContract) {
    meetingService.saveMeeting(createMeetingContract);
  }

  @GetMapping(ApiConstants.API_MEETINGS_EMPLOYEE_EMP_ID)
  public List<Meeting> getMeetingsForEmployee(@PathVariable(name = "empId") Long employeeId) {
    return meetingService.retrieveMeetingsByEmplyoeeId(employeeId);
  }

  @GetMapping(ApiConstants.API_MEETINGS_ROOMS_NEEDED)
  public Integer roomsRequiredToScheduleAllMeetings() {
    return meetingService.roomsRequiredToScheduleAllMeetings();
  }

  @ExceptionHandler({InvalidPropertiesFormatException.class, InviteeNotFoundException.class})
  public ResponseEntity<String> handleBadRequest(RuntimeException ex) {
    System.out.println(ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}