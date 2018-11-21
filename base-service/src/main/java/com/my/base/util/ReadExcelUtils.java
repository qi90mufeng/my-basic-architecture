/**
 * 
 */
package com.my.base.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.base.exception.BizException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取excel文件。后缀名为：xls和xlsx。
 */
public class ReadExcelUtils {

    private transient final Logger    logger                    = LoggerFactory
        .getLogger(ReadExcelUtils.class);
    private final String              OFFICE_EXCEL_2003_POSTFIX = "xls";
    private final String              OFFICE_EXCEL_2010_POSTFIX = "xlsx";

    private final String              EMPTY                     = "";
    private final String              POINT                     = ".";
    private final String              NOT_EXCEL_FILE            = " : Not the Excel file!";
    private final String              PROCESSING                = "Processing...";

    private List<String>              columnHeaderList;
    private List<List<String>>        listData;
    private List<Map<String, String>> mapData;
    private boolean                   flag;
    private int                       col                       = 0;
    private int                       row                       = 0;

    /**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    private void readExcel(String path) throws IOException {
        if (path == null || EMPTY.equals(path)) {
            return;
        } else {
            String postfix = getPostfix(path);
            if (!EMPTY.equals(postfix)) {
                if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    readXls(path);
                } else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    readXlsx(path);
                }else{
                	logger.info(path + NOT_EXCEL_FILE);
                	throw new BizException("",path + NOT_EXCEL_FILE);
                }
            } else {
                logger.info(path + NOT_EXCEL_FILE);
                throw new BizException("",path + NOT_EXCEL_FILE);
            }
        }
    }

    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    private void readXlsx(String path) throws IOException {
        logger.info(PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }

            listData = new ArrayList<List<String>>();
            mapData = new ArrayList<Map<String, String>>();
            columnHeaderList = new ArrayList<String>();
            // Read the Row
            row = xssfSheet.getLastRowNum() + 1;
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                Map<String, String> map = new HashMap<String, String>();
                List<String> list = new ArrayList<String>();
                if (xssfRow != null) {
                    col = xssfRow.getLastCellNum();
                    for (int j = 0; j < col; j++) {
                        XSSFCell cell = xssfRow.getCell(j);
                        if (rowNum == 0) {
                            columnHeaderList.add(cell == null? "":getValue(cell));
                        } else {
                            map.put(columnHeaderList.get(j), cell == null? "":getValue(cell));
                        }
                        list.add(cell == null? "":getValue(cell));
                    }

                }

                if (rowNum > 0) {
                    mapData.add(map);
                }
                listData.add(list);

            }

            flag = true;
        }
    }

    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    private void readXls(String path) throws IOException {
        logger.info(PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }

            listData = new ArrayList<List<String>>();
            mapData = new ArrayList<Map<String, String>>();
            columnHeaderList = new ArrayList<String>();
            // Read the Row
            row = hssfSheet.getLastRowNum() + 1;
            for (int rowNum = 0; rowNum <= row; rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                Map<String, String> map = new HashMap<String, String>();
                List<String> list = new ArrayList<String>();
                if (hssfRow != null) {
                    col = hssfRow.getLastCellNum();
                    for (int j = 0; j < col; j++) {
                        HSSFCell cell = hssfRow.getCell(j);
                        if (rowNum == 0) {
                            columnHeaderList.add(cell == null? "":getValue(cell));
                        } else {
                            map.put(columnHeaderList.get(j), cell == null? "":getValue(cell));
                        }
                        list.add(cell == null? "":getValue(cell));
                    }

                }

                if (rowNum > 0) {
                    mapData.add(map);
                }
                listData.add(list);
            }

            flag = true;
        }
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    /**
     * get postfix of the path
     * @param path
     * @return
     */
    private String getPostfix(String path) {
        if (path == null || EMPTY.equals(path.trim())) {
            return EMPTY;
        }
        if (path.contains(POINT)) {
            return path.substring(path.lastIndexOf(POINT) + 1, path.length());
        }
        return EMPTY;
    }

    /**
     * @param path the path of the excel file
     * @param row
     * @param col
     * @return
     * @throws IOException 
     */
    public String getCellData(String path, int row, int col) throws IOException {
        if (row <= 0 || col <= 0) {
            return null;
        }
        if (!flag) {
            this.readExcel(path);
        }
        if (listData.size() >= row && listData.get(row - 1).size() >= col) {
            return listData.get(row - 1).get(col - 1);
        } else {
            return null;
        }
    }

    public String getCellData(String path, int row, String headerName) throws IOException {
        if (row <= 0) {
            return null;
        }
        if (!flag) {
            this.readExcel(path);
        }
        if (mapData.size() >= row && mapData.get(row - 1).containsKey(headerName)) {
            return mapData.get(row - 1).get(headerName);
        } else {
            return null;
        }
    }

    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }
    
    public static void main(String[] args) throws Exception {
    	ReadExcelUtils readExcel = new ReadExcelUtils();
    	readExcel.getCellData("e:\\test.xlsx", 1, 1);
    }
}