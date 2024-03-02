package utilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Class for reading data from excel
public class ExcelReader {
	
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private FileInputStream fis;
	
	//Constructor for getting our excel file
	public ExcelReader(String path)
	{
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//To get the no of rows present in the sheet
	public int getRowCount(String sheetName)
	{
		sheet = workbook.getSheet(sheetName);
		if(sheet==null)
		{
			return -1;
		}
		return sheet.getLastRowNum()+1;
	}
	
	//To get the no of columns present in the sheet
	public int getCellCount(String sheetName)
	{
		sheet = workbook.getSheet(sheetName);
		if(sheet==null)
		{
			return -1;
		}
		row = sheet.getRow(0);
		if(row==null)
		{
			return -1;
		}
		return row.getLastCellNum();
	}
	
	//Reading a cell data using sheetname, cellnum and row number
	public String getCellData(String sheetName, int cellNum, int rowNum)
	{
		if(rowNum<0)
		{
			return "";
		}
		if(cellNum<0)
		{
			return "";
		}
		sheet = workbook.getSheet(sheetName);
		if(sheet==null)
		{
			return "";
		}
		row = sheet.getRow(rowNum);
		if(row==null)
		{
			return "";
		}
		cell = row.getCell(cellNum);
		if(cell==null)
		{
			return "";
		}
		if(cell.getCellType() == cell.CELL_TYPE_STRING)
		{
			return cell.getStringCellValue();
		}
		else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC)
		{
			return String.valueOf(cell.getNumericCellValue());
		}
		else if(cell.getCellType() == cell.CELL_TYPE_BOOLEAN)
		{
			return String.valueOf(cell.getBooleanCellValue());
		}
		else if(cell.getCellType() == cell.CELL_TYPE_FORMULA)
		{
			return String.valueOf(cell.getCellFormula());
		}
		else
		{
			return "";
		}	
		
	}

	//Overloaded method. Reading a cell data using sheetname, column name and row number
	public String getCellData(String sheetName, String cellName, int rowNum)
	{
		if(rowNum<0)
		{
			return "";
		}
		
		sheet = workbook.getSheet(sheetName);
		if(sheet==null)
		{
			return "";
		}
		
		int cellNum = -1;
		row = sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++)
		{
			if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(cellName))
			{
				cellNum = i;
			}
		}
		
		if(cellNum<0)
		{
			return "";
		}
		
		row = sheet.getRow(rowNum);
		if(row==null)
		{
			return "";
		}
		cell = row.getCell(cellNum);
		if(cell==null)
		{
			return "";
		}
		if(cell.getCellType() == cell.CELL_TYPE_STRING)
		{
			return cell.getStringCellValue();
		}
		else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC)
		{
			return String.valueOf(cell.getNumericCellValue());
		}
		else if(cell.getCellType() == cell.CELL_TYPE_BOOLEAN)
		{
			return String.valueOf(cell.getBooleanCellValue());
		}
		else if(cell.getCellType() == cell.CELL_TYPE_FORMULA)
		{
			return String.valueOf(cell.getCellFormula());
		}
		else
		{
			return "";
		}	
		
	}

}
