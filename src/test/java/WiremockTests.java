import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class WiremockTests {

    @Test
    void simpleWiremockTest() {

        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();

        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/baeldung")).willReturn(aResponse().withBody("Welcome to Baeldung!")));

        ResponseBodyExtractionOptions body = given()
                .body("")
                .when()
                .get("http://localhost:8080/baeldung")
                .then()
                .extract()
                .body();

        assertThat(body.asString()).isEqualTo("Welcome to Baeldung!");

        wireMockServer.stop();


    }

}
