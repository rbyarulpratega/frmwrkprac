package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import java.util.logging.Logger;

public class BrowserHelper {

	private static final Logger logger=Logger.getLogger(BrowserHelper.class.getName());
	public static WebDriver driver;
	
	BrowserHelper(WebDriver driver){
		BrowserHelper.driver=driver;
	}
	public static WebDriver implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("Implicit Wait for 10 seconds is done");
		return driver;
	}
	
	public static WebDriver maximiseBrowser(WebDriver driver) {
		driver.manage().window().maximize();
		logger.info("Maximizing of Browsers is Done");
		return driver;
	}
	
	public static WebDriver launchURL(WebDriver driver) {
		driver.get("https://www.google.com");
		logger.info("Launched the Google URL");
		return driver;
	}
}

