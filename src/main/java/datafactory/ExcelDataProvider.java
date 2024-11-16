package datafactory;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider implements DataProvider {
	
    private String name;

	public ExcelDataProvider(String name) {
        this.name = name;
    } 

	public Object[][] setDataProvider() throws Exception {
		// TODO Auto-generated method stub
		XSSFWorkbook wbk = null;
		wbk = new XSSFWorkbook("./data/"+name+".xlsx");
		XSSFSheet shtat = wbk.getSheetAt(0);
		int rowCount = shtat.getLastRowNum();
		short colCount=shtat.getRow(0).getLastCellNum();
		String[][] data = new String[rowCount][colCount];
		for (int i = 1; i <=rowCount; i++) {
			for(int j=0;j<colCount;j++) {
				String stringCellValue = shtat.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j]=stringCellValue;
			}
		}
		wbk.close();
		return data;
		}
}
