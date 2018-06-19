package booking.com.cucumber.booking;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

//Runner method of running the tests
@RunWith(Cucumber.class)
@CucumberOptions(
plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json" }, 
features = "src\\test\\java\\booking\\com\\cucumber\\booking\\Booking.feature", 
glue = {"booking.com.cucumber.booking" })

public class Runner {

}
