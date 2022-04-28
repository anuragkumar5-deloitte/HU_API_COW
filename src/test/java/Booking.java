import Reso.excelcall;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
public class Booking extends Base {
    @BeforeClass
    public void booking() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                //Specify base URI
                setBaseUri("https://hu-spacecorp-back-urtjok3rza-wl.a.run.app").
                addHeader("Content-type", "application/json").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                //Status Code Validation
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test(priority = 0)
    public void validate_pos_request() throws IOException {
        //calling excel class and creating a new object
        excelcall excel = new excelcall();
        //Request Object
        JSONObject Obj = excel.excel();
        String payload = Obj.toString();
                given().
                body(payload).
                when().
                post("/api/booking").
                then();
    }
}
