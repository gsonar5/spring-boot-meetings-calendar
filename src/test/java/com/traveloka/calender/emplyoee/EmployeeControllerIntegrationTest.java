package com.traveloka.calender.emplyoee;

import static org.junit.Assert.assertEquals;

import com.traveloka.calender.contract.CreateEmplyoeeContract;
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
public class EmployeeControllerIntegrationTest {

  @LocalServerPort
  private int port;
  TestRestTemplate restTemplate = new TestRestTemplate();
  HttpHeaders headers = new HttpHeaders();

  @Test
  public void testCreateEmployeeWhenValid() throws Exception {
    CreateEmplyoeeContract createEmplyoeeContract = getCreateEmplyoeeContract();
    HttpEntity<CreateEmplyoeeContract> entity = new HttpEntity(createEmplyoeeContract);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(ApiConstants.API_EMPLOYEES), HttpMethod.POST, entity, String.class);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
  }

  @Test
  public void testGetAllEmplyoees() throws Exception {
    CreateEmplyoeeContract createEmplyoeeContract = getCreateEmplyoeeContract();
    HttpEntity<CreateEmplyoeeContract> entity = new HttpEntity(createEmplyoeeContract);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort(ApiConstants.API_EMPLOYEES), HttpMethod.POST, entity, String.class);
    assertEquals(response.getStatusCode(), HttpStatus.OK);

    ResponseEntity<String> response1 = restTemplate.exchange(
        createURLWithPort(ApiConstants.API_EMPLOYEES), HttpMethod.GET, entity, String.class);

    assertEquals(response1.getStatusCode(), HttpStatus.OK);

    JSONAssert.assertEquals(Arrays.asList(createEmplyoeeContract).toString(), response1.getBody(), false);
  }

  private CreateEmplyoeeContract getCreateEmplyoeeContract() {
    CreateEmplyoeeContract createEmplyoeeContract = new CreateEmplyoeeContract();
    createEmplyoeeContract.setEmpId(1L);
    createEmplyoeeContract.setName("abc");
    return createEmplyoeeContract;
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + this.port + uri;
  }
}