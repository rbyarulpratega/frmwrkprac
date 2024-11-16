package datafactory;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestData {

	public static void main(String[] args) throws Exception {
	
	//Data passed through Excel
	DataProvider ExcelData=DataFactory.createDataSource(DataSource.excel, "testdata");	
	Object[][] edata = ExcelData.setDataProvider();
     googleSearch(edata);
    
     //Data passed through Faker
	DataProvider FakerData=DataFactory.createDataSource(DataSource.faker, "");	
    Object[][] fdata = FakerData.setDataProvider();
    googleSearch(fdata);
    
    //Data passed through API
	DataProvider APIData=DataFactory.createDataSource(DataSource.api,"https://api.restful-api.dev/objects");
    Object[][] apidata = APIData.setDataProvider();
    googleSearch(apidata);
   
    //Data passed through DB
	DataProvider DBData=DataFactory.createDataSource(DataSource.db,"");
	Object[][] dbdata = DBData.setDataProvider();
	googleSearch(dbdata);
  
	//Data passed through API
    DataProvider HardcodedData=DataFactory.createDataSource(DataSource.harcoded,"");
    Object[][] hdata = HardcodedData.setDataProvider();
    googleSearch(hdata);
	}
	
	public static void googleSearch(Object[][] data) throws Exception {
		ChromeDriver driver=new ChromeDriver();
	    driver.manage().window().maximize();  
	    WebDriverWait wait;
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get("https://www.google.com/");
	    WebElement searchBox=driver.findElement(By.name("q"));
	    searchBox.click();
	    searchBox.sendKeys((String) data[0][0]);
	    searchBox.submit();
	    driver.quit();		
	}
}
