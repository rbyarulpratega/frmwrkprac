package objectpool;

import java.util.LinkedList;
import java.util.Queue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleObjectPool {

    private Queue<WebDriver> driverQ;

    public SimpleObjectPool() {
        driverQ = new LinkedList<>();
        driverQ.offer(new ChromeDriver());
    }

    private WebDriver createDriver() {
        return new ChromeDriver();
    }

    public WebDriver getDriver() {
        if (!driverQ.isEmpty()) {
            WebDriver driver = driverQ.poll();
            System.out.println(driver.hashCode());
            return driver;
        } else {
            return createDriver();
        }
    }

    public void releaseDriver(WebDriver driver) {
        if (driver != null) {
            driverQ.offer(driver);
        }
    }

    public void closeAllDriver() {
        while (!driverQ.isEmpty()) {
            driverQ.poll().quit();
        }
    }
}