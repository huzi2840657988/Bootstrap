package com.zl.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Text4 {
	public static void main(String[] args) throws IOException {
		//1.获取要写入的表格的路径
		String path="resource/bill.xlsx";
		//2.创建workbook，由于表格后缀名有两种，所以要判断
		Workbook wb=null;//不确定后缀名为那种，先赋值为null
			//2.1获取后缀名，判断创建哪一个
			if(isxlsx(path)){
				wb=new XSSFWorkbook(new FileInputStream(path));
			}else{
				wb=new HSSFWorkbook(new FileInputStream(path));
			}
		//3.获取sheet的数量
			int numSheet=wb.getNumberOfSheets();
		//4.遍历每个表格
			for(int i=0;i<numSheet;i++){
				//4.1获取单个表格
				Sheet sheet=wb.getSheetAt(i);
				//4.2获取表格的行和列
				for(Row row:sheet){
						if(row.getRowNum()==0){
							System.err.print(row.getCell(0).getStringCellValue()+"\t");
							System.err.print(row.getCell(1).getStringCellValue()+"\t");
							System.err.print(row.getCell(2).getStringCellValue()+"\t");
							System.err.print(row.getCell(3).getStringCellValue()+"\t");
							System.err.print(row.getCell(4).getStringCellValue()+"\t");
							System.err.print(row.getCell(5).getStringCellValue()+"\t");
							System.out.println();
						}else{						
								System.err.print(row.getCell(0).getNumericCellValue()+"\t");
								System.err.print(row.getCell(1).getStringCellValue()+"\t");
								System.err.print(row.getCell(2).getStringCellValue()+"\t");
								System.err.print(row.getCell(3).getStringCellValue()+"\t");
								System.err.print(row.getCell(4).getStringCellValue()+"\t");
								System.err.print(row.getCell(5).getDateCellValue()+"\t");
						}				
					System.out.println();

				}
				System.out.println();
			}
	}
/*
 * 2.1获取后缀名
 * */
	private static boolean isxlsx(String filename){
		//lastIndexOf() 查找字符或者子串是后一次出现的地方
		int last=filename.lastIndexOf(".");
		//substring(int x)截取从x位置（包括x）到完的字符
		String name=filename.substring(last+1);
		//判断字符长度，确定后缀名
		boolean isok=name.length()==4?true:false;
		return isok;
	}
}
