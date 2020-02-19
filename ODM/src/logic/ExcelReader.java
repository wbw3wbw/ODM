package logic;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow ;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @返回值  string 将整个excel转化成字符串数组
 * 
 */
public class ExcelReader {
	
	//读取07格式的excel
	public ArrayList<String>  readExcel2007(String file){
		ArrayList<String> strList = new ArrayList<String> (); 
		String rowStr = null;
		try {
			File f = new File(file);
			InputStream input = new FileInputStream(f);
			//office2007工作区   
	        XSSFWorkbook wb = new XSSFWorkbook(input);   
	        //获得该工作区的第一个sheet   
	        XSSFSheet sheet = wb.getSheetAt(0);   
			Iterator rows = sheet.rowIterator();
//			int rowNum = sheet.getLastRowNum();
			int maxrownum = 1000;//最大读取行数
			int rowcount = 0;
			int maxcolnum = 20;//最大读取列数
			int colcount = 0;
			int errorRows = 0;
			while (rows.hasNext()) {//循环读取最多1000行
				if(rowcount>=maxrownum){
					break;
				}
				rowcount++;
				XSSFRow row = (XSSFRow)rows.next();
//				System.out.println("行：" + row.getRowNum() + " ");
				colcount = 0;
				errorRows = 0;
				Iterator cells = row.cellIterator();
				rowStr = "";
				//while (cells.hasNext()) {//循环读取最多20列
				for(int k =0; k<8; k++){
					if(colcount>=maxcolnum){
						break;
					}
					if(colcount>=maxcolnum){
						break;
					}
					colcount++;
					//XSSFCell cell = (XSSFCell) cells.next();
					// System.out.println("列：" + cell.getCellNum());
					XSSFCell cell = (XSSFCell) row.getCell(k);
					if(null == cell){
						rowStr += "null-#-";
						continue;
					}
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_NUMERIC: // 数字
//						System.out.println(cell.getNumericCellValue() );
						if(DateUtil.isCellDateFormatted(cell)){
							//用于转化为日期格式
							Date date = cell.getDateCellValue();
							DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
							rowStr+= formater.format(date);
						}else{
							rowStr+= String.valueOf((int)cell.getNumericCellValue());
						}
						break;
					case XSSFCell.CELL_TYPE_STRING: // 字符串
//						System.out.println(cell.getStringCellValue());
						rowStr+= String.valueOf(cell.getStringCellValue());
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
//						System.out.println(cell.getBooleanCellValue());
						rowStr+= String.valueOf(cell.getBooleanCellValue());
						break;
					case XSSFCell.CELL_TYPE_FORMULA: // 公式
//						System.out.println(cell.getCellFormula());
						rowStr+= String.valueOf(cell.getCellFormula());
						break;
					case XSSFCell.CELL_TYPE_BLANK: // 空值
//						System.out.println("null");
						errorRows++;
						rowStr+= "null";
						break;
					case XSSFCell.CELL_TYPE_ERROR: // 故障
//						System.out.println("error");
						errorRows++;
						rowStr+= "error";
						break;
					default:
//						System.out.print("未知类型");
						errorRows++;
						rowStr+= "error";
						break;
					}
					rowStr += "-#-";//分割符号
				}
				//当不为空行时加入数组
				if(errorRows != colcount){
					strList.add(rowStr);//添加到返回结果中
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return strList;
	}
	
	public ArrayList<String>  readExcel2003(String file){
		ArrayList<String> strList = new ArrayList<String> (); 
		String rowStr = null;
		try {
		    System.out.println(file); 
			File f = new File(file);
			InputStream input = new FileInputStream(f);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			int maxrownum = 1000;//最大读取行数
			int rowcount = 0;
			int maxcolnum = 20;//最大读取列数
			int colcount = 0;
			int errorRows = 0;
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {//循环读取最多100行
				if(rowcount>=maxrownum){
					break;
				}
				rowcount++;
				HSSFRow row = (HSSFRow) rows.next();
				colcount = 0;
				errorRows = 0;
				Iterator cells = row.cellIterator();
				rowStr = "";
				for(int k =0; k<8; k++){
				//while (cells.hasNext()) {//循环读取最多20列
					if(colcount>=maxcolnum){
						break;
					}
					colcount++;
					//HSSFCell cell = (HSSFCell) cells.next();
					HSSFCell cell = (HSSFCell) row.getCell(k);
					if(null == cell){
						rowStr += "null-#-";
						continue;
					}
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						if(HSSFDateUtil.isCellDateFormatted(cell)){
							//用于转化为日期格式
							Date date = cell.getDateCellValue();
							DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
							rowStr+= formater.format(date);
						}else{
							rowStr+= String.valueOf((int)cell.getNumericCellValue());
						}
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						rowStr+= String.valueOf(cell.getStringCellValue());
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
						rowStr+= String.valueOf(cell.getBooleanCellValue());
						break;
					case HSSFCell.CELL_TYPE_FORMULA: // 公式
						rowStr+= String.valueOf(cell.getCellFormula());
						break;
					case HSSFCell.CELL_TYPE_BLANK: // 空值
						rowStr+= "null";
						errorRows++;
						break;
					case HSSFCell.CELL_TYPE_ERROR: // 故障
						rowStr+= "error";
						errorRows++;
						break;
					default:
						rowStr+= "error";
						errorRows++;
						break;
					}
					rowStr += "-#-";//分割符号
				}
				//当不为空行时加入数组
				if(errorRows != colcount){
					strList.add(rowStr);//添加到返回结果中
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return strList;
	}
	

}
