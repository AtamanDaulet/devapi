package post_http_request_method;

import base_urls.MedunnaBaseUrl;
import base_urls.TypicodeBaseUrl;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.medunna.Registrant;
import utilities.WriteToTxt;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post04 extends MedunnaBaseUrl {
//Send the post request to the url https://medunna.com/api/register
    /*
    create a new user for Medunna project
    status code should be 200
                {
	              "activated": true,
	              "authorities": [
	                "string"
	              ],
	              "createdBy": "string",
	              "createdDate": "2022-01-03T19:25:02.075Z",
	              "email": "string",
	              "firstName": "string",
	              "id": 0,
	              "imageUrl": "string",
	              "langKey": "string",
	              "lastModifiedBy": "string",
	              "lastModifiedDate": "2022-01-03T19:25:02.075Z",
	              "lastName": "string",
	              "login": "string",
	              "password": "string",
	              "ssn": "string"
	            }
     */

	@Test
	public void post04() {
//Set the url
		spec.pathParams("first", "api", "second", "register");

		//Set the expected data
		/*
		Registrant registrant = new Registrant();
		registrant.setFirstName("Daulet");
		registrant.setLastName("Ataman");
		registrant.setLangKey("en");
		registrant.setPassword("123456D");
		registrant.setEmail("daulet99ataman@gmail.com");
		registrant.setLogin("daulet99");
		registrant.setSsn("123-45-6789");*/

		Faker faker = new Faker();

		String fileName = "C:/Users/hi/IdeaProjects/falldevapi/test_data/medunna_registrant_data.txt";

		//Set the expected data
		Registrant registrant = new Registrant();
		registrant.setFirstName(faker.name().firstName());
		registrant.setLastName(faker.name().lastName());
		registrant.setLangKey("en");
		registrant.setPassword(faker.internet().password(8,18,true,true));
		registrant.setEmail(faker.name().firstName() + faker.name().lastName() +"@gmail.com");
		registrant.setLogin(faker.name().firstName() + faker.name().lastName());
		registrant.setSsn(faker.idNumber().ssnValid());

		Response response = given().
						spec(spec).
						contentType(ContentType.JSON).
						body(registrant).
						when().
						post("/{first}/{second}");

		response.prettyPrint();

		WriteToTxt.saveRegistrantData(fileName, registrant);

		response.then().assertThat().statusCode(200);


	}

	@Test
	public void post04_2() {
		Faker faker = new Faker();

		String fileName = "C:/Users/hi/IdeaProjects/falldevapi/test_data/medunna_registrant_data.txt";
		String endPoint = "https://medunna.com/api/register";

		//Set the expected data
		Registrant registrant = new Registrant();
		registrant.setFirstName(faker.name().firstName());
		registrant.setLastName(faker.name().lastName());
		registrant.setLangKey("en");
		registrant.setPassword(faker.internet().password(8,18,true,true));
		registrant.setEmail(faker.name().firstName() + faker.name().lastName() +"@gmail.com");
		registrant.setLogin(faker.name().firstName() + faker.name().lastName());
		registrant.setSsn(faker.idNumber().ssnValid());


		Response response = given().
						contentType(ContentType.JSON).
						body(registrant).
						when().
						post(endPoint);

		response.prettyPrint();

		WriteToTxt.saveRegistrantData(fileName, registrant);

		response.then().assertThat().statusCode(200);

	}
}