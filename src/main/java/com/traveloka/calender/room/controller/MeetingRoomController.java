package com.traveloka.calender.room.controller;

import com.traveloka.calender.contract.CreateMeetingRoomContract;
import com.traveloka.calender.room.persistence.MeetingRoom;
import com.traveloka.calender.emplyoee.service.EmployeeService;
import com.traveloka.calender.room.service.MeetingRoomService;
import com.traveloka.calender.utils.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeetingRoomController {

  @Autowired
  private MeetingRoomService meetingRoomService;

  public void setMeetingRoomService(EmployeeService employeeService) {
    this.meetingRoomService = meetingRoomService;
  }

  @GetMapping(ApiConstants.API_MEETING_ROOMS)
  public List<MeetingRoom> getMeetingRooms() {
    List<MeetingRoom> meetingRooms = meetingRoomService.retrieveMeetingRooms();
    return meetingRooms;
  }

  @GetMapping(ApiConstants.API_MEETING_ROOMS_ROOM_ID)
  public MeetingRoom getEmployee(@PathVariable(name = "roomId") Long roomId) {
    return meetingRoomService.getMeetingRoom(roomId);
  }

  @PostMapping(ApiConstants.API_MEETING_ROOMS)
  public void saveMeetingRoom(@RequestBody CreateMeetingRoomContract createMeetingRoomContract) {
    meetingRoomService.saveMeetingRoom(createMeetingRoomContract);
  }

}