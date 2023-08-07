package TestCases;
import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class APITest extends ExtentReporter {
	
	@DataProvider(name = "testData_01")
	public Object[][] testData_01() {
		return new Object[][]{
			{"Germany","Baden-Württemberg", "70597", "Stuttgart Degerloch"}
			// User can Include additional test data as required
		};
	}
	@Test(enabled=true,priority =1,dataProvider ="testData_01")
	public void APITest_01(String Country, String State,String postCode, String expPlace) throws IOException
	{
		test=extent.createTest("Launch Browser and verify Success : " + "API_TestCase 01");
		String baseUrl = Common.getValue("API_BaserURL");
	
		// Send a GET request to the API and get the response
		Response response = RestAssured.get(baseUrl);
		
	try {
		test.log(Status.INFO, "Response Header : " + '\n'+ get(baseUrl).then().extract().headers( ));
		           
		test.log(Status.INFO, "Response Body : " + '\n'+ (response.getBody()).asPrettyString());
		//response.then().log().all();
		
		// 1. Verify that the response status code is 200
		if( response.getStatusCode()==200)
			test.log(Status.PASS, "Response Code is Sucess - " + response.getStatusCode() );	
		else
			test.log(Status.FAIL, "Response Code not as expected - " + response.getStatusCode());	

		// 2. Verify that the content type is JSON

		if(( response.getContentType()).contains("json"))
			test.log(Status.PASS, "Validated Content Type - " + response.getContentType() );	
		else
			test.log(Status.FAIL, "Content Type is not as expected - " + response.getContentType());


		// 3. Verify that the response time is below 1s.
		if( response.getTimeIn(TimeUnit.MILLISECONDS)<=(1000))
			test.log(Status.PASS,"The time taken to fetch the response -"+ response.timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
		else
			test.log(Status.FAIL,"The time taken to fetch the response -"+ response.timeIn(TimeUnit.MILLISECONDS) + " milliseconds");

		// 4. Verify in Response - That "country" is "Germany" and "state" is "Baden-Württemberg"

		response.then()
        .statusCode(200).body("country",Matchers.equalTo(Country))
		         .body("state",Matchers.equalTo(State));
		test.log(Status.PASS,"The Country Code and State Matched");
		

		// 5. Verify in Response - For Post Code "70597" the place name has "Stuttgart Degerloch"
		 response.then()
         .statusCode(200)
         .body("places.find { it.'post code' == '" + postCode +"' }.'place name'", Matchers.equalTo(expPlace));
		test.log(Status.PASS, "Postal Code and place name is Validated Sucessfully");	

	}
	catch(Exception e) {
		test.log(Status.FAIL, "API Test Failed /n" + e);	
	}
	}
	

	@DataProvider(name = "testData_02")
	public Object[][] testData_02() {
		return new Object[][]{
			{"us", "90210", "Beverly Hills"},
			{"us", "12345", "Schenectady"},
			{"ca", "B2R", "Waverley"}
			// User can Include additional test data as required
		};
	}

	@Test(enabled=true,priority =
			2,dataProvider = "testData_02")
	public void APITest_02(String country, String postCode, String expPlace) throws IOException {

		test=extent.createTest("Launch Browser and verify Success : " + "API_TestCase 02");
		// Base URL for the API
		
		String baseUrl = Common.getValue("API_URI_02") + country + "/" + postCode;
		// Send GET request and get the response
				Response response = RestAssured.get(baseUrl);
        test.log(Status.INFO, "Response Header : " + '\n'+ get(baseUrl).then().extract().headers());
        
		test.log(Status.INFO, "Response Body : " + '\n'+ response.asPrettyString() );
		
	    //response.then().log().all();
		try {
		// 1. Verify that the response status code is 200
				if( response.getStatusCode()== 200)
					test.log(Status.PASS, "Response Code is Sucess - " + response.getStatusCode() );	
				else
					test.log(Status.FAIL, "Response Code not as expected - " + response.getStatusCode());	

				// 2. Verify that the content type is JSON

				if( response.getContentType().contains("json"))
					test.log(Status.PASS, "Validated Content Type - " + response.getContentType() );	
				else
					test.log(Status.FAIL, "Content Type is not as expected - " + response.getContentType());

				// 3. Verify that the response time is below 1s.
				if( response.getTimeIn(TimeUnit.MILLISECONDS)<=(1000))
					test.log(Status.PASS,"The time taken to fetch the response - "+ response.timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
				else
					test.log(Status.FAIL,"The time taken to fetch the response - "+ response.timeIn(TimeUnit.MILLISECONDS) + " milliseconds");


		// 4. Verify in Response - "Place Name" for each input "Country" and "Postal Code"
				response.then()
		         .statusCode(200)
		         .body("places[0].'place name'", Matchers.equalTo(expPlace));
				 test.log(Status.PASS,"Place name is validated for Country and Postcode ");
				
		}
		catch(Exception e) {
			test.log(Status.FAIL, "API Test Failed /n" + e);	
		}
					
	}
	
}






