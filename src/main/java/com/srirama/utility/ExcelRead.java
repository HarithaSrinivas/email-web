package com.srirama.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelRead {

	public List<DevoteeDetails> readExcel(AppConstants objAppConstants) {

		Workbook objWorkbook = null;
		try {
			FileInputStream objFileInputStream = new FileInputStream(new File(objAppConstants.getMasterFilePath()));
			objWorkbook = new XSSFWorkbook(objFileInputStream);
			int intNoOfSheets = objWorkbook.getNumberOfSheets();
			for (int i = 0; i < intNoOfSheets; i++) {
				List<DevoteeDetails> objList_DevoteeDetails = new ArrayList<DevoteeDetails>();
				if (objWorkbook.getSheetAt(i).getSheetName()
						.equalsIgnoreCase(objAppConstants.getExcelWorkSheetName())) {

					Sheet objSheet = objWorkbook.getSheetAt(i);
					int intCountOfRows = objSheet.getPhysicalNumberOfRows();

					Map<String, Integer> headerCellsMap = new HashMap<String, Integer>();
					
					// Get Header Row
					XSSFRow objHSSFRow = (XSSFRow) objSheet.getRow(0);

					short shortMinColIndex = objHSSFRow.getFirstCellNum();
					short shortMaxColIndex = objHSSFRow.getLastCellNum();
					for (short shortColIndex = shortMinColIndex; shortColIndex < shortMaxColIndex; shortColIndex++) {
						XSSFCell objHSSFCell = objHSSFRow.getCell(shortColIndex);
						headerCellsMap.put(objHSSFCell.getStringCellValue(), objHSSFCell.getColumnIndex());
					}

					int intStartCounter = 1;
					
					for (int intCounter = 1; intCounter <= intCountOfRows; intCounter++) {
						
						XSSFRow hssfRowDataFromCells = (XSSFRow) objSheet.getRow(intCounter);
						
						int intIndexForKID = headerCellsMap.get(objAppConstants.getExcelColnHeaderKid());
						System.out.println(intIndexForKID);
						System.out.println(hssfRowDataFromCells.getCell(intIndexForKID));
						
						int intValueForKID = new Double(
								hssfRowDataFromCells.getCell(intIndexForKID).getNumericCellValue()).intValue();
						
						if ((0 != intValueForKID)
								&& (intValueForKID == Integer.valueOf(objAppConstants.getExcelKidStart()))) {
						
							intStartCounter = intCounter;
							break;
						}
					}

					for (int intTemp = intStartCounter; intTemp <= intCountOfRows; intTemp++) {
						DevoteeDetails objDevoteeDetails = new DevoteeDetails();
						XSSFRow hssfRowDataFromCells = (XSSFRow) objSheet.getRow(intTemp);
						int intIndexForKID = headerCellsMap.get(objAppConstants.getExcelColnHeaderKid());
						if (0 != new Double(hssfRowDataFromCells.getCell(intIndexForKID).getNumericCellValue())
								.intValue()) {
							int intIndexForEmailID = headerCellsMap.get(objAppConstants.getExcelColHeaderEmailId());
							int intIndexForName = headerCellsMap.get(objAppConstants.getExcelColHeaderDevoteeName());
							int intIndexForLastName = headerCellsMap.get(objAppConstants.getExcelColHeaderLastName());

							objDevoteeDetails.setKidNumber(
									new Double(hssfRowDataFromCells.getCell(intIndexForKID).getNumericCellValue())
											.intValue());
							objDevoteeDetails.setPrimaryEmailID(
									hssfRowDataFromCells.getCell(intIndexForEmailID).getStringCellValue());
							objDevoteeDetails.setDevoteeName((new StringBuilder(
									(hssfRowDataFromCells.getCell(intIndexForName).getStringCellValue())
											.concat(" " + (hssfRowDataFromCells.getCell(intIndexForLastName)
													.getStringCellValue())))).toString());
							int intIndexForGothram = headerCellsMap.get(objAppConstants.getExcelColHeaderGothram());

							boolean spouseFound = false;
							do {
								intStartCounter = ++intTemp;
								XSSFRow hssfRowDataFromInnerCells = (XSSFRow) objSheet.getRow(intTemp);
								if (0 == new Double(
										hssfRowDataFromInnerCells.getCell(intIndexForKID).getNumericCellValue())
												.intValue()
										&& hssfRowDataFromInnerCells.getCell(intIndexForGothram).getStringCellValue()
												.equals(objAppConstants.getExcelColumnHeaderGothramSpouse())) {
									objDevoteeDetails.setSpouseName(
											(hssfRowDataFromInnerCells.getCell(intIndexForName).getStringCellValue()));
									objDevoteeDetails.setSecondaryEmailID((hssfRowDataFromInnerCells
											.getCell(intIndexForEmailID).getStringCellValue()));
									spouseFound = true;
								} else if (null != hssfRowDataFromInnerCells.getCell(intIndexForKID)) {
									intStartCounter = --intTemp;
									spouseFound = true;
								}
							} while (!spouseFound);

							objList_DevoteeDetails.add(objDevoteeDetails);
							if ((new Double(hssfRowDataFromCells.getCell(intIndexForKID).getNumericCellValue())
									.intValue() == Double.valueOf(objAppConstants.getExcelKidEnd())))
								break;
						}
					}
				}
				System.out.println(objList_DevoteeDetails);
				return objList_DevoteeDetails;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != objWorkbook) {
				try {
					objWorkbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
