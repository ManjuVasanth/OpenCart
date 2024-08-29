package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
// DataProvider 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		//Taking excel file from TestData and  .represents-> current data project location
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		
		// Creating an object for ExcelUtility
		
		ExcelUtility xlutil = new ExcelUtility(path);
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1",1);
		//Create 2D array which can store rows and columns
		String logindata[][] = new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcols;j++) {
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); // 1,0 here i-1 because array index starts from 0
			}
		}
		return logindata;
		
	}
	// DataProvider 2
	
}
