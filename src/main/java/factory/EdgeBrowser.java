package factory;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.logging.Logger;

public class EdgeBrowser implements Browser{

	private static final Logger logger=Logger.getLogger(EdgeBrowser.class.getName());

	@Override
	public WebDriver launchBrowser() {
		// TODO Auto-generated method stub
		logger.info("EdgeDriver");
		return new EdgeDriver();
	}
}
