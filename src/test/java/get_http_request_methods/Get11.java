package get_http_request_methods;

import base_urls.RestapiexampleBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import pojos.employees.Employee;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Get11 extends RestapiexampleBaseUrl {
	/*
      When
        I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
       Status code should be 200
       Use Gson and ObjectMapper
       make sure you have 24 records for data
  */

	@Test
	public void get11() throws IOException {
		//Set the url
		spec.pathParams("first", "api",
						"second", "v1",
						"third", "employees");

		Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
		response.prettyPrint();

		response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

		ObjectMapper obj = new ObjectMapper();

		//This is a converter from Java to Json, Json to Java
		Employee employees = obj.readValue(response.asString(),Employee.class);
		for (int i = 0; i < employees.getData().size(); i++) {
			System.out.println("The person "+i+" is "+ employees.getData().get(i).getEmployee_name());
		}
		Assert.assertTrue("The data size does not Match!", employees.getData().size() == 24);
	}

	@Test
	public void get11D(){
		//Set the url
		spec.pathParams("first", "api",
						"second", "v1",
						"third", "employees");

		Response response = given().spec(spec).when().get("/{first}/{second}/{third}");

		Gson gson = new Gson();

		Employee emp = gson.fromJson(response.asString(), Employee.class);

		System.out.println(emp.getData().size());




	}


}