package datafactory;

import java.util.Arrays;

public class DataFactory {
	public static DataProvider createDataSource(DataSource sourceType, String name) throws Exception {
        switch (sourceType) {
            case excel:
                return new ExcelDataProvider(name);
            case api:
                return new APIDataProvider(name); 
            case faker:
            	int rowCount = 1; 
                int colCount = 1;
                String[] fields = {"name"};
                return new FakerDataProvider(rowCount, colCount, fields); 
            case db:
                return new DBDataProvider("jdbc:mysql://localhost:3306/demo,root,ZAQxsw!11,Select username from demo.logindetails"); 
            case harcoded:
            	 return new HardCodedDataProvider();
            default:
                throw new IllegalArgumentException("Invalid data source type: " + sourceType);
        }
    }

}
