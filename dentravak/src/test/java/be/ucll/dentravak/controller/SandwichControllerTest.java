package be.ucll.dentravak.controller;

import be.ucll.dentravak.model.Sandwich;
import be.ucll.dentravak.repository.SandwichRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.json;

import java.math.BigDecimal;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SandwichController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SandwichControllerTest {

    @Autowired
    private SandwichRepository sandwichRepository;

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void Setup() {
        sandwichRepository.save(Sandwich.SandwichBuilder.makeSandwich()
                                .withId(UUID.randomUUID())
                                .withName("Gezond")
                                .withIngredients("sla, tomaat")
                                .withPrice(BigDecimal.valueOf(3.50))
                                .build());
    }

    @Test
    public void testRetrieveSandwiches() {
        ResponseEntity<String> response = getHttpResponse("/sandwiches");

        assertThatJson(response.getBody()).isEqualTo("{\n" +
                "id: \"2d87e147-7673-4108-85b6-137b2288c758\",\n" +
                "name: \"Bla\",\n" +
                "ingredients: \"bla, bla\",\n" +
                "price: 5\n" +
                "}");
    }

    private ResponseEntity<String> getHttpResponse(String uri) {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        return restTemplate.exchange(
                createURLWithPort(uri),
                HttpMethod.GET, entity, String.class);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}