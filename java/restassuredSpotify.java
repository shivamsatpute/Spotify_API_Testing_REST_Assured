import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class restassuredSpotify {
    public String token = "";
    public static String userID;
    @BeforeTest
    public void setUp(){
        token = "Bearer BQCnxg2Vugh7ltuigH99RtvoXCje9ys6Vjm6hWUJMj1EBgBTO7HXmzRvT3Xd3SY6OkhHZP48dZ0Eh95upAbONQKxMh7x9zGM6tru3KOGJbXijnN7sxKaWTHmp78kVfJG9l51YJEnA3noiuMDztfDTDN6NgeIKC0tAaU4_gfUSo8qTMkP37HoDhl4PIEn-lVI4N7-S-pLnDiUSF77ZjkFOady4jWz1u-0uuK8wkf3ZzjiZvJCNT60AL-k1Fwp8cGHvvGyXA8EOGvOaz62VuS-Nc6N-QxDkE260lFwrHaD";
    }
    @Test(priority = 1)
    public void getUser_Profile(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/users/"+userID+"/");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
    @Test(priority = 0)
    public void getCurrent_userProfile(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/me");
        System.out.println("response using pout"+ response);
        response.prettyPrint();
        userID = response.path("id");
        System.out.println("UserID :"+ userID);
        response.then().assertThat().statusCode(200);
    }
    @Test(priority = 1)
    public void create_playlist(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",token)
                .body("{\n" +
                        "  \"name\": \"punjabi_songs\",\n" +
                        "  \"description\": \"New playlist description\",\n" +
                        "  \"public\": false\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/users/lgwefv6m4s943fg7sxj5afhz8/playlists");
        response.prettyPrint();
    }
    @Test(priority = 2)
    public void addItem_toPlaylist() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .queryParams("uris","spotify:track:3bugxNPh9ky4wF79y1UUTo")
                .when()
                .post("https://api.spotify.com/v1/playlists/0C0ZB848oJW2DQsO85zCSq/tracks");
        response.prettyPrint();
    }
    @Test(priority = 3)
    public void updatePlaylist_nmae() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .body("{\n" +
                        "  \"name\": \"classical\",\n" +
                        "  \"description\": \"Updated playlist description\",\n" +
                        "  \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/0C0ZB848oJW2DQsO85zCSq");
        response.prettyPrint();
    }
    @Test(priority = 4)
    public void delete_trackFrom_playlist() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .body("{\n" +
                        "  \"tracks\": [\n" +
                        "    {\n" +
                        "      \"uri\": \"spotify:track:3bugxNPh9ky4wF79y1UUTo\",\n" +
                        "      \"positions\": [\n" +
                        "        0\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/0C0ZB848oJW2DQsO85zCSq/tracks");
        response.prettyPrint();
    }
}