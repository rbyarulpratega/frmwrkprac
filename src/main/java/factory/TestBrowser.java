package factory;

import org.openqa.selenium.WebDriver;


public class TestBrowser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	BrowserFactory.launchBrowsers(BrowserType.chrome);
	BrowserFactory.launchUrl();
	BrowserFactory.launchBrowsers(BrowserType.edge);
	BrowserFactory.launchUrl();
	}

}
