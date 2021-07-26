Feature: Validating Place APIs

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPIResource" with "POST" Http Request
	Then the API call success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GetPlaceAPIResource"
	
Examples:
	|name    | language | address 			 |
	|AAhouse | English  | world cross center |
#	|BBhouse | German	| Badnera Amravati 	 |
#	|CChouse | French	| Nagpur             |
	

@DeletePlace
Scenario: Verify if Delete Place functionality is working

	Given Delete Place Payload
	When user calls "DeletePlaceAPIResource" with "POST" Http Request
	Then the API call success with status code 200
	And "status" in response body is "OK"
	