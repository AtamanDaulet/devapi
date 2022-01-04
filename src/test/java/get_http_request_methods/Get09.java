package get_http_request_methods;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Todo1InterviewQuestion;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get09 extends HerokuappBaseUrl {
/*    When
            I send GET Request to https://restful-booker.herokuapp.com/booking/1
        Then
            Response body should be like that;
            {
                "firstname": "Eric",
                "lastname": "Jones",
                "totalprice": 355,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2020-11-09",
                    "checkout": "2017-04-30"
                 }
            }
  */

	@Test
	public void get09() {
		//Set the url
		spec.pathParams("first", "booking",
						"second", "10");

		//Set the expected data
		Map<String, Object> expectedBookingDates = new HashMap<>();
		expectedBookingDates.put("checkin", "2016-12-08");
		expectedBookingDates.put("checkout", "2017-09-24");

		Map<String, Object> expectedDataMap = new HashMap<>();
		expectedDataMap.put("firstname", "Eric");
		expectedDataMap.put("lastname", "Ericsson");
		expectedDataMap.put("totalprice", 713);
		expectedDataMap.put("depositpaid",  false);
		expectedDataMap.put("bookingdates",  expectedBookingDates);


		Response response = given().spec(spec).when().get("/{first}/{second}");

		response.prettyPrint();

		Map<String, Object> actualDataMap = response.as(HashMap.class);

		Assert.assertEquals("The data does not match!",
						expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
		Assert.assertEquals("The data does not match!",
						expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
		Assert.assertEquals("The data does not match!",
						expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
		Assert.assertEquals("The data does not match!",
						expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));

		System.out.println(expectedBookingDates.get("checkin"));
		System.out.println(((Map)actualDataMap.get("bookingdates")).get("checkin"));

		Assert.assertEquals("The data does not match!",
						expectedBookingDates.get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin"));
		Assert.assertEquals("The data does not match!",
						expectedBookingDates.get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));
	}

}
