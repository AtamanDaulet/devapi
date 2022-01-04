package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RestapiexampleBaseUrl {
	protected RequestSpecification spec;

	@Before
	public void setup(){
		spec = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com").build();
	}
}
