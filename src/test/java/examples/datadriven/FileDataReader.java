package examples.datadriven;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileDataReader {

    private static XSSFWorkbook wb;

    public static String readFromExcel(String fileName, int x) {

	String f = "src/test/resources/TestData/" + fileName + ".xlsx";
	String testdata = "";
	try {
	    FileInputStream fis = new FileInputStream(f);
	    wb = new XSSFWorkbook(fis);
	    XSSFSheet sht = wb.getSheet("Sheet1");

	    DataFormatter df = new DataFormatter();
	    testdata = df.formatCellValue(sht.getRow(x - 1).getCell(1));

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	return testdata;

    }

}
