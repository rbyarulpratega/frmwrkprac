package browserfactoryobjpool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgDriver implements DriverProvider{
	public DriverType driverType;
	public EdgDriver(DriverType driverType) {
		this.driverType=driverType;
	}
	public WebDriver getDriverProvider(DriverType driverType) {
		return new EdgeDriver();
	}
}
