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
		// 查询用户信息
		List<Bill> list = service.query();
		mav.addObject("list", list);
		return mav;
	}
	@RequestMapping(value = "/export")
	public void export(HttpServletRequest request) throws Exception{
		//1.查出要保存到数据库中的数据
		List<Bill> list=service.query();
		//2.获取tomcat路径
		String path=request.getSession().getServletContext().getRealPath("/");
		//3.将传入的list导入指定路径的excel表中
		exportToExcel(list,path+"bill.xlsx");
		System.out.println("导入成功");
		
	}
	/*
	 * 3.将传入的list导入指定路径的excel表中
	 * */
	private static void exportToExcel(List<Bill> list,String path) throws Exception{
		//3.1创建workbook
		Workbook wb = new XSSFWorkbook();
		//3.2创建表
		Sheet sheet = wb.createSheet("账单信息");
		//3.3创建表头
		createExcelHead(sheet);
		//3.4给表中添加数据
			//3.4.1遍历list,将list中的每一项生成一行
		for(int i=0;i<list.size();i++){
			//3.4.2取出list中每 一行
			Bill item=list.get(i);
			//3.4.3创建表的第i+1行（因为第一行是表头）
			Row row=sheet.createRow(i+1);
			//3.4.4根据行给相应的列添加数据
			row.createCell(0).setCellValue(item.getId());
			row.createCell(1).setCellValue(item.getTradeName());
			row.createCell(2).setCellValue(item.getNum());
			row.createCell(3).setCellValue(item.getAmount());
			row.createCell(4).setCellValue(item.getSupplier());
			//3.4.5时间格式这样直接给显示在浏览器上的格式不对，应该变动
			Cell cell=row.createCell(5);
			doDate(wb,item.getCreateDate(),cell);
		}
		//3.5持久化到tomcat目录下
		FileOutputStream fos=new FileOutputStream(new File(path));
		wb.write(fos);
		//3.6释放io资源
		fos.close();
	}
	/*
	 * 3.3创建表头
	 * */
	private static void createExcelHead(Sheet sheet){
		//第一行
		Row row = sheet.createRow(0);
		//第一行的列
		row.createCell(0).setCellValue("编号");
		row.createCell(1).setCellValue("商品名");
		row.createCell(2).setCellValue("商品数量");
		row.createCell(3).setCellValue("账单金额");
		row.createCell(4).setCellValue("供应商");
		row.createCell(5).setCellValue("创建时间");
	}
	/*
	 * 3.4.5设置时间格式
	 * @param wb 生成一个格式化对象,将格式化对象在设置到风格对象中
	 * @param date 需要格式化数据
	 * @param cell 容器（需要赋值给谁）
	 * */
	private static void doDate(Workbook wb,Date date,Cell cell){
		//用workbook生成格式化对象
		short obj=wb.createDataFormat().getFormat("yyyy-MM-dd DD:mm:ss");
		//用workbook生成单元风格对象
		CellStyle cellstyle=wb.createCellStyle();
		//将格式化对象在设置到风格对象中
		cellstyle.setDataFormat(obj);
		//给单元格设置值
		cell.setCellValue(date);
		//给单元格设置风格
		cell.setCellStyle(cellstyle);
	}
	
}
