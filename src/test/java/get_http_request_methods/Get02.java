package get_http_request_methods;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 extends HerokuappBaseUrl {
	/*
							Given https://restful-booker.herokuapp.com/booking/1001
							When user sends a GET request to the url
							Then HTTP status code should be 404
							And   response body contains "Not Found"
							And status line should be HTTP/1.1 404 Not Found
							And body does not contain "techproed"
							And Server is "Cowboy"
							 */
	@Test
	public void get02(){
		//Set the url
		spec.pathParams("first", "booking", "second", 1001);

		Response response = given().spec(spec).when().get("/{first}/{second}");

		response.prettyPrint();

		response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

		assertTrue(response.asString().contains("Not Found"));

		assertFalse(response.asString().contains("techproed"));

		assertTrue(response.header("Server").contains("Cowboy"));

		assertEquals("Cowboy", response.header("Server") );

		System.out.println(response.header("Server"));
	}


}
