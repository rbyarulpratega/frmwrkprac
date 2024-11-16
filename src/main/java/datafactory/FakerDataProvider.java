package datafactory;
import com.github.javafaker.Faker;

public class FakerDataProvider  implements DataProvider {
	 private int rowCount;
	    private int colCount;
	    private String[] fields;

	    public FakerDataProvider(int rowCount, int colCount, String[] fields) {
	        this.rowCount = rowCount;
	        this.colCount = colCount;
	        this.fields = fields;
	    }

	    public Object[][] setDataProvider() {
	        Faker faker = new Faker();
	        Object[][] data = new Object[rowCount][colCount];

	        for (int i = 0; i < rowCount; i++) {
	            for (int j = 0; j < colCount; j++) {
	                switch (fields[j]) {
	                    case "name":
	                        data[i][j] = faker.name().fullName();
	                        break;
	                    case "email":
	                        data[i][j] = faker.internet().emailAddress();
	                        break;
	                }
	            }
	        }

	        return data;
	    }
}
