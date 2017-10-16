package com.zl.test;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Text3 {
	public static void main(String[] args) throws IOException {
		//1.创建一个excel对象
		Workbook wb=new XSSFWorkbook();
		//2.创建一个表对象(传的参数是表名)
		Sheet sheet=wb.createSheet("mydata");	
		//3.创建表中的第一行(传的参数是行数,从0开始.0代表第一行)
		Row row=sheet.createRow(0);
		//4.在第一行的基础上创建列(传的参数是列数,从0开始.0代表第一列)
		//setCellValue(object);表示给表格里面设置数据,可设置多种类型
		row.createCell(0).setCellValue("编号");
		row.createCell(1).setCellValue("商品名");
		row.createCell(2).setCellValue("商品数量");
		row.createCell(3).setCellValue("账单金额");
		row.createCell(4).setCellValue("供应商");
		row.createCell(5).setCellValue("创建时间");
		row.createCell(6).setCellValue("编号");	
		//5.设置好数据之后,将execle对象持久化到文件中
		FileOutputStream fos=new FileOutputStream("resource/data.xlsx");
		wb.write(fos);
		//6.释放io资源
		fos.close();
	}
}
