package com.traveloka.calender.meeting.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
  public static final String GET_START_END_TIME_OF_ALL_MEETINGS =
      "SELECT START_TIME, END_TIME FROM MEETING";

  List<Meeting> findByInvitees_EmpId(Long id);

  @Query(value = GET_START_END_TIME_OF_ALL_MEETINGS, nativeQuery = true)
  public List<Object[]> getStartEndTimeOfAllMeetings();
}