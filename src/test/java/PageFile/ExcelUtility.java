package PageFile;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility{
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell_userName,cell_password;


	public XSSFSheet setWorkBookSheet()throws IOException{

		FileInputStream xlData= new FileInputStream ("E:\\eclipse-workspace_Selenium\\Project-AutomationTesting-2\\src\\test\\java\\DataFile\\ProjectData.xlsx");
		workbook= new XSSFWorkbook(xlData);
		//XSSFSheet sheet= workbook.getSheet("Sheet1");
		sheet= workbook.getSheetAt(0);
		return sheet;	

	}
	public XSSFCell get_Username(int rownum, int colmnum)throws IOException{
		sheet= setWorkBookSheet();
		cell_userName=sheet.getRow(rownum).getCell(colmnum);
		return cell_userName;
	}
	public XSSFCell get_password(int rownum, int colmnum)throws IOException{
		sheet= setWorkBookSheet();
		cell_password=sheet.getRow(rownum).getCell(colmnum);
		return cell_password;
	}
}
