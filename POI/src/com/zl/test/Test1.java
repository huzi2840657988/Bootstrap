package com.zl.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test1 {
	public static void main(String[] args) throws IOException {
		//创建了excel对象
		Workbook wb = new XSSFWorkbook();
		//创将sheet
		Sheet sheet = wb.createSheet("my");
		Row row = sheet.createRow(0);//第一行
		//创建列
		row.createCell(0).setCellValue("编号");
		row.createCell(1).setCellValue("商品名");
		row.createCell(2).setCellValue("商品数量");
		row.createCell(3).setCellValue("账单金额");
		row.createCell(4).setCellValue("供应商");
		row.createCell(5).setCellValue("创建时间");
		row.createCell(6).setCellValue("编号");
		//持久化到文件中
		FileOutputStream fos = new FileOutputStream("resource/1.xlsx");
		wb.write(fos);
		//释放io资源
		fos.close();
	}
}
