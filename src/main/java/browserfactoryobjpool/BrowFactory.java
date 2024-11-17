package browserfactoryobjpool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.HashMap;
import java.util.Map;


public class BrowFactory implements DriverProvider {

    private static final Map<DriverType, DriverProvider> driverProviders = new HashMap<>();

    static {
        driverProviders.put(DriverType.chrome, new DriverProvider() {
            @Override
            public WebDriver getDriverProvider(DriverType driverType) {
                return new ChromeDriver();
            }
        });
        driverProviders.put(DriverType.edge, new DriverProvider() {
            @Override
            public WebDriver getDriverProvider(DriverType driverType) {
                return new EdgeDriver(); // Replace with actual EdgeDriver setup
            }
        });
    }
    
     @Override
    public WebDriver getDriverProvider(DriverType driverType) {
        DriverProvider provider = driverProviders.get(driverType);
        if (provider == null) {
            throw new IllegalArgumentException("Unsupported browser type: " + driverType);
        }
        return provider.getDriverProvider(driverType);
    }

    public WebDriver getDriverProvider(DriverType driverType, Map<String, Object> options) {
        DriverProvider provider = driverProviders.get(driverType);
        if (provider == null) {
            throw new IllegalArgumentException("Unsupported browser type: " + driverType);
        }
        WebDriver driver = provider.getDriverProvider(driverType);
        return driver;
    }
}
