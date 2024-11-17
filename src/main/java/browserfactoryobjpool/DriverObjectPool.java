package browserfactoryobjpool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverObjectPool {

    private final ConcurrentMap<DriverType, ConcurrentLinkedQueue<WebDriver>> driverPools;
    private final BrowFactory browFactory;
    private final ReentrantLock lock = new ReentrantLock(); 
    WebDriver driver;
    ConcurrentLinkedQueue<WebDriver> pool;

    public DriverObjectPool(BrowFactory browFactory) {
        this.browFactory = browFactory;
        driverPools = new ConcurrentHashMap<>();
    }

    private WebDriver createDriver(DriverType driverType) {
        lock.lock(); 
        try {
            return browFactory.getDriverProvider(driverType);
        } 
        finally {
            lock.unlock();
        }
    }

    public WebDriver getDriver(DriverType driverType) {
    	pool = driverPools.get(driverType);
        if (pool == null || pool.isEmpty()) {
            synchronized (driverPools) {
                pool = driverPools.computeIfAbsent(driverType, k -> new ConcurrentLinkedQueue<>());
                if (pool.isEmpty()) {
                    Object options;
					WebDriver driver = createDriver(driverType);
                    pool.add(driver);
                }
            }
        }
        return pool.poll();
    }
    
    private WebDriver createDriver(DriverType driverType, Map<String, Object> options) {
        switch (driverType) {
            case chrome:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (options.containsKey("headless")) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
            case edge:
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + driverType);
        }
        return driver;
    }

	public void releaseDriver(WebDriver driver) {
		 if (driver != null) {
			 pool.offer(driver);
	        }
		
	}
	public void closeAllDriver() {
		while (!driverPools.isEmpty()) {
	        for (ConcurrentLinkedQueue<WebDriver> pool : driverPools.values()) {
	            while (!pool.isEmpty()) {
	                pool.poll().quit();
	            }
	        }
	    }
}
}
