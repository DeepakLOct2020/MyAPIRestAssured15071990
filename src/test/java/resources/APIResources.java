package resources;

public enum APIResources {
	
	
	AddPlaceAPIResource("/maps/api/place/add/json"),
	GetPlaceAPIResource("/maps/api/place/get/json"),
	DeletePlaceAPIResource("/maps/api/place/delete/json");
	
	private String resource;
	
	//Constructor
	APIResources(String resource){
		
	this.resource = resource;
	}
	
	public String getResource() {
		
		return resource;
	}
	
}
