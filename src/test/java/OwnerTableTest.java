import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class OwnerTableTest {
    @BeforeClass
    public void owner() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://hu-spacecorp-back-urtjok3rza-wl.a.run.app").
                addHeader("Content-type", "application/json");
        RestAssured.requestSpecification = requestSpecBuilder.build();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test(priority = 8)
    public void createOwner() throws IOException {
        ReadingExcel excel = new ReadingExcel();
        RestAssured.useRelaxedHTTPSValidation();
        String username = excel.sendData(0, 1, 0);
        String password = excel.sendData(0, 1, 1);
        String name = excel.sendData(0, 1, 2);
        String email = excel.sendData(0, 1, 3);
        String dob = excel.sendData(0, 1, 4);
        String contact = excel.sendData(0, 1, 5);
        String bio = excel.sendData(0, 1, 6);

        JSONObject object = new JSONObject();
        object.put("username", username);
        object.put("password", password);
        object.put("name", name);
        object.put("email", email);
        object.put("dob", dob);
        object.put("contact", contact);
        object.put("bio", bio);
        String jsonAsString = object.toString();
        Response response =
                given()
                        .body(jsonAsString)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("api/owners/create")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .statusLine("HTTP/1.1 200 OK")
                        .header("content-type", "application/json; charset=utf-8")
                        .log().all()
                        .extract()
                        .response();
        String jsonString = response.asString();
        Assert.assertTrue(jsonString.contains("Owner created!"));

    }

    @Test(priority = 9)
    public void getAllOwners() {
        RestAssured.useRelaxedHTTPSValidation();
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/api/owners")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .statusLine("HTTP/1.1 200 OK")
                        .header("content-type", "application/json; charset=utf-8")
                        .log().all()
                        .extract().response();

        JSONArray jsonAsArray = new JSONArray(response.asString());
        boolean flag = false;
        for (int i = 0; i < jsonAsArray.length(); i++) {
            JSONObject obj = jsonAsArray.getJSONObject(i);
            if (obj.get("username").equals("aaa") || obj.get("username").equals("owner1")
                    || obj.get("username").equals("sam007") || obj.get("username").equals("admin")) {
                flag = true;
            }
        }
        Assert.assertTrue(flag);
    }

    //getting owner by id
    @Test(priority = 3)
    public void getOwnerById() {
        System.out.println("getting owner by id");
        System.out.println("----------------------------------------");
        RestAssured.useRelaxedHTTPSValidation();
        Response response =
                given()
                        .param("id", "5")
                        .when()
                        .get("/api/owners/getOwnerById")
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
        if (obj.get("id").equals(5) && obj.get("username").equals("owner1")) {
            flag = true;
        }
        Assert.assertTrue(flag);
        System.out.println("----------------------------------------");
    }

    //delete owner by id
    @Test(priority = 4)
    public void deleteOwnerById() {
        RestAssured.useRelaxedHTTPSValidation();
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .delete("/api/owners/getOwnerById/27")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .statusLine("HTTP/1.1 200 OK")
                        .header("content-type", "application/json; charset=utf-8")
                        .log().all()
                        .extract().response();
        String jsonAsStr = response.asString();
        Assert.assertTrue(jsonAsStr.contains("Owner deleted!"));
    }

    //delete all owners
//@Test(priority = 5)
    public void deleteALlOwner() {
        RestAssured.useRelaxedHTTPSValidation();
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .delete("api/owners")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .statusLine("HTTP/1.1 200 OK")
                        .header("content-type", "application/json; charset=utf-8")
                        .log().all()
                        .extract().response();
        String jsonAsStr = response.asString();
        Assert.assertTrue(jsonAsStr.contains(" deleted"));
    }

    //verifying an owner with username and password parameters
    @Test(priority = 6)
    public void verifyAnOwner() {
        RestAssured.useRelaxedHTTPSValidation();
        JSONObject jsObj = new JSONObject();
        jsObj.put("username", "admin");
        jsObj.put("password", "admin");
        String jsStr = jsObj.toString();
        Response response =
                given()
                        .body(jsStr)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/api/ownerLogin")
                        .then()
                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .header("content-type", "application/json; charset=utf-8")
                        .log().all()
                        .extract().response();
        String jsonStr = response.asString();
        Assert.assertTrue(jsonStr.contains("Record found!"));
    }
}