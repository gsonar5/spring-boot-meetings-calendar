package com.traveloka.calender.room.service;

import com.traveloka.calender.contract.CreateMeetingRoomContract;
import com.traveloka.calender.room.persistence.MeetingRoom;
import com.traveloka.calender.room.persistence.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

  @Autowired
  private MeetingRoomRepository meetingRoomRepository;

  public void setEmployeeRepository(MeetingRoomRepository meetingRoomRepository) {
    this.meetingRoomRepository = meetingRoomRepository;
  }

  public List<MeetingRoom> retrieveMeetingRooms() {
    List<MeetingRoom> rooms = meetingRoomRepository.findAll();
    return rooms;
  }

  public MeetingRoom getMeetingRoom(Long roomId) {
    Optional<MeetingRoom> room = meetingRoomRepository.findById(roomId);
    return room.get();
  }

  public void saveMeetingRoom(CreateMeetingRoomContract createMeetingRoomContract) {
    MeetingRoom meetingRoom = new MeetingRoom();
    meetingRoom.setName(createMeetingRoomContract.getName());
    meetingRoom.setLocation(createMeetingRoomContract.getLocation());
    meetingRoomRepository.save(meetingRoom);
  }


}