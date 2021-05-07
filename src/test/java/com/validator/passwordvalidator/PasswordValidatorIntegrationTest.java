package com.validator.passwordvalidator;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

@SpringBootTest()
public class PasswordValidatorIntegrationTest {

  WireMockServer wireMockServer;

  @BeforeEach
  public void setup() {
    wireMockServer = new WireMockServer(8095);
    wireMockServer.start();
  }

  @AfterEach
  public void teardown() {
    wireMockServer.stop();
  }

  @Test
  public void shouldBeResponseOkAndTrue() {
    wireMockServer.stubFor(get(urlPathMatching("/validator/([aA-zZ]*)/([aA-zZ]*)"))
      .willReturn(aResponse().withStatus(200)));

    given().
      when().
      get("http://localhost:8095/validator/strong/abcABC").
      then().
      assertThat().statusCode(200);
  }

  @Test
  public void shouldBeResponseNotFound() {
    wireMockServer.stubFor(get(urlPathMatching("/wrong-path"))
      .willReturn(aResponse().withStatus(404)));

    given().
      when().
      get("http://localhost:8095/wrong-path").
      then().
      assertThat().statusCode(404);
  }
}
