package objectpool;

import org.openqa.selenium.WebDriver;

public class TestBrowser {

    static SimpleObjectPool drivers; 

    public static void main(String[] args) {
        drivers = new SimpleObjectPool(); 
        test1();
        test2();
        drivers.closeAllDriver();
    }

    static void test1() {
        WebDriver driver = drivers.getDriver();
        driver.get("https://www.google.com/");
        drivers.releaseDriver(driver);
    }

    static void test2() {
        WebDriver driver = drivers.getDriver();
        driver.get("https://www.facebook.com/");
        drivers.releaseDriver(driver);
    }
}