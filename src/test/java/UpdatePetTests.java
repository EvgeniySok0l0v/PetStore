import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.petstore.model.pet.Pet;
import org.petstore.utils.ApiMethodUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static org.petstore.specs.pet.PetSpecs.addOrUpdatePetSpec;
import static org.petstore.specs.pet.PetSpecs.getOrDeleteOrUpdatePetByIdSpec;
import static providers.PetDataProvider.BODY;

public class UpdatePetTests {

    @BeforeClass
    public void setUp(){
        ApiMethodUtils.post(addOrUpdatePetSpec, BODY);
    }

    @AfterClass
    public void clear(){
        ApiMethodUtils.delete(getOrDeleteOrUpdatePetByIdSpec.pathParam("petId",BODY.get("id")));
    }

    @Test(enabled = false, description = "отключил так как возвращает xml, а также не меняет статус пета")
    @Owner("Yauheni Sokalau")
    public void updatePetStatusByIdTest(){
        Response response = ApiMethodUtils.post(Map.of("status", "sold"),
                getOrDeleteOrUpdatePetByIdSpec.pathParam("petId", BODY.get("id")));

        Pet petFromServer = ApiMethodUtils.get(
                getOrDeleteOrUpdatePetByIdSpec.pathParam("petId", BODY.get("id"))).as(Pet.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Different code");
        softAssert.assertEquals(petFromServer.getStatus(),"sold", "Incorrect status");

        softAssert.assertAll();
    }

    @Test
    @Owner("Yauheni Sokalau")
    public void updatePetStatusTest(){
        Map<String, Object> requestBody = new HashMap<>(BODY);
        requestBody.put("status", "sold");

        Response response = ApiMethodUtils.put(addOrUpdatePetSpec, requestBody);

        Pet petFromServer = ApiMethodUtils.get(getOrDeleteOrUpdatePetByIdSpec.pathParam("petId", BODY.get("id")))
                .as(Pet.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Different code");
        softAssert.assertEquals(petFromServer.getStatus(),"sold", "Incorrect status");

        softAssert.assertAll();
    }
}
