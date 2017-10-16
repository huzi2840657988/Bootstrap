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
		//1.����һ��excel����
		Workbook wb=new XSSFWorkbook();
		//2.����һ�������(���Ĳ����Ǳ���)
		Sheet sheet=wb.createSheet("mydata");	
		//3.�������еĵ�һ��(���Ĳ���������,��0��ʼ.0�����һ��)
		Row row=sheet.createRow(0);
		//4.�ڵ�һ�еĻ����ϴ�����(���Ĳ���������,��0��ʼ.0�����һ��)
		//setCellValue(object);��ʾ�����������������,�����ö�������
		row.createCell(0).setCellValue("���");
		row.createCell(1).setCellValue("��Ʒ��");
		row.createCell(2).setCellValue("��Ʒ����");
		row.createCell(3).setCellValue("�˵����");
		row.createCell(4).setCellValue("��Ӧ��");
		row.createCell(5).setCellValue("����ʱ��");
		row.createCell(6).setCellValue("���");	
		//5.���ú�����֮��,��execle����־û����ļ���
		FileOutputStream fos=new FileOutputStream("resource/data.xlsx");
		wb.write(fos);
		//6.�ͷ�io��Դ
		fos.close();
	}
}
