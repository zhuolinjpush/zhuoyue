package net.zhuoyue.util;

import java.io.FileOutputStream;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

    private static Logger LOG = LogManager.getLogger(ExcelUtil.class.getName());
    
    public static void createXlsTable(String sheetName, LinkedList<String> title, LinkedList<String> className, LinkedList<LinkedList<Object>> values, String output) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            HSSFRow row = sheet.createRow(0);
            for (int i = 0; i < title.size(); i++) {
                row.createCell(i).setCellValue(new HSSFRichTextString(title.get(i)));
            }
            int rownum = 1;
            for (int j = 0; j < values.size(); j++) {
                row = sheet.createRow(rownum);
                LinkedList<Object> objList = values.get(j);
                for (int k = 0; k < objList.size(); k++) {
                    Object obj = objList.get(k);
                    String type = className.get(k).toLowerCase();
                    if ("string".equals(type)) {
                        row.createCell(k).setCellValue(new HSSFRichTextString(String.valueOf(obj)));
                    } else if ("int".equals(type)) {
                        row.createCell(k).setCellValue(Integer.parseInt(String.valueOf(obj)));
                    } else if ("double".equals(type)) {
                        row.createCell(k).setCellValue(Double.parseDouble(String.valueOf(obj)));
                    } else if ("long".equals(type)) {
                        row.createCell(k).setCellValue(Long.parseLong(String.valueOf(obj)));
                    } else {
                        row.createCell(k).setCellValue(new HSSFRichTextString(String.valueOf(obj)));
                    }
                }
                rownum++;
            }
            FileOutputStream out = new FileOutputStream(output);
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            LOG.error("create xls table error", e);
        }
    }
    
    public static void main(String[] args) {
 
    }

}
