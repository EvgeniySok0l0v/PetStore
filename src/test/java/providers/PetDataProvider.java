package providers;

import org.petstore.model.pet.Pet;
import org.testng.annotations.DataProvider;

import java.util.Map;

public class PetDataProvider {

    public static final Map<String, Object> BODY = Map.of(
            "id", 1L,
            "category", Map.of(
                    "id", 0,
                    "name", "category1"
            ),
            "name", "Name1",
            "photoUrls", new String[]{"url1", "url2"},
            "tags", new Object[]{
                    Map.of(
                            "id", 0,
                            "name", "tag1"
                    ),
                    Map.of(
                            "id", 1,
                            "name", "tag2"
                    )
            },
            "status", "available"
    );

    @DataProvider(name = "petProvider")
    public static Object[][] petProvider() {
        return new Object[][]{
                {
                        new Pet(
                                12,
                                new Pet.Category(0, "category1"),
                                "Name1",
                                new String[]{"url1", "url2"},
                                new Pet.Tag[]{new Pet.Tag(0, "tag1")},
                                "available")},
                {
                        new Pet(
                                34,
                                new Pet.Category(1, "category2"),
                                "Name2",
                                new String[]{"url3", "url4"},
                                new Pet.Tag[]{new Pet.Tag(1, "tag2")},
                                "available")
                },
                {
                        new Pet(
                                56,
                                new Pet.Category(2, "category3"),
                                "Name3",
                                new String[]{"url5"},
                                new Pet.Tag[]{new Pet.Tag(2, "tag3")},
                                "available")}
        };
    }

    @DataProvider(name = "mapModificationProvider")
    public static Object[][] mapModificationProvider() {
        return new Object[][]{
                {"id", "any string"},
                {"name", new String[]{"name"}},
                {"status", new String[]{"name"}},
                {"category", "str"},
                {"tags", "str"},
                {"photoUrls", "str"},
                {"category", Map.of(
                        "id", "str"
                )},
                {"tags", Map.of(
                        "id", "str"
                )}
        };
    }
}
