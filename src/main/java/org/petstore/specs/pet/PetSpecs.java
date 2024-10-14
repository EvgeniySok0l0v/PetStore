package org.petstore.specs.pet;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.petstore.specs.BaseSpec;

import static org.petstore.constants.ApiConfig.BASE_URI;
import static org.petstore.endpoints.PetEndpoints.ADD_OR_UPDATE_PET;
import static org.petstore.endpoints.PetEndpoints.GET_OR_DELETE_OR_UPDATE_PET_BY_ID;

public class PetSpecs extends BaseSpec {

    public static RequestSpecification addOrUpdatePetSpec = new RequestSpecBuilder()
            .addRequestSpecification(baseRequest)
            .setBaseUri(BASE_URI)
            .setBasePath(ADD_OR_UPDATE_PET)
            .build();

    public static RequestSpecification getOrDeleteOrUpdatePetByIdSpec = new RequestSpecBuilder()
            .addRequestSpecification(baseRequest)
            .setBaseUri(BASE_URI)
            .setBasePath(GET_OR_DELETE_OR_UPDATE_PET_BY_ID)
            .build();
}
