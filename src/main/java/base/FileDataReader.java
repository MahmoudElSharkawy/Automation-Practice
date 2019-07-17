package base;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileDataReader {

	private XSSFWorkbook wb;

	public String read(String fileName, int x, int y) throws IOException {
		
		String f = "src/test/resources/TestData/" + fileName;
		//New file
		FileInputStream fis = new FileInputStream(f);
		wb = new XSSFWorkbook(fis);
		//Get from this sheet
		XSSFSheet sht = wb.getSheet("Sheet1");
		
//		String testdata = sht.getRow(x).getCell(y).getStringCellValue();
		DataFormatter df = new DataFormatter();
		String testdata = df.formatCellValue(sht.getRow(x).getCell(y));

		return testdata;
	}

}
