package com.zl.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Test2 {
	public static void main(String[] args) throws IOException {
		String path = "resource/newBill.xlsx";//读取的路径
		Workbook wb = null;
		if(isXlsx(path)){
			wb = new XSSFWorkbook(new FileInputStream(path));
		}else{
			wb = new HSSFWorkbook(new FileInputStream(path));
		}
		//获取sheet
		int numSheet = wb.getNumberOfSheets();//获取sheet的数量
		for (int i = 0; i < numSheet; i++) {
			Sheet itemSheet = wb.getSheetAt(i);//
			for (Row row : itemSheet) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue()+"\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getDateCellValue()+"\t");
						break;
					default:
						break;
					}
				}
				System.out.println("");
			}
		}
	}
	private static boolean isXlsx(String fileName){
		int last = fileName.lastIndexOf(".")+1;//获取最后一个点的位置
		return fileName.substring(last, fileName.length()).equals("xlsx");
	}
}
