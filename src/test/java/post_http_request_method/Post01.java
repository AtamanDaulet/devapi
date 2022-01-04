package post_http_request_method;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post01 extends HerokuappBaseUrl {
	/*
        When
            I send POST Request to the Url https://restful-booker.herokuapp.com/booking
            with the request body {
                                    "firstname": "Atabek",
                                    "lastname": "Akbalaev",
                                    "totalprice": 11111,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2021-09-09",
                                        "checkout": "2021-09-21"
                                     }
                                  }
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 11,
                                                "booking": {
                                                    "firstname": "Atabek",
                                                    "lastname": "Akbalaev",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */
	@Test
	public void post01() {
		//Set the url
		spec.pathParam("first", "booking");

		//Set the expected data
		Map<String, Object> expectedBookingDates = new HashMap<>();
		expectedBookingDates.put("checkin", "2022-09-09");
		expectedBookingDates.put("checkout", "2022-09-21");

		Map<String, Object> expectedBooking = new HashMap<>();
		expectedBooking.put("firstname", "Atabek");
		expectedBooking.put("lastname", "Akbalaev");
		expectedBooking.put("totalprice", 11111);
		expectedBooking.put("depositpaid",  true);
		expectedBooking.put("bookingdates",  expectedBookingDates);

		/*  Dont need, Id generate automaticaly

		Map<String, Object> expectedDataMap = new HashMap<>();
		expectedDataMap.put("bookingid", 11);
		expectedDataMap.put("booking", expectedBooking);*/


		//Sends the Get request and get the response
		Response response = given().
						spec(spec).
						contentType(ContentType.JSON).
						body(expectedBooking).
						when().
						post("/{first}");

		response.prettyPrint();

		response.then().assertThat().statusCode(200);

		Map<String, Object> actualData = response.as(HashMap.class);

		Assert.assertEquals("The data does not match!",
						expectedBooking.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));


	}
}
