import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.petstore.model.pet.Pet;
import org.petstore.response.ErrorResponse;
import org.petstore.utils.ApiMethodUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import providers.PetDataProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.petstore.specs.pet.PetSpecs.addOrUpdatePetSpec;
import static org.petstore.specs.pet.PetSpecs.getOrDeleteOrUpdatePetByIdSpec;
import static org.petstore.utils.PetUtils.addPetBody;
import static org.testng.Assert.assertEquals;
import static providers.PetDataProvider.BODY;

public class AddPetTests {

    private final List<Long> petIdsForDelete = new ArrayList<>(List.of((long)BODY.get("id")));

    @AfterClass
    public void clear(){
        petIdsForDelete.forEach(id -> ApiMethodUtils.delete(getOrDeleteOrUpdatePetByIdSpec.pathParam("petId",id)));
    }

    @Test(dataProvider = "petProvider", dataProviderClass = PetDataProvider.class)
    @Owner("Yauheni Sokalau")
    public void addPetTest(Pet pet) {
        Response response = ApiMethodUtils.post(addOrUpdatePetSpec, addPetBody(pet));

        Pet petFromServer = ApiMethodUtils.get(
                getOrDeleteOrUpdatePetByIdSpec.pathParam("petId", pet.getId())).as(Pet.class);

        petIdsForDelete.add(pet.getId());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Different code");
        softAssert.assertEquals(response.as(Pet.class), pet, "Different pets");
        softAssert.assertEquals(petFromServer, pet, "Different pets");

        softAssert.assertAll();
    }

    @Test
    @Owner("Yauheni Sokalau")
    public void addPetWithEmptyBody(){
        Response response = ApiMethodUtils.post(addOrUpdatePetSpec, "");

        assertEquals(response.getStatusCode(), 405, "Different code");
    }

    @Test
    @Owner("Yauheni Sokalau")
    public void addEmptyPetTest(){
        Response response = ApiMethodUtils.post(addOrUpdatePetSpec, Map.of());

        Pet petFromServer = ApiMethodUtils.get(getOrDeleteOrUpdatePetByIdSpec.pathParam(
                "petId", response.as(Pet.class).getId())).as(Pet.class);

        petIdsForDelete.add(petFromServer.getId());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Different code");
        softAssert.assertNotNull(petFromServer, "Not found pet");

        softAssert.assertAll();
    }

    @Test(dataProvider = "mapModificationProvider", dataProviderClass = PetDataProvider.class)
    @Owner("Yauheni Sokalau")
    public void putIncorrectParamsInBody(String key, Object value){
        Map<String, Object> requestBody = new HashMap<>(BODY);
        requestBody.put(key, value);

        Response response = ApiMethodUtils.post(addOrUpdatePetSpec, requestBody);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 500, "Different code");
        softAssert.assertEquals(response.as(ErrorResponse.class).getMessage(),"something bad happened",
                "Incorrect message");

        softAssert.assertAll();
    }
}
