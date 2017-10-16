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
		//1.��ȡҪд��ı���·��
		String path="resource/bill.xlsx";
		//2.����workbook�����ڱ���׺�������֣�����Ҫ�ж�
		Workbook wb=null;//��ȷ����׺��Ϊ���֣��ȸ�ֵΪnull
			//2.1��ȡ��׺�����жϴ�����һ��
			if(isxlsx(path)){
				wb=new XSSFWorkbook(new FileInputStream(path));
			}else{
				wb=new HSSFWorkbook(new FileInputStream(path));
			}
		//3.��ȡsheet������
			int numSheet=wb.getNumberOfSheets();
		//4.����ÿ�����
			for(int i=0;i<numSheet;i++){
				//4.1��ȡ�������
				Sheet sheet=wb.getSheetAt(i);
				//4.2��ȡ�����к���
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
 * 2.1��ȡ��׺��
 * */
	private static boolean isxlsx(String filename){
		//lastIndexOf() �����ַ������Ӵ��Ǻ�һ�γ��ֵĵط�
		int last=filename.lastIndexOf(".");
		//substring(int x)��ȡ��xλ�ã�����x��������ַ�
		String name=filename.substring(last+1);
		//�ж��ַ����ȣ�ȷ����׺��
		boolean isok=name.length()==4?true:false;
		return isok;
	}
}
