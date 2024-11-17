package browserfactoryobjpool;

import org.openqa.selenium.WebDriver;

public class TestBrowserFPool {

    static DriverObjectPool drivers; 

    public static void main(String[] args) {
        BrowFactory browFactory = new BrowFactory();
        drivers = new DriverObjectPool(browFactory); 
        test1();
        test2();
        drivers.closeAllDriver();
    }

    static void test1() {
        WebDriver driver = drivers.getDriver(DriverType.chrome);
        driver.get("https://www.google.com/");
        drivers.releaseDriver(driver);
    }

    static void test2() {
        WebDriver driver = drivers.getDriver(DriverType.edge);
        driver.get("https://www.facebook.com/");
        drivers.releaseDriver(driver);
    }
}