package com.traveloka.calender.meeting.persistence;

import com.traveloka.calender.emplyoee.persistence.Employee;
import com.traveloka.calender.room.persistence.MeetingRoom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "MEETING")
public class Meeting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long meetingId;

  private String subject;

  private Date startTime;

  private Date endTime;

  @OneToOne
  private Employee organiser;

  @OneToOne
  private MeetingRoom meetingRoom;

  @ManyToMany
  Set<Employee> invitees;

  public Long getMeetingId() {
    return meetingId;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Employee getOrganiser() {
    return organiser;
  }

  public void setOrganiser(Employee organiser) {
    this.organiser = organiser;
  }

  public Set<Employee> getInvitees() {
    return invitees;
  }

  public void setInvitees(Set<Employee> invitees) {
    this.invitees = invitees;
  }

  public MeetingRoom getMeetingRoom() {
    return meetingRoom;
  }

  public void setMeetingRoom(MeetingRoom meetingRoom) {
    this.meetingRoom = meetingRoom;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }
}