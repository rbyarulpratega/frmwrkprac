package factory;

import org.openqa.selenium.WebDriver;

public class BrowserFactory{
	
	public static WebDriver driver;

	BrowserFactory(WebDriver driver){
		this.driver=driver;
	}
	public static WebDriver launchBrowsers(BrowserType browserType) {
	
	switch(browserType) {
	case chrome:
		driver= new ChromeBrowser().launchBrowser();
		return driver;
	case edge:
		driver=new EdgeBrowser().launchBrowser();
		return driver;
	default:
		throw new IllegalArgumentException("Browser type Not Supported"+browserType);
	}
	}
	
	public static WebDriver launchUrl() {
		BrowserHelper.implicitWait(driver);
		BrowserHelper.maximiseBrowser(driver);
		BrowserHelper.launchURL(driver);
		return driver;
	}

}
