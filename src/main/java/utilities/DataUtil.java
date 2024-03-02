package utilities;

import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import basepage.SeleniumDriver;

public class DataUtil {
	
	//DataProvider class to get the data from excel and return the same to text cases
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m)
	{
		String sheetName = m.getName();
		int rowCount = SeleniumDriver.excel.getRowCount(sheetName);
		int cellCount = SeleniumDriver.excel.getCellCount(sheetName);
		
		Object[][] data = new Object[rowCount-1][1];
		
		Hashtable<String,String> table = null;
		for(int row=1;row<rowCount;row++)
		{
			table = new Hashtable<String,String>();
			for(int cell=0;cell<cellCount;cell++)
			{
				table.put(SeleniumDriver.excel.getCellData(sheetName, cell, 0), SeleniumDriver.excel.getCellData(sheetName, cell, row));
				data[row-1][0] = table;
			}
		}
		
		return data;
	}
	
	//FileWriter to write title in file
	public static void fileWriterForHeader(String fileLocation,String header)
	{
		try {
		FileWriter writer = new FileWriter(fileLocation, true);
		writer.write(header+ System.lineSeparator());
		writer.write(System.lineSeparator());
		writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//FileWriter to write data in file
	public static void fileWriter(String fileLocation, String data)
	{
		try {
		FileWriter writer = new FileWriter(fileLocation, true);
		writer.write(data+ System.lineSeparator());
		writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
