package delete_http_request_method;

import base_urls.TypicodeBaseUrl;
import data.TypicodeData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Delete01 extends TypicodeBaseUrl {
/*
        When
            I send DELETE Request to the Url https://jsonplaceholder.typicode.com/todos/198
        Then
            Status code is 200
            And Response body is {}
    */

	@Test
	public void delete01() {
//Set the url
		spec.pathParams("first", "todos", "second", "198");

		Response response = given().
						spec(spec).
						when().
						delete("/{first}/{second}");

		response.prettyPrint();

		response.then().assertThat().statusCode(200);
	}
}
