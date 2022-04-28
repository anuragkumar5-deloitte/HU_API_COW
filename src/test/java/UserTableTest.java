import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserTableTest {

    String url = "https://hu-spacecorp-back-urtjok3rza-wl.a.run.app";
    String jwt = "";
    RequestSpecification req;
    ResponseSpecification res;

    @BeforeTest
    void setupTest() {
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setBaseUri(url).addHeader("Content-Type", "application/json; charset=UTF-9");
        req = RestAssured.with().spec(reqBuilder.build());
        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder().expectContentType(ContentType.JSON);
        res = specBuilder.build();
    }
//Getting all the Users
    @Test(priority = 1)
    public void getUser() {

        RestAssured.useRelaxedHTTPSValidation();
        Response response =
                given()
                        .baseUri("https://hu-spacecorp-back-urtjok3rza-wl.a.run.app")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/api/users")
                        .then()
                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .log().all().extract().response();

        JSONArray arr = new JSONArray(response.asString());
        boolean flag = false;
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            if (obj.get("id").equals(2) && obj.get("username").equals("random_user")||obj.get("id").equals(29) && obj.get("username").equals("abc")) {
                flag = true;
            }
        }
        Assert.assertTrue(flag);
        System.out.println("ALL USERS PRINTED");
    }
    //Getting a User using specific ID
    @Test (priority = 2)
    public void getUserById(){
        RestAssured.useRelaxedHTTPSValidation();
        Response response =
                given()
                        .baseUri("https://hu-spacecorp-back-urtjok3rza-wl.a.run.app")
                        .param("id","29")
                        .when()
                        .get("/api/users/getById")
                        .then()
                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .header("content-type","application/json; charset=utf-8")
                        .log().all().extract().response();
        JSONArray jsonAsArray = new JSONArray(response.asString());
        boolean flag= false;
        JSONObject obj = jsonAsArray.getJSONObject(0);
        if(obj.get("id").equals(29) && obj.get("username").equals("abc"))
        {
            flag = true;
        }
        Assert.assertTrue(flag);
    }
    //Creating a User
        @Test(priority = 3)
    public void createUser() throws IOException {
        Requests.ReadingExcel excel = new Requests.ReadingExcel();
        RestAssured.useRelaxedHTTPSValidation();
        String username=excel.sendData(0, 1, 0);
        System.out.println(username);
        String password =excel.sendData(0, 1, 1);
        System.out.println(password);
        String name = excel.sendData(0, 1, 2);
        System.out.println(name);
        String email =excel.sendData(0, 1, 3);
        System.out.println(email);
        String dob=excel.sendData(0, 1, 4);
        System.out.println(dob);
        String contact= excel.sendData(0, 1, 5);
        System.out.println(contact);
        JSONObject object = new JSONObject();
        object.put("username",username);
        object.put("password",password);
        object.put("name",name);
        object.put("email",email);
        object.put("dob",dob);
        object.put("contact",contact);
        String jsonAsString = object.toString();
        Response response =
                given()
                        .baseUri("https://hu-spacecorp-back-urtjok3rza-wl.a.run.app")
                        .body(jsonAsString)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/api/users/create")
                        .then()
                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .header("content-type","application/json; charset=utf-8")
                        //.log().all()
                        .extract()
                        .response();
        String jsonString = response.asString();
        System.out.println(jsonString);

//            JSONArray arr= new JSONArray(response.asString());
//        boolean flag= false;
//        for (int i =0; i<arr.length();i++){
//            JSONObject obj = arr.getJSONObject(i);
//            if(obj.get("name").equals("raju") && obj.get("username").equals("random_user")){
//                flag = true;
//            }
//        }
//        Assert.assertTrue(flag);
        Assert.assertEquals(jsonString.contains("User created!"),true);
}

}
