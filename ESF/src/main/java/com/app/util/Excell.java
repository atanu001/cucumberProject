package com.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Author
 */

public class Excell {

	File excelFile = null;
	Workbook workbook = null;

	HSSFWorkbook asd = null;

	public Excell(String pathName) throws IOException {
		excelFile = new File(System.getProperty("user.dir") + pathName);
		FileInputStream excelFileTestData = new FileInputStream(excelFile);
		String fileExtenstion = pathName.substring(pathName.indexOf("."));
		if (fileExtenstion.equals(".xls"))
			workbook = new HSSFWorkbook(excelFileTestData);
		else
			workbook = new XSSFWorkbook(excelFileTestData);
	}

	/**
	 * This method is used to get test data in Excel Files
	 *
	 * @param sheetName   Sheet Name where data needs to be retrieved
	 * @param coloumnName Column Name where data needs to be retrieve
	 * 
	 * @return listOfTestDataInColoumn List of Test data for a particular column
	 */

	public ArrayList<String> getCellDataAsList(String sheetName, String coloumnName) {
		ArrayList<String> listOfTestDataInColoumn = new ArrayList<>();
		Sheet sheet = workbook.getSheet(sheetName);
		int noOfRows = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;

		for (int r = 1; r < noOfRows; r++) {
			Row row = sheet.getRow(r);
			listOfTestDataInColoumn
					.add(new DataFormatter().formatCellValue(row.getCell(getColoumnNumber(sheet, coloumnName))));
		}
		Log.info("Listof items ftetched from Excell for Coloumn Name :' " + coloumnName + " ' are  : ");
		for (String s : listOfTestDataInColoumn) {
			Log.info(s);
		}
		return listOfTestDataInColoumn;
	}

	/**
	 * This method is used to write data in Excel Files
	 *
	 * @param sheetName   Sheet Name where data needs to be entered
	 * @param coloumnName Column Name where data needs to be entered
	 * @param rowNumber   RowNumber where data needs to be entered
	 * @param data        TestData which needs to be entered
	 */

	public void writeCellData(String sheetName, String coloumnName, int rowNumber, String data) throws IOException {

		Sheet sheet = workbook.getSheet(sheetName);
		int colomnNumberToWrite = getColoumnNumber(sheet, coloumnName);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.createCell(colomnNumberToWrite);
		cell.setCellValue(data);
		FileOutputStream file = new FileOutputStream(excelFile);
		workbook.write(file);
	}

	public String getCellData(String sheetName, String coloumnName, int rowNumber) {
		return getCellDataAsList(sheetName, coloumnName).get(rowNumber);

	}

	/**
	 * This method is used to get required Column Number from a column name
	 *
	 * @param sheetName   Sheet Name from where column number needs to be taken
	 * @param coloumnName Column Name from where column number needs to be taken
	 * @return requiredColoumnNumber Required Column number
	 */
	public int getColoumnNumber(Sheet sheet, String ColoumnName) {
		Row firstRow = sheet.getRow(0);
		int noOfColoumns = firstRow.getLastCellNum() - firstRow.getFirstCellNum();

		int requiredColoumnNumber = 0;

		for (int c = 0; c < noOfColoumns; c++) {
			if (sheet.getRow(0).getCell(c).getStringCellValue().equals(ColoumnName)) {
				requiredColoumnNumber = c;
				break;
			}
		}
		return requiredColoumnNumber;
	}

}
