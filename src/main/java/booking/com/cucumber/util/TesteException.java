package booking.com.cucumber.util;

import org.openqa.selenium.WebDriver;

public class TesteException extends Exception{
	
	public TesteException(String msg,Exception e, WebDriver driver){
		super(msg,e);
		driver.quit();
	}
	public TesteException(String msg, WebDriver driver){
		super(msg);
		driver.quit();
	}
	
}
