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
		//������excel����
		Workbook wb = new XSSFWorkbook();
		//����sheet
		Sheet sheet = wb.createSheet("my");
		Row row = sheet.createRow(0);//��һ��
		//������
		row.createCell(0).setCellValue("���");
		row.createCell(1).setCellValue("��Ʒ��");
		row.createCell(2).setCellValue("��Ʒ����");
		row.createCell(3).setCellValue("�˵����");
		row.createCell(4).setCellValue("��Ӧ��");
		row.createCell(5).setCellValue("����ʱ��");
		row.createCell(6).setCellValue("���");
		//�־û����ļ���
		FileOutputStream fos = new FileOutputStream("resource/1.xlsx");
		wb.write(fos);
		//�ͷ�io��Դ
		fos.close();
	}
}
