package org.alex.guima.rpg.tracker.infrastructure.web;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
class CharacterResourceTest {

    private String post(String path, Map<String, Object> body) {
        return given().contentType(ContentType.JSON).body(body)
                .when().post(path)
                .then().statusCode(201)
                .extract().path("id");
    }

    @Test
    void createsListsAndUpdatesCharacters() {
        String campaignId = post("/campaign", Map.of("title", "Lost Mine", "description", "Starter"));
        String playerId = post("/player", Map.of("name", "Alex"));
        String classId = post("/character-class", Map.of("name", "Wizard", "description", "Casts spells"));

        given().when().get("/character-class/" + classId)
                .then().statusCode(200).body("description", equalTo("Casts spells"));

        String npcId = post("/character/non-playable",
                Map.of("name", "Innkeeper", "description", "Friendly", "campaignId", campaignId));
        String pcId = post("/character/playable", Map.of(
                "name", "Gale", "description", "Wizard", "campaignId", campaignId,
                "playerId", playerId, "characterClassId", classId));

        given().when().get("/character/" + pcId)
                .then().statusCode(200)
                .body("type", equalTo("PLAYABLE"))
                .body("playerId", equalTo(playerId))
                .body("characterClassId", equalTo(classId));

        given().queryParam("campaignId", campaignId).when().get("/character")
                .then().statusCode(200).body("$", hasSize(2));

        given().contentType(ContentType.JSON).body(Map.of("name", "Barkeep", "description", "Grumpy"))
                .when().put("/character/" + npcId)
                .then().statusCode(200)
                .body("name", equalTo("Barkeep"))
                .body("type", equalTo("NON_PLAYABLE"))
                .body("campaignId", equalTo(campaignId));
    }

    @Test
    void returnsNotFoundForUnknownReferences() {
        given().contentType(ContentType.JSON)
                .body(Map.of("name", "X", "description", "Y", "campaignId", UUID.randomUUID().toString()))
                .when().post("/character/non-playable")
                .then().statusCode(404);

        given().when().get("/character/" + UUID.randomUUID()).then().statusCode(404);
        given().when().get("/character").then().statusCode(400);
    }
}
