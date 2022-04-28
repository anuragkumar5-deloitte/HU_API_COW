import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static io.restassured.RestAssured.*;


public class CreateSpacesTest {
    @Test(priority = 1)
    public void GetAllSpaces() {
        baseURI = "https://hu-spacecorp-back-urtjok3rza-wl.a.run.app/api";
        //Request object
        RequestSpecification httpRequest = given();
        //Response object
//        Response response= httpRequest.request(Method.GET,"/spaces");
        Response response = given().
                when().
                get("/spaces").
                then().extract().response();
        //print response
        String responseBody = response.toString();
        System.out.println("response body is " + responseBody);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.toString());

        }
        given().
                when().
                get("/spaces").
                then().
                body(matchesJsonSchemaInClasspath("json_getAllSpace_typicode.json"));

    }

    @Test(priority = 2)
    public void GetSpacesDetailsByID() {
        baseURI = "https://hu-spacecorp-back-urtjok3rza-wl.a.run.app/api";
        int id = 1;
        //Request object
        RequestSpecification httpRequest = given();
        //Response object
//        Response response= httpRequest.request(Method.GET,"/spaces");
        Response response = given().
                when().
                get("/spaces/getBySpaceId?id=" + id).
                then().extract().response();
        //print response
        String responseBody = response.toString();
        System.out.println("response body is " + responseBody);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println("jsonobject " + jsonObject.get("space_id"));
            Assert.assertEquals(jsonObject.get("space_id").toString(), String.valueOf(id));
        }
        given().
                when().
                get("/spaces/getBySpaceId?id=" + id).
                then().
                body(matchesJsonSchemaInClasspath("json_getAllSpace_typicode.json"));

    }

    @Test(priority = 3)
    public void GetSpaceDetailsBySpaceTypeName() {
        baseURI = "https://hu-spacecorp-back-urtjok3rza-wl.a.run.app/api";
        //Request object
        RequestSpecification httpRequest = given();
        //Response object
//        Response response= httpRequest.request(Method.GET,"/spaces");
        Response response = given().
                when().
                get("/spaces/getByTypeName?name=cabin").
                then().extract().response();
        //print response
        String responseBody = response.toString();
        System.out.println("response body is " + responseBody);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.toString());

        }
        given().
                when().
                get("/spaces/getByTypeName?name=cabin").
                then().
                body(matchesJsonSchemaInClasspath("json_getAllSpace_typicode.json"));


    }

    @Test(priority = 4)
    public void GetTopNRatedSpacesDetails() {
        baseURI = "https://hu-spacecorp-back-urtjok3rza-wl.a.run.app/api";
        //Request object
        RequestSpecification httpRequest = given();
        //Response object
//        Response response= httpRequest.request(Method.GET,"/spaces");
        Response response = given().
                when().
                get("/spaces/getTopRatedSpaces?limit=3").
                then().extract().response();
        //print response
        String responseBody = response.toString();
        System.out.println("response body is " + responseBody);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.toString());
        }
        given().
                when().
                get("/spaces/getTopRatedSpaces?limit=3").
                then().
                body(matchesJsonSchemaInClasspath("json_NratedSpaceDetails_typicode.json"));
    }

    @Test(priority = 5)
    public void GetSpaceDetailsWithAverageRatingUsingSpaceID() {
        baseURI = "https://hu-spacecorp-back-urtjok3rza-wl.a.run.app/api";
        int id = 4;
        //Request object
        RequestSpecification httpRequest = given();
        //Response object
//        Response response= httpRequest.request(Method.GET,"/spaces");
        Response response = given().
                when().
                get("/spaces/getAverageRatingBySpaceId?id=4").
                then().extract().response();
        //print response
        String responseBody = response.toString();
        System.out.println("response body is " + responseBody);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.toString());
            Assert.assertEquals(jsonObject.get("id").toString(), String.valueOf(id));
        }
        given().
                when().
                get("/spaces/getAverageRatingBySpaceId?id=4").
                then().
                body(matchesJsonSchemaInClasspath("json_GetSpace_AvgRating_typicode.json"));
    }

    @Test(priority = 6)
    public void createspaces() throws IOException {
        CreateSpaces cs = new CreateSpaces();
        RestAssured.useRelaxedHTTPSValidation();
        String name = cs.sendData(0, 1, 0);
        String type = cs.sendData(0, 1, 1);
        String price = cs.sendData(0, 1, 2);
        String address = cs.sendData(0, 1, 3);
        String start_time = cs.sendData(0, 1, 4);
        String end_time = cs.sendData(0, 1, 5);
        String owner_id = cs.sendData(0, 1, 6);
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("type", type);
        object.put("price", price);
        object.put("address", address);
        object.put("start_time", start_time);
        object.put("end_time", end_time);
        object.put("owner_id", owner_id);
        String jsonAsString = object.toString();
        Response response =
                given()
                        .baseUri("https://hu-spacecorp-back-urtjok3rza-wl.a.run.app").body(jsonAsString)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/api/spaces/create")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .statusLine("HTTP/1.1 200 OK")
                        .header("content-type", "application/json; charset=utf-8")
                        .log().all()
                        .extract()
                        .response();
        JSONArray jsonAsArray = new JSONArray(response.asString());
        boolean flag = false;
        JSONObject obj = jsonAsArray.getJSONObject(0);
        if (obj.get("lastSpaceId").equals(101)) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }
}


