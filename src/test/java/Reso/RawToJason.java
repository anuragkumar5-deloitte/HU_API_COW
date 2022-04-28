package Reso;

import io.restassured.path.json.JsonPath;

public class RawToJason {

    public static JsonPath rawToJason(String response)
    {
        JsonPath js = new JsonPath(response);
        return  js;
    }
}
