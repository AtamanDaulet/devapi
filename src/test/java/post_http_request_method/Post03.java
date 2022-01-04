package post_http_request_method;

import base_urls.TypicodeBaseUrl;
import data.TypicodeData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Post03 extends TypicodeBaseUrl {
	/*
		When
				I send POST Request to the Url https://jsonplaceholder.typicode.com/todos
				with the request body {
																"userId": 55,
																"title": "Tidy your room",
																"completed": false
															 }
		Then
				Status code is 201
				And response body is like {
																		"userId": 55,
																		"title": "Tidy your room",
																		"completed": false,
																		"id": 201
																	}
			 */
	@Test
	public void post03() {
//Set the url
		spec.pathParam("first", "todos");

		//Set the expected data
		/*Map<String, Object> expectedBody = new HashMap<>();
		expectedBody.put("userId", 777);
		expectedBody.put("title", "Tidy your room 777");
		expectedBody.put("completed", false);*/

		//Map<String, Object> expectedData = TypicodeData.expectedDataSetUp();

		Map<String, Object> expectedData = TypicodeData.expectedDataSetUp(99, "Tidy your room 99",false );


		Response response = given().
						spec(spec).
						auth().basic("admin", "1234").
						contentType(ContentType.JSON).
						body(expectedData).
						when().
						post("/{first}");

		response.prettyPrint();

		response.then().assertThat().statusCode(201).
						body("userId", equalTo(99),
										"title", equalTo("Tidy your room 99"),
										"completed", equalTo(false));
	}
}
