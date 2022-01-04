package put_http_request_method;

import base_urls.TypicodeBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Todo1InterviewQuestion;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Put01 extends TypicodeBaseUrl {
	/*
         When
            I send PUT Request to the Url https://jsonplaceholder.typicode.com/todos/198
            with the PUT Request body like {
                                            "userId": 21,
                                            "title": "Wash the dishes",
                                            "completed": false
                                           }
         Then
           Status code is 200
           And response body is like   {
                                        "userId": 21,
                                        "title": "Wash the dishes",
                                        "completed": false,
                                        "
                                       }
     */
	@Test
	public void put01() {
//Set the url
		spec.pathParams("first", "todos", "second", 198);

		//Set the expected data
		//1 way
		Map<String, Object> expectedBody = new HashMap<>();
		expectedBody.put("userId", 21);
		expectedBody.put("title", "Wash the dishes 777");
		expectedBody.put("completed", false);
		//expectedBody.put("id",  201);

		// 2 way                     //(int userId, int id, String title, boolean completed)
		Todo1InterviewQuestion todo = new Todo1InterviewQuestion(21,198,"Wash the dishes 777",false);

		Response response = given().
						spec(spec).
						contentType(ContentType.JSON).
						body(expectedBody).
						when().
						put("/{first}/{second}");

		response.prettyPrint();

		// 1 way validation
		response.then().assertThat().statusCode(200).body(
						"userId", equalTo(21) ,
						"title",equalTo("Wash the dishes 777"),
						"completed", equalTo(false)
		);

		// 2 way validation
		Map<String, Object> actualData = response.as(HashMap.class);

		Assert.assertEquals("The data does not match!",
						expectedBody.get("userId"), actualData.get("userId"));
		Assert.assertEquals("The data does not match!",
						expectedBody.get("title"), actualData.get("title"));
		Assert.assertEquals("The data does not match!",
						expectedBody.get("completed"), actualData.get("completed"));

		// 3 way validation
		Todo1InterviewQuestion todoResponse = response.as(Todo1InterviewQuestion.class);

		Assert.assertEquals("The data does not match!",
						todo.getUserId(), todoResponse.getUserId());
		Assert.assertEquals("The data does not match!",
						todo.getTitle(), todoResponse.getTitle());
		Assert.assertEquals("The data does not match!",
						todo.isCompleted(), todoResponse.isCompleted());

		// 4 way
		JsonPath json = response.jsonPath();

		Assert.assertEquals("The data does not match!",
						todo.getUserId(), json.getInt("userId"));
		Assert.assertEquals("The data does not match!",
						todo.getTitle(), json.getString("title"));
		Assert.assertEquals("The data does not match!",
						todo.isCompleted(), json.getBoolean("completed"));

	}

}
