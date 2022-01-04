package patch_http_request_method;

import base_urls.TypicodeBaseUrl;
import data.TypicodeData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Patch01 extends TypicodeBaseUrl {
 /*
  When
      I send PATCH Request to the Url https://jsonplaceholder.typicode.com/todos/198
      with the PUT Request body like {
                                      "title": "Tidy your room"
                                     }
 Then
     Status code is 200
     And response body is like  {
                                  "userId": 10,
                                  "title": "Tidy your room",
                                  "completed": true,
                                  "id": 198
                                }
     */

	@Test
	public void patch01() {
//Set the url
		spec.pathParams("first", "todos", "second", "198");

		//Set the expected data
		Map<String, Object> expectedData = new HashMap<>();
		//expectedData.put("title", "Tidy your room 777 patch");
		expectedData.put("userId", 20);

		//Map<String, Object> expectedData = TypicodeData.expectedDataSetUp();

		//Map<String, Object> expectedData = TypicodeData.expectedDataSetUp(99, "Tidy your room 99",false );


		Response response = given().
						spec(spec).
						contentType(ContentType.JSON).
						body(expectedData).
						when().
						patch("/{first}/{second}");

		response.prettyPrint();

		response.then().assertThat().statusCode(200).
						body("userId", equalTo(20),
										"title", equalTo("quis eius est sint explicabo"),
										"completed", equalTo(true));
	}
}
