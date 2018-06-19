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
	// Mudar nome das variaveis constantes tudo maiusculo
	private static final String OPENSITE = "http://www.booking.com/index.en-gb.html";
	private static final String classDate = "xp__dates-inner";
	private static final String nameCityy = "ss";
	private static final String classNextMonth = "c2-button c2-button-further";
	private static final String idSelectDate = "1537228800000";
	private static final String classButtonSubmit = "sb-searchbox__button  ";
	private static final String classLabelFilters = "filter_label";
	private static final String classNameHotelFilters = "sr-hotel__name";

	public SearchHotel() {
		// Baixar o chrome driver "explicar no readme" como fazer para rodar
		// Explicar no readme qual classe executar para iniciar o projeto
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alans\\Documents\\workspace\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(OPENSITE);
	}

	@Given("^I am on the results screen$")
	// mudar nome dos metodos para boas praticas
	public void openViewResults() throws Throwable {
		wait = new WebDriverWait(driver, 3);
		try {
			driver.findElement(By.xpath(Fields.VIEW_CITY_NAME.getXPathFromField())).sendKeys(nameCity);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(@class, '" + classDate + "')]"))).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(@class, '" + classNextMonth + "')]"))).click();
			driver.findElement(By.xpath("//*[contains(@class, '" + classNextMonth + "')]")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(@data-id, '" + idSelectDate + "')]"))).click();
			driver.findElement(By.xpath("//*[contains(@class, '" + classButtonSubmit + "')]")).click();
		} catch (NoSuchElementException|TimeoutException e) { 
			throw new TestException("Could not perform search, elements not found", e, driver);
		} 
	}

	@When("^I select \"([^\"]*)\"$")
	// mudar nome dos metodos para boas praticas
	public void selectFilter(String filter) throws Throwable {
		wait = new WebDriverWait(driver, 3);
		try {
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[contains(@class, '" + classLabelFilters + "') and contains(text(), '" + filter + "')]")))
					.click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Filters have been applied')]")))
					.isEnabled();
		} catch (NoSuchElementException|TimeoutException e) {
			throw new TestException("Could not select filter, elements not found", e, driver);
		}
	}

	@Then("^the name of the hotel \"([^\"]*)\" will appear$")
	// mudar nome dos metodos para boas praticas
	public void checkHotelName(String hotelName) throws Throwable {
		wait = new WebDriverWait(driver, 1);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, '"
					+ classNameHotelFilters + "') and contains(text(), '" + hotelName + "')]")));
		} catch (TimeoutException e) {
			condition = false;
		} catch (NoSuchElementException e) {
			throw new TestException("We could not find the Hotel", e, driver);
		}
	}

	@When("^I will have the result \"([^\"]*)\" on the screen$")
	// mudar nome dos metodos para boas praticas
	public void compareResult(Boolean result) throws Throwable {
		if (result == condition) {
			System.out.println("=======================Scenery Success==========================");
			driver.close();
		} else {
			throw new TestException("Scenario failure", driver);
		}
	}

}
