package get_http_request_methods;

import base_urls.RestapiexampleBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get10 extends RestapiexampleBaseUrl {
	/*
         When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
            Make sure Rhona Davidson earns more than Herrod Chandler
           {
            "id": 7,
            "employee_name": "Herrod Chandler",
            "employee_salary": 137500,
            "employee_age": 59,
            "profile_image": ""
        },
        {
            "id": 8,
            "employee_name": "Rhona Davidson",
            "employee_salary": 327900,
            "employee_age": 55,
            "profile_image": ""
        },
     */

	@Test
	public void get10() throws InterruptedException {
		//Set the url
		spec.pathParams("first", "api",
						"second", "v1",
						"third", "employee",
						"fourth", 7);

		Response response1 = given().spec(spec).when().get("/{first}/{second}/{third}/{fourth}");
		response1.prettyPrint();

		response1.then().assertThat().statusCode(200).contentType(ContentType.JSON);

		JsonPath jsonpath1 = response1.jsonPath();

		int employee_salary1 = jsonpath1.get("data.employee_salary");
		System.out.println(employee_salary1);

		Thread.sleep(1000);
		// 2 person
		spec.pathParams("first", "api",
						"second", "v1",
						"third", "employee",
						"fourth", 8);
		Response response2 = given().spec(spec).when().get("/{first}/{second}/{third}/{fourth}");

		response2.then().assertThat().statusCode(200).contentType(ContentType.JSON);

		JsonPath jsonpath2 = response2.jsonPath();

		int employee_salary2 = jsonpath2.get("data.employee_salary");
		System.out.println(employee_salary2);

		Assert.assertTrue(employee_salary1 < employee_salary2 );


		}
	}
