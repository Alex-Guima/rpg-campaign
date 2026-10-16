package org.alex.guima.rpg.tracker.infrastructure.web;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@QuarkusTest
class RequestValidationTest {

    private void expectBadRequest(String method, String path, Object body) {
        var request = given().contentType(ContentType.JSON);
        if (body != null) {
            request = request.body(body);
        }
        request.when().request(method, path).then().statusCode(400);
    }

    @Test
    void rejectsBlankRequiredText() {
        expectBadRequest("POST", "/campaign", Map.of("title", " ", "description", "Desc"));
        expectBadRequest("PUT", "/campaign/" + UUID.randomUUID(), Map.of("title", "", "description", "Desc"));
        expectBadRequest("POST", "/player", Map.of("name", ""));
        expectBadRequest("POST", "/character-class", Map.of("description", "No name"));
        expectBadRequest("PUT", "/character/" + UUID.randomUUID(), Map.of("name", " "));
    }

    @Test
    void rejectsTextLongerThanColumn() {
        String tooLong = "x".repeat(256);
        expectBadRequest("POST", "/campaign", Map.of("title", "Title", "description", tooLong));
        expectBadRequest("POST", "/player", Map.of("name", tooLong));
    }

    @Test
    void rejectsCampaignTitleLongerThan64Characters() {
        String title = "x".repeat(65);
        expectBadRequest("POST", "/campaign", Map.of("title", title));
        expectBadRequest("PUT", "/campaign/" + UUID.randomUUID(), Map.of("title", title));
        given().contentType(ContentType.JSON).body(Map.of("title", "x".repeat(64)))
                .when().post("/campaign")
                .then().statusCode(201);
    }

    @Test
    void rejectsNamesLongerThan64Characters() {
        String name = "x".repeat(65);
        expectBadRequest("POST", "/player", Map.of("name", name));
        expectBadRequest("POST", "/character-class", Map.of("name", name));
        expectBadRequest("PUT", "/character/" + UUID.randomUUID(), Map.of("name", name));
        expectBadRequest("POST", "/character/non-playable",
                Map.of("name", name, "campaignId", UUID.randomUUID().toString()));
        given().contentType(ContentType.JSON).body(Map.of("name", "x".repeat(64)))
                .when().post("/player")
                .then().statusCode(201);
    }

    @Test
    void rejectsMissingReferenceIds() {
        expectBadRequest("POST", "/character/non-playable", Map.of("name", "Innkeeper"));
        expectBadRequest("POST", "/character/playable",
                Map.of("name", "Gale", "campaignId", UUID.randomUUID().toString()));
    }

    @Test
    void rejectsMissingBody() {
        expectBadRequest("POST", "/campaign", null);
        expectBadRequest("POST", "/player", null);
    }
}
