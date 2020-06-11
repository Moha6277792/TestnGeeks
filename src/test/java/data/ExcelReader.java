package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	static FileInputStream fis = null;
	public FileInputStream getFileInputStream () {
		String filepath = System.getProperty("user.dir") + "/src/test/java/data/ExcelData.xlsx";
		File srcfile = new File(filepath);
		try {
			fis = new FileInputStream(srcfile);
		} catch (FileNotFoundException e) {
			System.out.println("No File Found");
			System.exit(0);
		}
		return fis;
	}
	public Object[][] getExcelData() throws IOException {
		fis = getFileInputStream();
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int TotalNumberofRows = (sheet.getLastRowNum()+1);
		int TotalNumberofColumns = 6 ;
		String[][] arrayExcelData = new String[TotalNumberofRows][TotalNumberofColumns];

		for (int i = 0; i < TotalNumberofRows; i++) {
			for (int j = 0; j < TotalNumberofColumns; j++) 
			{
				XSSFRow row = sheet.getRow(i);
				arrayExcelData[i][j] = row.getCell(j).toString();
			}
		}
		wb.close();
		return arrayExcelData;
	}
}
