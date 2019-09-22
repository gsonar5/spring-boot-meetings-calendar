package com.traveloka.calender.room.service;

import com.traveloka.calender.contract.CreateMeetingRoomContract;
import com.traveloka.calender.room.persistence.MeetingRoom;

import java.util.List;

public interface MeetingRoomService {
  public List<MeetingRoom> retrieveMeetingRooms();

  public MeetingRoom getMeetingRoom(Long employeeId);

  public void saveMeetingRoom(CreateMeetingRoomContract employee);

}