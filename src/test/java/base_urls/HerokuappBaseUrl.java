package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuappBaseUrl {
	//We will use ReSpec for storing base url into it
	protected RequestSpecification spec;

	@Before
	public void setup(){
		spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
	}
}
