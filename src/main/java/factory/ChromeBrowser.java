package factory;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.logging.Logger;

public class ChromeBrowser implements Browser{

	private static final Logger logger=Logger.getLogger(ChromeBrowser.class.getName());

	@Override
	public WebDriver launchBrowser() {
		// TODO Auto-generated method stub
		logger.info("ChromeDriver");
		return new ChromeDriver();
	}
}
