package cucumber.Options;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		//FEATURE: SHOWS FEATURE FILE LOCATION
		features = "src/test/java/features/placeValidation.feature",
		
		//Report Generation
		plugin = "json:target/jsonReports/cucumber-report.json",
		
		//GLUE: STEP DEFINATION LOCATION
		glue= "stepDefination",
		
		//IF ANY OF THE METHOD IS UNIMPLEMENTED THEN SCENARIO IS MARKED AS FAILED 
		strict = true,
		
		//REMOVE WILD-CARD CHARACTERS FROM CONSOLE
		monochrome=true,
		
		//CHECK IF ALL METHODS ARE IMPLEMENTED
		 dryRun=false
		 
		// tags = {"@DeletePlace"}
		
)
public class TestRunner {
	
}
