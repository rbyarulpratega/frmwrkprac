package browserfactoryobjpool;

import org.openqa.selenium.WebDriver;

public interface DriverProvider {

	WebDriver getDriverProvider(DriverType driverType);
}
