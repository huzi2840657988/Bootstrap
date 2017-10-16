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
@RequestMapping(value = "/bill1")
public class BillController {
	@Resource
	private IBillService service;

	@RequestMapping(value = "/query1")
	public ModelAndView queryAll() {
		ModelAndView mav = new ModelAndView("my");
		// ��ѯ�û���Ϣ
		List<Bill> list = service.query();
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping(value = "/export1")
	public void export(HttpServletRequest request) {
		List<Bill> list = service.query();//��Ҫ����������
		String path = request.getSession().getServletContext().getRealPath("/");
		System.out.println(path);
		try {
			exprotToExcel(list,path+"newBill.xlsx");//�־û����ļ�
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �������list������excel
	 * @param list
	 * @throws IOException 
	 */
	private static void exprotToExcel(List<Bill> list,String path) throws IOException{
		//����workbook
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("�˵���Ϣ");
		createExcelHead(sheet);//�����˱�ͷ
		/**
		 * ����list,��list�е�ÿһ������һ��
		 */
		for (int i =0;i<list.size();i++) {
			Bill item = list.get(i);//����
			Row row = sheet.createRow(i+1);//������i��
			row.createCell(0).setCellValue(item.getId());
			row.createCell(1).setCellValue(item.getTradeName());
			row.createCell(2).setCellValue(item.getNum());
			row.createCell(3).setCellValue(item.getAmount());
			row.createCell(4).setCellValue(item.getSupplier());
			Cell cell = row.createCell(5);
			doDate(wb, item.getCreateDate(), cell);//���õ�����-����ʱ��
		}
		//�����
		FileOutputStream fos = new FileOutputStream(new File(path));
		wb.write(fos);
		//�ͷ�io��Դ
		fos.close();
	}
	/**
	 * 
	 * @param wb ����һ����ʽ������,��ʽ�����������õ���������
	 * @param date ��Ҫ��ʽ������
	 * @param cell ����
	 */
	private static void doDate(Workbook wb,Date date,Cell cell){
		CellStyle cellStyle = wb.createCellStyle();//���ɵ�Ԫ���
		cellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd"));	
		cell.setCellValue(date);//����Ԫ������ֵ
		cell.setCellStyle(cellStyle);//����Ԫ�����÷��
	}
	private static void createExcelHead(Sheet sheet){
		Row row = sheet.createRow(0);//��һ��
		//������
		row.createCell(0).setCellValue("���");
		row.createCell(1).setCellValue("��Ʒ��");
		row.createCell(2).setCellValue("��Ʒ����");
		row.createCell(3).setCellValue("�˵����");
		row.createCell(4).setCellValue("��Ӧ��");
		row.createCell(5).setCellValue("����ʱ��");
	}
}
