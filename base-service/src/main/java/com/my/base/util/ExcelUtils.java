package com.my.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtils {
	private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
	private final static String              OFFICE_EXCEL_2003_POSTFIX = ".xls";
    private final static String              OFFICE_EXCEL_2010_POSTFIX = ".xlsx";
	public static Workbook read(File file) {
		String filepath = file.getAbsolutePath();
		if(filepath==null){  
            return null;  
        }  
		Workbook wb = null;
        String ext = filepath.substring(filepath.lastIndexOf("."));  
        try {  
            InputStream is = new FileInputStream(file);  
            if(OFFICE_EXCEL_2003_POSTFIX.equals(ext)){  
                wb = new HSSFWorkbook(is);  
            }else if(OFFICE_EXCEL_2010_POSTFIX.equals(ext)){  
                wb = new XSSFWorkbook(is);  
            }else{  
                wb=null;  
            }  
        } catch (FileNotFoundException e) {  
            logger.error("FileNotFoundException", e);  
        } catch (IOException e) {  
            logger.error("IOException", e);  
        } 
		return wb;
	}
	
	/** 
     *  
     * 根据Cell类型设置数据 
     *  
     * @param cell 
     * @return 
     */  
    public static String getCellValue(Cell cell) {  
        String cellvalue = "";  
        if (cell != null) {  
            // 判断当前Cell的Type  
            switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC  
            case Cell.CELL_TYPE_FORMULA: {  
                // 判断当前的cell是否为Date  
                if (DateUtil.isCellDateFormatted(cell)) {  
                    Date date = cell.getDateCellValue();  
                    cellvalue = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
                } else {
                    // 取得当前Cell的数值  
                    cellvalue = String.valueOf(cell.getNumericCellValue());  
                }  
                break;  
            }  
            case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING  
                // 取得当前的Cell字符串  
                cellvalue = cell.getRichStringCellValue().getString();  
                break;  
            default:// 默认的Cell值  
                cellvalue = "";  
            }  
        } else {  
            cellvalue = "";  
        }  
        return cellvalue;  
    }  
    
}
