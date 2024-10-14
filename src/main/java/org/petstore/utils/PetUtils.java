package org.petstore.utils;

import org.petstore.model.pet.Pet;

import java.util.*;

public class PetUtils {

    public static Map<String, Object> addPetBody(Pet pet){

        List<Map<String, Object>> tags = new ArrayList<>(pet.getTags().length);

        Arrays
                .stream(pet.getTags())
                .forEach(tag -> tags.add(Map.of(
                        "id", tag.getId(),
                        "name", tag.getName()
                )));

        return Map.of(
                "id", pet.getId(),
                "category", Map.of(
                        "id", pet.getCategory().getId(),
                        "name", pet.getCategory().getName()
                ),
                "name", pet.getName(),
                "photoUrls", pet.getPhotoUrls(),
                "tags", tags.toArray(),
                "status", pet.getStatus()
        );
    }
}
