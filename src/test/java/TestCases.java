import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONArray;
import org.json.JSONObject;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class TestCases {
    @Test
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
        System.out.println("response body is "+responseBody);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject;
        for (int i =0; i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.toString());

        }
        given().
                when().
                get("/spaces").
                then().
                body(matchesJsonSchemaInClasspath("json_getAllSpace_typicode.json"));

    }
    @Test
    public void GetSpacesDetailsByID() {
        baseURI = "https://hu-spacecorp-back-urtjok3rza-wl.a.run.app/api";
        int id = 1;
        //Request object
        RequestSpecification httpRequest = given();
        //Response object
//        Response response= httpRequest.request(Method.GET,"/spaces");
        Response response = given().
                when().
                get("/spaces/getBySpaceId?id="+id).
                then().extract().response();
        //print response
        String responseBody = response.toString();
        System.out.println("response body is "+responseBody);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject;
        for (int i =0; i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println("jsonobject "+jsonObject.get("space_id"));
            Assert.assertEquals(jsonObject.get("space_id").toString(),String.valueOf(id));
        }
        given().
                when().
                get("/spaces/getBySpaceId?id="+id).
                then().
                body(matchesJsonSchemaInClasspath("json_getAllSpace_typicode.json"));

    }
    @Test
    public void GetSpaceDetailsBySpaceTypeName(){
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
        System.out.println("response body is "+responseBody);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject;
        for (int i =0; i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.toString());

        }
        given().
                when().
                get("/spaces/getByTypeName?name=cabin").
                then().
                body(matchesJsonSchemaInClasspath("json_getAllSpace_typicode.json"));


    }
    @Test
    public void GetTopNRatedSpacesDetails(){
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
        System.out.println("response body is "+responseBody);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject;
        for (int i =0; i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.toString());
        }
        given().
                when().
                get("/spaces/getTopRatedSpaces?limit=3").
                then().
                body(matchesJsonSchemaInClasspath("json_NratedSpaceDetails_typicode.json"));
    }
    @Test
    public void GetSpaceDetailsWithAverageRatingUsingSpaceID(){
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
        System.out.println("response body is "+responseBody);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject;
        for (int i =0; i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.toString());
            Assert.assertEquals(jsonObject.get("id").toString(),String.valueOf(id));
        }
        given().
                when().
                get("/spaces/getAverageRatingBySpaceId?id=4").
                then().
                body(matchesJsonSchemaInClasspath("json_GetSpace_AvgRating_typicode.json"));
    }
//    @Test
//    public void CreateASpace(){
//        given()
//                .baseUri("https://hu-spacecorp-back-urtjok3rza-wl.a.run.app/api")
//                .body("")
//                .header("Content-Type","application/x-www-form-urlencoded")
//                .when()
//                .post("/spaces/create")
//                .then()
//                .statusCode(200);
//    }
//    @Test
//    public void GetEveryDetail(){
//        baseURI = "https://hu-spacecorp-back-urtjok3rza-wl.a.run.app/api";
//        //Request object
//        RequestSpecification httpRequest = given();
//        //Response object
////        Response response= httpRequest.request(Method.GET,"/spaces");
//        Response response = given().
//                when().
//                get("/spaces/getEveryDetail").
//                then().extract().response();
//        //print response
//        String responseBody = response.toString();
//        System.out.println("response body is "+responseBody);
//        //JSONArray jsonArray = new JSONArray(response.asString());
//        JSONObject jsonObject;
        //for (int i =0; i<jsonArray.length();i++){
            //jsonObject = jsonArray.getJSONObject(i);
            //System.out.println(jsonObject.toString());
        }
//        given().
//                when().
//                get("/spaces/getEveryDetail").
//                then().
//                body(matchesJsonSchemaInClasspath("json_getAllSpace_typicode.json"));



