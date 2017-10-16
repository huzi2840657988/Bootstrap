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
		// 查询用户信息
		List<Bill> list = service.query();
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping(value = "/export1")
	public void export(HttpServletRequest request) {
		List<Bill> list = service.query();//需要导出的数据
		String path = request.getSession().getServletContext().getRealPath("/");
		System.out.println(path);
		try {
			exprotToExcel(list,path+"newBill.xlsx");//持久化到文件
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将传入的list导出到excel
	 * @param list
	 * @throws IOException 
	 */
	private static void exprotToExcel(List<Bill> list,String path) throws IOException{
		//创建workbook
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("账单信息");
		createExcelHead(sheet);//创建了表头
		/**
		 * 遍历list,将list中的每一项生成一行
		 */
		for (int i =0;i<list.size();i++) {
			Bill item = list.get(i);//数据
			Row row = sheet.createRow(i+1);//创建第i号
			row.createCell(0).setCellValue(item.getId());
			row.createCell(1).setCellValue(item.getTradeName());
			row.createCell(2).setCellValue(item.getNum());
			row.createCell(3).setCellValue(item.getAmount());
			row.createCell(4).setCellValue(item.getSupplier());
			Cell cell = row.createCell(5);
			doDate(wb, item.getCreateDate(), cell);//设置第六个-设置时间
		}
		//输出流
		FileOutputStream fos = new FileOutputStream(new File(path));
		wb.write(fos);
		//释放io资源
		fos.close();
	}
	/**
	 * 
	 * @param wb 生成一个格式化对象,格式化对象在设置到风格对象中
	 * @param date 需要格式化数据
	 * @param cell 容器
	 */
	private static void doDate(Workbook wb,Date date,Cell cell){
		CellStyle cellStyle = wb.createCellStyle();//生成单元风格
		cellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd"));	
		cell.setCellValue(date);//给单元格设置值
		cell.setCellStyle(cellStyle);//给单元格设置风格
	}
	private static void createExcelHead(Sheet sheet){
		Row row = sheet.createRow(0);//第一行
		//创建列
		row.createCell(0).setCellValue("编号");
		row.createCell(1).setCellValue("商品名");
		row.createCell(2).setCellValue("商品数量");
		row.createCell(3).setCellValue("账单金额");
		row.createCell(4).setCellValue("供应商");
		row.createCell(5).setCellValue("创建时间");
	}
}
