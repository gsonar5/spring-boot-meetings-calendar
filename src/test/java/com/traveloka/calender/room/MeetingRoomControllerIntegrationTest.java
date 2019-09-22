package com.traveloka.calender.room;

import static org.junit.Assert.assertEquals;

import com.traveloka.calender.contract.CreateEmplyoeeContract;
import com.traveloka.calender.contract.CreateMeetingRoomContract;
import com.traveloka.calender.utils.ApiConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeetingRoomControllerIntegrationTest {

  @LocalServerPort
  private int port;
  TestRestTemplate restTemplate = new TestRestTemplate();
  HttpHeaders headers = new HttpHeaders();

  @Test
  public void testMeetingRoomWhenValid() throws Exception {
    CreateMeetingRoomContract createMeetingRoomContract = getCreateMeetingRoomContract();
    HttpEntity<CreateEmplyoeeContract> entity = new HttpEntity(createMeetingRoomContract);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(ApiConstants.API_MEETING_ROOMS), HttpMethod.POST, entity, String.class);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
  }

  @Test
  public void testGetAllMeetingRooms() throws Exception {
    CreateMeetingRoomContract createMeetingRoomContract = getCreateMeetingRoomContract();
    HttpEntity<CreateEmplyoeeContract> entity = new HttpEntity(createMeetingRoomContract);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(ApiConstants.API_MEETING_ROOMS), HttpMethod.POST, entity, String.class);
    assertEquals(response.getStatusCode(), HttpStatus.OK);

    ResponseEntity<String> response1 = restTemplate.exchange(
        createURLWithPort(ApiConstants.API_MEETING_ROOMS), HttpMethod.GET, entity, String.class);

    assertEquals(response1.getStatusCode(), HttpStatus.OK);
  }

  private CreateMeetingRoomContract getCreateMeetingRoomContract() {
    CreateMeetingRoomContract createMeetingRoomContract = new CreateMeetingRoomContract();
    createMeetingRoomContract.setLocation("HYD");
    createMeetingRoomContract.setName("CRANE");
    return createMeetingRoomContract;
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + this.port + uri;
  }

}