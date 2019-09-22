package com.traveloka.calender.meeting.service;

import com.traveloka.calender.contract.CreateMeetingContract;
import com.traveloka.calender.meeting.persistence.Meeting;

import java.util.List;

public interface MeetingService {
  public int roomsRequiredToScheduleAllMeetings();

  public List<Meeting> retrieveMeetings();

  public void saveMeeting(CreateMeetingContract meeting);

  public List<Meeting> retrieveMeetingsByEmplyoeeId(Long empId);

}