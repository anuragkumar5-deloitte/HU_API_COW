import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static org.testng.Assert.assertEquals;

public class SpaceTypeTest {
    static Logger log = Logger.getLogger(String.valueOf(SpaceTypeTest.class));
    @Test(priority = 11)
    public void getAllSpaceTypesTest() {
        Response response = given().log().all().baseUri("https://hu-spacecorp-urtjok3rza-wl.a.run.app")
                .when().get("/api/spaceTypes ").then().log().all().contentType(ContentType.HTML).extract()
                .response();
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.header("Server"), "Google Frontend");
        assertEquals(response.header("content-type"), "text/html; charset=utf-8");
        XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
        assertEquals(htmlPath.getString("html.head.title"), "React App");
        assertEquals(htmlPath.getString("html.body.noscript"), "You need to enable JavaScript to run this app.");
        log.info("Getting all space types");

    }
}