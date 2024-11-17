package steps;

import base.BaseTest;
import io.restassured.response.Response;
import models.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Constants.*;

public class PetSteps extends BaseTest {
    private Response response;

    public Pet createRandomPet() {
        Random random = new Random();
        int petId = random.nextInt(1000) + 1001;
        String petName = "Pet-" + UUID.randomUUID();
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(0, petName + "_1.jpg");
        photoUrls.add(1, petName + "_2.jpg");
        Pet pet = new Pet();
        pet.setId(petId);
        pet.setName(petName);
        pet.setPhotoUrls(photoUrls);

        response = request.when()
                .body(pet)
                .post(PET_PATH);

        Pet createdPet = response.then().statusCode(OK)
                .extract()
                .as(Pet.class);
        assertEquals(petId, createdPet.getId());
        assertEquals(petName, createdPet.getName());
        assertEquals(photoUrls, createdPet.getPhotoUrls());

        return createdPet;
    }

    public void removePet(int id) {
        response = request.when()
                .delete(PET_PATH + "/" + id);

        response.then().statusCode(OK);

        String responseBody = response.asString();

        assertEquals(PET_DELETED, responseBody, GENERAL_NEGATIVE_ASSERT_MESSAGE);
    }
}
