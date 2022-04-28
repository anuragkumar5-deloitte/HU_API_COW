import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getreview {
    //method to get the status code of the response payload
    @Test(priority = 7)
    public void test_statuscode()
    {
        given().
                baseUri("https://hu-spacecorp-back-urtjok3rza-wl.a.run.app").
                log().all().
                when().
                get("/api/reviews/getBySpaceId?space_id=3").
                then().
                log().all().
                statusCode(200);

    }

}
