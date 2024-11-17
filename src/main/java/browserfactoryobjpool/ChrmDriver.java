package browserfactoryobjpool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChrmDriver implements DriverProvider{
	public DriverType driverType;
	public ChrmDriver(DriverType driverType) {
		this.driverType=driverType;
	}
	public WebDriver getDriverProvider(DriverType driverType) {
		return new ChromeDriver();
	}
}
