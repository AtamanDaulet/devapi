package get_http_request_methods;

import base_urls.TypicodeBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Todo1InterviewQuestion;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get08TodoPojo extends TypicodeBaseUrl {
/*     Given
            https://jsonplaceholder.typicode.com/todos/2
     When I send a Get Request
     Then the actual data should be as following;
        {
        "userId": 1,
        "id": 2,
        "title": "quis ut nam facilis et officia qui",
        "completed": false
    }     */

	@Test
	public void get08() {
		//Set the url
		spec.pathParams("first", "todos",
						"second", "2");

		//Set the expected data
		Map<String, Object> expectedDataMap = new HashMap<>();
		expectedDataMap.put("userId", 1);
		expectedDataMap.put("id", 2);
		expectedDataMap.put("title",  "quis ut nam facilis et officia qui");
		expectedDataMap.put("completed",  false);


		Response response = given().spec(spec).when().get("/{first}/{second}");

		response.prettyPrint();

		Map<String, Object> actualDataMap = response.as(HashMap.class);

		Assert.assertEquals("The data does not match!",
						expectedDataMap.get("id"), actualDataMap.get("id"));

		Assert.assertEquals("The data does not match!",
						expectedDataMap.get("id"), actualDataMap.get("id"));

		Assert.assertEquals("The data does not match!",
						expectedDataMap.get("title"), actualDataMap.get("title"));

		Assert.assertEquals("The data does not match!",
						expectedDataMap.get("completed"), actualDataMap.get("completed"));


	}

	@Test
	public void get08D() {
		/*     Given
		https://jsonplaceholder.typicode.com/todos/2
		When I send a Get Request
		Then the actual data should be as following;
		{
			"userId": 1,
						"id": 2,
						"title": "quis ut nam facilis et officia qui",
						"completed": false
		}     */

		//Set the url
		spec.pathParams("first", "todos",
						"second", "2");

		Todo1InterviewQuestion expectedTodo = new Todo1InterviewQuestion(1, 2, "quis ut nam facilis et officia qui", false);

		Response response = given().spec(spec).when().get("/{first}/{second}");

		response.prettyPrint();

		Todo1InterviewQuestion actualTodo = response.as(Todo1InterviewQuestion.class);

		Assert.assertEquals("The data does not match!",
						expectedTodo.getUserId(), actualTodo.getUserId());

		Assert.assertEquals("The data does not match!",
						expectedTodo.getId(), actualTodo.getId());

		Assert.assertEquals("The data does not match!",
						expectedTodo.getTitle(), actualTodo.getTitle());

		Assert.assertEquals("The data does not match!",
						expectedTodo.isCompleted(), actualTodo.isCompleted());

	}
}
