package tests;

import base.BaseTest;
import io.restassured.response.Response;
import io.restassured.http.Method;
import models.Pet;
import org.junit.jupiter.api.Test;
import steps.PetSteps;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.Matchers.equalTo;
import static utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPetTest extends BaseTest {
    private Response response;
    private final PetSteps petSteps = new PetSteps();
    private Pet pet;
    private int id = 0;

    @Test
    public void testGetPetById() {
        pet = petSteps.createRandomPet();
        id = pet.getId();

        response = request.when()
                .get(PET_PATH + "/" + id);

        response.then().statusCode(OK)
                .body(ID, equalTo(id))
                .body(NAME, equalTo(pet.getName()))
                .body(PHOTO_URLS, equalTo(pet.getPhotoUrls()));

        petSteps.removePet(id);
    }

    @Test
    public void testGetNonExistentPet() {
        response = request.when()
                .get(PET_PATH + "/" + NON_EXISTENT_PET_ID);

        response.then().statusCode(NOT_FOUND);
    }

    @Test
    public void testGetRemovedPet() {
        pet = petSteps.createRandomPet();
        id = pet.getId();

        petSteps.removePet(id);

        response = request.when()
                .get(PET_PATH + "/" + id);

        response.then().statusCode(NOT_FOUND);
    }

    @Test
    public void testGetPetWithInvalidId() {
        response = request.when()
                .get(PET_PATH + "/" + INVALID_PET_ID);

        response.then().statusCode(BAD_REQUEST);
    }

    @ParameterizedTest
    @EnumSource(value = Method.class, names = {POST, PATCH, OPTIONS})
    public void testPetWithIdUnsupportedMethods(Method method) {
        response = request.when()
                .request(method, PET_PATH + "/" +NON_EXISTENT_PET_ID);

        assertEquals(METHOD_NOT_ALLOWED, response.getStatusCode(),
                NEGATIVE_METHOD_NOT_ALLOWED_MESSAGE + method);
    }
}
