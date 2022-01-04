package get_http_request_methods;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {
	/*
    Given
           https://restful-booker.herokuapp.com/booking/3
    When
         user sends a request to the url
    Then
         HTTP Status code should be 200
     And
         ContentType should be JSON
     And
        Status Line should be HTTP/1.1 200 OK
     */
	@Test
	public void get01(){

		//Set the url
		String url = "https://restful-booker.herokuapp.com/booking/7";
		//This is the primitive way and not recommended
		//Set the expected data
		//Type the automation script to Send the request
		Response response = given().when().get(url);
		response.prettyPrint();
		//When we run the scenario and have multiple errors, but we see only one error message, this means we are using "Hard Assertion"
		//When we have multiple errors and it shows all error messages, then we use "Soft Assertion"
		//Do the assertions
		response.then().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");


	}

}
