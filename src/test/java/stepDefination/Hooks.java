package stepDefination;

import cucumber.api.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		
		AddPlaceStepDef m = new AddPlaceStepDef();
		
		if(AddPlaceStepDef.place_Id == null) {

		m.add_Place_Payload_with("Deepak", "German", "Deepak");
		m.user_calls_with_Http_Request("AddPlaceAPIResource", "POST");
		m.verify_place_id_created_maps_to_using("Deepak", "GetPlaceAPIResource");
		
		}
	
	}

}
