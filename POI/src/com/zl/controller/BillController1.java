package com.zl.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zl.bean.Bill;
import com.zl.service.IBillService;

@Controller
@RequestMapping(value = "/bill")
public class BillController1 {
	@Resource
	private IBillService service;

	@RequestMapping(value = "/query")
	public ModelAndView queryAll() {
		ModelAndView mav = new ModelAndView("index");
		// ��ѯ�û���Ϣ
		List<Bill> list = service.query();
		mav.addObject("list", list);
		return mav;
	}
	@RequestMapping(value = "/export")
	public void export(HttpServletRequest request) throws Exception{
		//1.���Ҫ���浽���ݿ��е�����
		List<Bill> list=service.query();
		//2.��ȡtomcat·��
		String path=request.getSession().getServletContext().getRealPath("/");
		//3.�������list����ָ��·����excel����
		exportToExcel(list,path+"bill.xlsx");
		System.out.println("����ɹ�");
		
	}
	/*
	 * 3.�������list����ָ��·����excel����
	 * */
	private static void exportToExcel(List<Bill> list,String path) throws Exception{
		//3.1����workbook
		Workbook wb = new XSSFWorkbook();
		//3.2������
		Sheet sheet = wb.createSheet("�˵���Ϣ");
		//3.3������ͷ
		createExcelHead(sheet);
		//3.4�������������
			//3.4.1����list,��list�е�ÿһ������һ��
		for(int i=0;i<list.size();i++){
			//3.4.2ȡ��list��ÿ һ��
			Bill item=list.get(i);
			//3.4.3������ĵ�i+1�У���Ϊ��һ���Ǳ�ͷ��
			Row row=sheet.createRow(i+1);
			//3.4.4�����и���Ӧ�����������
			row.createCell(0).setCellValue(item.getId());
			row.createCell(1).setCellValue(item.getTradeName());
			row.createCell(2).setCellValue(item.getNum());
			row.createCell(3).setCellValue(item.getAmount());
			row.createCell(4).setCellValue(item.getSupplier());
			//3.4.5ʱ���ʽ����ֱ�Ӹ���ʾ��������ϵĸ�ʽ���ԣ�Ӧ�ñ䶯
			Cell cell=row.createCell(5);
			doDate(wb,item.getCreateDate(),cell);
		}
		//3.5�־û���tomcatĿ¼��
		FileOutputStream fos=new FileOutputStream(new File(path));
		wb.write(fos);
		//3.6�ͷ�io��Դ
		fos.close();
	}
	/*
	 * 3.3������ͷ
	 * */
	private static void createExcelHead(Sheet sheet){
		//��һ��
		Row row = sheet.createRow(0);
		//��һ�е���
		row.createCell(0).setCellValue("���");
		row.createCell(1).setCellValue("��Ʒ��");
		row.createCell(2).setCellValue("��Ʒ����");
		row.createCell(3).setCellValue("�˵����");
		row.createCell(4).setCellValue("��Ӧ��");
		row.createCell(5).setCellValue("����ʱ��");
	}
	/*
	 * 3.4.5����ʱ���ʽ
	 * @param wb ����һ����ʽ������,����ʽ�����������õ���������
	 * @param date ��Ҫ��ʽ������
	 * @param cell ��������Ҫ��ֵ��˭��
	 * */
	private static void doDate(Workbook wb,Date date,Cell cell){
		//��workbook���ɸ�ʽ������
		short obj=wb.createDataFormat().getFormat("yyyy-MM-dd DD:mm:ss");
		//��workbook���ɵ�Ԫ������
		CellStyle cellstyle=wb.createCellStyle();
		//����ʽ�����������õ���������
		cellstyle.setDataFormat(obj);
		//����Ԫ������ֵ
		cell.setCellValue(date);
		//����Ԫ�����÷��
		cell.setCellStyle(cellstyle);
	}
	
}
