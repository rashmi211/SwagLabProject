package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestData {
	public static String getData(int i,int j) throws EncryptedDocumentException, IOException {
		
	FileInputStream excel= new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\SwagLab\\src\\test\\resources\\Test Data.xlsx");

	String data=WorkbookFactory.create(excel).getSheet("SwagLab").getRow(i).getCell(j).getStringCellValue();
	
	return data;

}}

