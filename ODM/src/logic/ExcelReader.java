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
 * @����ֵ  string ������excelת�����ַ�������
 * 
 */
public class ExcelReader {
	
	//��ȡ07��ʽ��excel
	public ArrayList<String>  readExcel2007(String file){
		ArrayList<String> strList = new ArrayList<String> (); 
		String rowStr = null;
		try {
			File f = new File(file);
			InputStream input = new FileInputStream(f);
			//office2007������   
	        XSSFWorkbook wb = new XSSFWorkbook(input);   
	        //��øù������ĵ�һ��sheet   
	        XSSFSheet sheet = wb.getSheetAt(0);   
			Iterator rows = sheet.rowIterator();
//			int rowNum = sheet.getLastRowNum();
			int maxrownum = 1000;//����ȡ����
			int rowcount = 0;
			int maxcolnum = 20;//����ȡ����
			int colcount = 0;
			int errorRows = 0;
			while (rows.hasNext()) {//ѭ����ȡ���1000��
				if(rowcount>=maxrownum){
					break;
				}
				rowcount++;
				XSSFRow row = (XSSFRow)rows.next();
//				System.out.println("�У�" + row.getRowNum() + " ");
				colcount = 0;
				errorRows = 0;
				Iterator cells = row.cellIterator();
				rowStr = "";
				//while (cells.hasNext()) {//ѭ����ȡ���20��
				for(int k =0; k<8; k++){
					if(colcount>=maxcolnum){
						break;
					}
					if(colcount>=maxcolnum){
						break;
					}
					colcount++;
					//XSSFCell cell = (XSSFCell) cells.next();
					// System.out.println("�У�" + cell.getCellNum());
					XSSFCell cell = (XSSFCell) row.getCell(k);
					if(null == cell){
						rowStr += "null-#-";
						continue;
					}
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_NUMERIC: // ����
//						System.out.println(cell.getNumericCellValue() );
						if(DateUtil.isCellDateFormatted(cell)){
							//����ת��Ϊ���ڸ�ʽ
							Date date = cell.getDateCellValue();
							DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
							rowStr+= formater.format(date);
						}else{
							rowStr+= String.valueOf((int)cell.getNumericCellValue());
						}
						break;
					case XSSFCell.CELL_TYPE_STRING: // �ַ���
//						System.out.println(cell.getStringCellValue());
						rowStr+= String.valueOf(cell.getStringCellValue());
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
//						System.out.println(cell.getBooleanCellValue());
						rowStr+= String.valueOf(cell.getBooleanCellValue());
						break;
					case XSSFCell.CELL_TYPE_FORMULA: // ��ʽ
//						System.out.println(cell.getCellFormula());
						rowStr+= String.valueOf(cell.getCellFormula());
						break;
					case XSSFCell.CELL_TYPE_BLANK: // ��ֵ
//						System.out.println("null");
						errorRows++;
						rowStr+= "null";
						break;
					case XSSFCell.CELL_TYPE_ERROR: // ����
//						System.out.println("error");
						errorRows++;
						rowStr+= "error";
						break;
					default:
//						System.out.print("δ֪����");
						errorRows++;
						rowStr+= "error";
						break;
					}
					rowStr += "-#-";//�ָ����
				}
				//����Ϊ����ʱ��������
				if(errorRows != colcount){
					strList.add(rowStr);//��ӵ����ؽ����
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
			int maxrownum = 1000;//����ȡ����
			int rowcount = 0;
			int maxcolnum = 20;//����ȡ����
			int colcount = 0;
			int errorRows = 0;
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {//ѭ����ȡ���100��
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
				//while (cells.hasNext()) {//ѭ����ȡ���20��
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
					case HSSFCell.CELL_TYPE_NUMERIC: // ����
						if(HSSFDateUtil.isCellDateFormatted(cell)){
							//����ת��Ϊ���ڸ�ʽ
							Date date = cell.getDateCellValue();
							DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
							rowStr+= formater.format(date);
						}else{
							rowStr+= String.valueOf((int)cell.getNumericCellValue());
						}
						break;
					case HSSFCell.CELL_TYPE_STRING: // �ַ���
						rowStr+= String.valueOf(cell.getStringCellValue());
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
						rowStr+= String.valueOf(cell.getBooleanCellValue());
						break;
					case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ
						rowStr+= String.valueOf(cell.getCellFormula());
						break;
					case HSSFCell.CELL_TYPE_BLANK: // ��ֵ
						rowStr+= "null";
						errorRows++;
						break;
					case HSSFCell.CELL_TYPE_ERROR: // ����
						rowStr+= "error";
						errorRows++;
						break;
					default:
						rowStr+= "error";
						errorRows++;
						break;
					}
					rowStr += "-#-";//�ָ����
				}
				//����Ϊ����ʱ��������
				if(errorRows != colcount){
					strList.add(rowStr);//��ӵ����ؽ����
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return strList;
	}
	

}
