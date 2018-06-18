package booking.com.cucumber.booking;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import booking.com.cucumber.util.TesteException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchHotel {

	WebDriver driver;
	WebDriverWait wait;
	boolean condition = true;
	private static final String classDate = "xp__dates-inner";
	private static final String nameCity = "ss";
	private static final String classNextMonth = "c2-button c2-button-further";
	private static final String idSelectDate = "1537228800000";
	private static final String classButtonSubmit = "sb-searchbox__button  ";
	private static final String classLabelFilters = "filter_label";
	private static final String classNameHotelFilters = "sr-hotel__name";

	public void openBooking() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alans\\Documents\\workspace\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.booking.com/index.en-gb.html");
	}

	@Given("^I am on the results screen$")
	public void i_am_on_the_results_screen() throws Throwable {
		openBooking();
		wait = new WebDriverWait(driver, 3);
		try {
			driver.findElement(By.xpath("//*[contains(@name, '" + nameCity + "')]")).sendKeys("Limerick");
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(@class, '" + classDate + "')]"))).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(@class, '" + classNextMonth + "')]"))).click();
			driver.findElement(By.xpath("//*[contains(@class, '" + classNextMonth + "')]")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(@data-id, '" + idSelectDate + "')]"))).click();
			driver.findElement(By.xpath("//*[contains(@class, '" + classButtonSubmit + "')]")).click();
		} catch (NoSuchElementException e) {
			throw new TesteException("Could not perform search, elements not found", e, driver);
		} catch (TimeoutException e) {
			throw new TesteException("Could not perform search, elements not found", e, driver);
		}
	}

	@When("^I select \"([^\"]*)\"$")
	public void i_select(String filter) throws Throwable {
		wait = new WebDriverWait(driver, 3);
		try {
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[contains(@class, '" + classLabelFilters + "') and contains(text(), '" + filter + "')]")))
					.click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Filters have been applied')]")))
					.isEnabled();
		} catch (NoSuchElementException e) {
			throw new TesteException("Could not select filter, elements not found", e, driver);
		} catch (TimeoutException e) {
			throw new TesteException("Could not select filter, elements not found", e, driver);
		}
	}

	@Then("^the name of the hotel \"([^\"]*)\" will appear$")
	public void the_name_of_the_hotel_will_appear(String hotelName) throws Throwable {
		wait = new WebDriverWait(driver, 1);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, '"
					+ classNameHotelFilters + "') and contains(text(), '" + hotelName + "')]")));
		} catch (TimeoutException e) {
			condition = false;
		} catch (NoSuchElementException e) {
			throw new TesteException("We could not find the Hotel", e, driver);
		}
	}

	@When("^I will have the result \"([^\"]*)\" on the screen$")
	public void i_will_have_the_result_on_the_screen(Boolean result) throws Throwable {
		if (result == condition) {
			System.out.println("=======================Scenery Success==========================");
			driver.close();
		} else {
			throw new TesteException("Scenario failure", driver);
		}
	}

}
