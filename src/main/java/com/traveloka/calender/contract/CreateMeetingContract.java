package com.traveloka.calender.contract;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JsonDeserialize
public class CreateMeetingContract {

  @NotNull
  private String organiser;

  @NotNull
  private String roomName;

  @NotNull
  @NotEmpty
  private List<String> invitees;

  @NotNull
  private String subject;

  @NotNull
  private Date startTime;

  @NotNull
  private Date endTime;

  public String getOrganiser() {
    return organiser;
  }

  public void setOrganiser(String organiser) {
    this.organiser = organiser;
  }

  public List<String> getInvitees() {
    return invitees;
  }

  public void setInvitees(List<String> invitees) {
    this.invitees = invitees;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
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
