package be.ucll.dentravak.controller;

import be.ucll.dentravak.Application;
import be.ucll.dentravak.model.Sandwich;
import be.ucll.dentravak.repository.SandwichRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static be.ucll.dentravak.model.SandwichTestBuilder.aSandwich;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SandwichControllerIntegrationTest extends AbstractControllerIntegrationTest {
    @Autowired
    private SandwichRepository sandwichRepository;

    @Before
    public void setUpASavedSandwich() {
        sandwichRepository.deleteAll();
    }

    @Test
    public void testGetSandwiches_NoSavedSandwiches_EmptyList() throws JSONException {
        String actualSandwiches = httpGet("/sandwiches");
        String expectedSandwiches = "[]";

        assertThatJson(actualSandwiches).isEqualTo(expectedSandwiches);
    }

    @Test
    public void testPostSandwich() throws JSONException {
        Sandwich sandwich = aSandwich().withName("Americain").withIngredients("Vlees").withPrice(4.0).build();

        String actualSandwichAsJson = httpPost("/sandwiches", sandwich);
        String expectedSandwichAsJson = "{\"id\":\"${json-unit.ignore}\",\"name\":\"Americain\",\"ingredients\":\"Vlees\",\"price\":4}";

        assertThatJson(actualSandwichAsJson).isEqualTo(expectedSandwichAsJson);
    }

    @Test
    public void testPutSandwich() throws JSONException {
        Sandwich sandwich = aSandwich().withName("Gezond").withIngredients("Sla").withPrice(3.0).build();

        String sandwichJson = httpPost("/sandwiches/", sandwich);

        JSONObject jsonSandwichObject = new JSONObject(sandwichJson);

        String id = jsonSandwichObject.getString("id");

        sandwich.setIngredients("Sla, tomaat");
        sandwich.setId(UUID.fromString(id));

        String returnJson = httpPut("/sandwiches/" + id, sandwich);

        String expectedJson = "{\"id\":\"${json-unit.ignore}\",\"name\":\"Gezond\",\"ingredients\":\"Sla, tomaat\",\"price\":3}";

        assertThatJson(returnJson).isEqualTo(expectedJson);
    }

    @Test
    public void testGetSandwiches_WithSavedSandwiches_ListWithSavedSandwich() throws JSONException {
        Sandwich sandwich = aSandwich().withName("Gezond").withIngredients("Sla").withPrice(3.0).build();

        httpPost("/sandwiches/", sandwich);

        String actualSandwiches = httpGet("/sandwiches");

        JSONArray jsonSandwichArray = new JSONArray(actualSandwiches);
        JSONObject jsonSandwichObject = jsonSandwichArray.getJSONObject(0);
        String id = jsonSandwichObject.getString("id");

        String expectedJson = "[{\"id\":\"" + id + "\",\"name\":\"Gezond\",\"ingredients\":\"Sla\",\"price\":3.0}]";

        assertThatJson(actualSandwiches).isEqualTo(expectedJson);
    }
}
