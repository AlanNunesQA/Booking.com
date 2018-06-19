package booking.com.cucumber.booking;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import booking.com.cucumber.util.TestException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchHotel {

	private WebDriver driver;
	private WebDriverWait wait;
	boolean condition = true;
	private String nameCity = "Limerick";
	private static final String OPENSITE = "http://www.booking.com/index.en-gb.html";

	public SearchHotel() {
		// Chrome Driver for running tests
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alans\\Documents\\workspace\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(OPENSITE);
	}

	@Given("^I am on the results screen$")
	// Consultation method with pre-informed data
	public void openViewResults() throws Throwable {
		wait = new WebDriverWait(driver, 3);
		try {
			// City name input
			driver.findElement(By.xpath(Fields.VIEW_CITY_NAME.getXPathFromCityName())).sendKeys(nameCity);
			// Click on the field check in
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(Fields.VIEW_CLASS_DATE.getXPathFromClassDate()))).click();
			// Simulation of user with double click for next month
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(Fields.VIEW_CLASS_NEXT_MONTH.getXPathFromNextMonth())))
					.click();
			driver.findElement(By.xpath(Fields.VIEW_CLASS_NEXT_MONTH.getXPathFromNextMonth())).click();
			// Date selection
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(Fields.VIEW_ID_SELECT_DATE.getXPathFromSelectDate()))).click();
			// Submit form
			driver.findElement(By.xpath(Fields.VIEW_CLASS_BUTTON_SUBMIT.getXPathFromSubmit())).click();
		} catch (NoSuchElementException | TimeoutException e) {
			throw new TestException("Could not perform search, elements not found", e, driver);
		}
	}

	@When("^I select \"([^\"]*)\"$")
	public void selectFilter(String filter) throws Throwable {
		wait = new WebDriverWait(driver, 3);
		try {
			// I put this thread sleep because it was intermittence on the site, it can be
			// removed if there is no intermittence.
			Thread.sleep(1000);
			// Selection of the filter according to the value passed by parameter in the
			// scenario
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath(Fields.VIEW_CLASS_LABEL_FILTERS.getXPathFromLabelFilter(filter)))).click();
			// Wait for filter application
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(Fields.VIEW_APPLIED_FILTER.getXPathFromAppliedFilter())))
					.isEnabled();
		} catch (NoSuchElementException | TimeoutException e) {
			throw new TestException("Could not select filter, elements not found", e, driver);
		}
	}

	@Then("^the name of the hotel \"([^\"]*)\" will appear$")
	public void checkHotelName(String hotelname) throws Throwable {
		wait = new WebDriverWait(driver, 1);
		try {
			// Find the name of the hotel passed by parameter in the scenario
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath(Fields.VIEW_CLASS_NAME_HOTEL_FILTERS.getXPathFromHotelName(hotelname))));
		} catch (TimeoutException e) {
			// If the hotel is not found it will be passed the value false to variable, this
			// bollean being used to compare with the value listed in the scenario
			condition = false;
		} catch (NoSuchElementException e) {
			throw new TestException("We could not find the Hotel", e, driver);
		}
	}

	@When("^I will have the result \"([^\"]*)\" on the screen$")
	public void compareResult(Boolean result) throws Throwable {
		// Condition to verify if the expected result in the scenario matches the
		// received in the test
		if (result == condition) {
			System.out.println("=======================Scenery Success==========================");
			driver.close();
		} else {
			throw new TestException("Scenario failure", driver);
		}
	}

}
