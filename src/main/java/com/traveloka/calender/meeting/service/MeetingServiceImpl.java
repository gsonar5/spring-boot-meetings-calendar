package com.traveloka.calender.meeting.service;

import com.traveloka.calender.contract.CreateMeetingContract;
import com.traveloka.calender.emplyoee.persistence.Employee;
import com.traveloka.calender.emplyoee.persistence.EmployeeRepository;
import com.traveloka.calender.exceptions.InvalidStartEndDateException;
import com.traveloka.calender.exceptions.InviteeNotFoundException;
import com.traveloka.calender.exceptions.MeetingRoomNotFoundException;
import com.traveloka.calender.meeting.persistence.Meeting;
import com.traveloka.calender.meeting.persistence.MeetingRepository;
import com.traveloka.calender.room.persistence.MeetingRoom;
import com.traveloka.calender.room.persistence.MeetingRoomRepository;
import com.traveloka.calender.utils.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class MeetingServiceImpl implements MeetingService {

  @Autowired
  private MeetingRepository meetingRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private MeetingRoomRepository meetingRoomRepository;

  public void setMeetingRepository(
      MeetingRepository meetingRepository,
      EmployeeRepository employeeRepository, MeetingRoomRepository meetingRoomRepository) {
    this.meetingRepository = meetingRepository;
    this.employeeRepository = employeeRepository;
    this.meetingRoomRepository = meetingRoomRepository;
  }

  @Override
  public List<Meeting> retrieveMeetings() {
    List<Meeting> meetings = meetingRepository.findAll();
    return meetings;
  }

  @Override
  public void saveMeeting(CreateMeetingContract meeting) {

    Meeting meetingToSave = transformToMeeting(meeting);
    meetingRepository.save(meetingToSave);
  }

  @Override
  public List<Meeting> retrieveMeetingsByEmplyoeeId(Long empId) {
    return meetingRepository.findByInvitees_EmpId(empId);
  }

  @Override
  public int roomsRequiredToScheduleAllMeetings() {
    int result = 0;
    List<Object[]> startEndDateTimes = meetingRepository.getStartEndTimeOfAllMeetings();

    if (startEndDateTimes != null && startEndDateTimes.size() > 0) {
      int n = startEndDateTimes.size();
      long startTime[] = new long[n];
      long endTime[] = new long[n];
      for (int i = 0; i < n; i++) {
        startTime[i] = ((Date) startEndDateTimes.get(i)[0]).getTime();
        endTime[i] = ((Date) startEndDateTimes.get(i)[1]).getTime();
      }
      result = calculateRoomsNeeded(result, n, startTime, endTime);
    }

    return result;
  }

  private int calculateRoomsNeeded(int result, int n, long[] startTime, long[] endTime) {
    Arrays.sort(startTime);
    Arrays.sort(endTime);

    int roomsNeeded = 1;
    result = 1;
    int i = 1, j = 0;

    while (i < n && j < n) {
      if (startTime[i] <= endTime[j]) {
        roomsNeeded++;
        i++;

        if (roomsNeeded > result)
          result = roomsNeeded;
      } else {
        roomsNeeded--;
        j++;
      }
    }
    return result;
  }

  private Meeting transformToMeeting(CreateMeetingContract meeting) {
    Employee organiser = employeeRepository.findByName(meeting.getOrganiser());
    Set<Employee> invitees = employeeRepository.findByNameIn(meeting.getInvitees());

    MeetingRoom room = meetingRoomRepository.findByName(meeting.getRoomName());

    if (organiser == null || invitees == null || invitees.size() == 0) {
      throw new InviteeNotFoundException(meeting.getInvitees().toString());
    }

    if (room == null) {
      throw new MeetingRoomNotFoundException(room.getName());
    }

    if (meeting.getEndTime().getTime() <= (meeting.getStartTime()).getTime()) {
      throw new InvalidStartEndDateException(ApiConstants.START_DATE_IS_AFTER_END_DATE_MSG);
    }

    return populateMeetingEntity(meeting, organiser, invitees, room);
  }

  private Meeting populateMeetingEntity(
      CreateMeetingContract meeting, Employee organiser,
      Set<Employee> invitees, MeetingRoom room) {

    invitees.add(organiser);

    Meeting meetingToSave = new Meeting();
    meetingToSave.setOrganiser(organiser);
    meetingToSave.setInvitees(invitees);
    meetingToSave.setStartTime(meeting.getStartTime());
    meetingToSave.setEndTime(meeting.getEndTime());
    meetingToSave.setSubject(meeting.getSubject());
    meetingToSave.setMeetingRoom(room);
    return meetingToSave;
  }

}