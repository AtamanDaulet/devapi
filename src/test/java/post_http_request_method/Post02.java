package post_http_request_method;

import base_urls.TypicodeBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post02 extends TypicodeBaseUrl {
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
	public void post02() {
//Set the url
		spec.pathParam("first", "todos");

		//Set the expected data
		Map<String, Object> expectedBody = new HashMap<>();
		expectedBody.put("userId", 55);
		expectedBody.put("title", "Tidy your room");
		expectedBody.put("completed", false);
		//expectedBody.put("id",  201);

		Response response = given().
						spec(spec).
						contentType(ContentType.JSON).
						body(expectedBody).
						when().
						post("/{first}");

		response.prettyPrint();

		response.then().assertThat().statusCode(201);

		Map<String, Object> actualData = response.as(HashMap.class);

		Assert.assertEquals("The data does not match!",
						expectedBody.get("userId"), actualData.get("userId"));
		Assert.assertEquals("The data does not match!",
						expectedBody.get("title"), actualData.get("title"));
		Assert.assertEquals("The data does not match!",
						expectedBody.get("completed"), actualData.get("completed"));
	//	Assert.assertEquals("The data does not match!",
	//					expectedBody.get("id"), actualData.get("id"));
	}
}
