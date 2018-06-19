package booking.com.cucumber.util;

import org.openqa.selenium.WebDriver;

public class TestException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	//Treatment of Exceptions
	public TestException(String msg,Exception e, WebDriver driver){
		super(msg,e);
		driver.quit();
	}
	public TestException(String msg, WebDriver driver){
		super(msg);
		driver.quit();
	}
	
}
