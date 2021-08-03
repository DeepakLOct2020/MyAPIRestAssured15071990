package stepDefination;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.AddPlacePOJO;
import pojo.LocationPOJO;
import resources.APIResources;
import resources.TestDataPayloads;
import resources.Utils;

public class AddPlaceStepDef extends Utils {
	
	RequestSpecification res;
	ResponseSpecification respSpec;
	Response response;
	static APIResources resourceAPI;
	static String place_Id;
	
	@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_Place_Payload_with(String arg1, String arg2, String arg3) throws Throwable {
		
	    
		AddPlacePOJO p = new AddPlacePOJO();
		
		p.setAccuracy(50);
		p.setAddress(arg2);
		p.setLanguage(arg3);
		p.setPhone_number("45545454545");
		p.setWebsite("https://automationbykrishna.com");
		p.setName(arg1);
		
		List<String> typesList = new ArrayList<String>();
		
		typesList.add("shoe park");
		typesList.add("shop");
		
		p.setTypes(typesList);
		
		LocationPOJO l = new LocationPOJO();
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
			
		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		res = given().spec(requestSpecification()).body(p);	
		
		
	}
	
	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" Http Request$")
	public void user_calls_with_Http_Request(String resource, String method) throws Throwable {
		
		resourceAPI = APIResources.valueOf(resource);
		
		if(method.equalsIgnoreCase("POST"))
		response = res.when().post(resourceAPI.getResource())
				.then().spec(respSpec).extract().response();
		
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource())
			.then().spec(respSpec).extract().response();
		
		
		else if(method.equalsIgnoreCase("PUT"))
			response = res.when().put(resourceAPI.getResource())
			.then().spec(respSpec).extract().response();
		
		else if(method.equalsIgnoreCase("DELETE"))
			response = res.when().delete(resourceAPI.getResource())
			.then().spec(respSpec).extract().response();
	}

	@Then("^the API call success with status code (\\d+)$")
	public void the_API_call_success_with_status_code(int arg1) throws Throwable {
	    
		int statuCode = response.getStatusCode();
	     assertEquals(200, statuCode);
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String key, String expectedValue) throws Throwable {
	    
		String body = response.getBody().asString();
		assertEquals(getJsonPath(response, key),expectedValue);	
	}
	
	
	@Then("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws Throwable {
	    
		//1.Hit getAPI call
		place_Id = getJsonPath(response, "place_id");
		
		res = given().spec(requestSpecification()).queryParam("place_id", place_Id);
		
		user_calls_with_Http_Request(resource, "GET");
		
		String actualname = getJsonPath(response,"name");
	
		assertEquals(actualname,expectedName);
	}
	
	@Given("^Delete Place Payload$")
	public void delete_Place_Payload() throws Throwable {
		
		String payload = TestDataPayloads.readTextFileUsingFilesClass("src/test/java/resources/fileblock/deletePlacePayload.txt");
		System.out.println(payload);
		String updatedPayload = payload.replaceAll("@Place_Id@", place_Id);
		System.out.println(updatedPayload);
		
	//	res = given().spec(requestSpecification()).body(updatedPayload);
	}


	public static void main(String args[]) {
		
	}

}
