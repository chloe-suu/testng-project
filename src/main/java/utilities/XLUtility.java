package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class XLUtility {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;
    public XLUtility(String path){
        this.path = path;
    }

    public void accessExcelSheet(String sheetName) throws IOException{
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
    }

    public void closeExcel() throws IOException {
        workbook.close();
        fi.close();
    }

    public int getNumberOfRows() throws IOException{
        return sheet.getLastRowNum();
    }
    public int getNumberOfColsInRow(int rowNo) throws IOException{
        row = sheet.getRow(rowNo);
        return row.getLastCellNum();
    }
    public String getCellData(String sheetName, int rowNo, int cellNo) throws IOException {
        accessExcelSheet(sheetName);
        row = sheet.getRow(rowNo);
        cell = row.getCell(cellNo);

        DataFormatter formatter= new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); //return data as string value
        } catch (Exception e){
            data = "";
        }
        closeExcel();
        return data;
    }
    public String[][] getDataTable(String sheetName) throws IOException {
        accessExcelSheet(sheetName);
        //get all rows in the sheet
        int rowCount = getNumberOfRows();
        int cellCount = getNumberOfColsInRow(1);
        String[][] dataTable = new String[rowCount][cellCount];;
        //iterate over all the row
        for(int i=1; i<=rowCount; i++){
            //iterate over each cell to print its value
            for(int j=0; j < cellCount; j++){
                dataTable[i-1][j] =getCellData(sheetName,i,j);
            }
        }
        closeExcel();
        return dataTable;
    }
    public void writeDataToExcelFile(String fileName, String sheetName, Map<String,Object[]> obj) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet( sheetName);

        //Create row object
        XSSFRow row;
        //Iterate over data and write to sheet
        Set< String > keyid = obj.keySet();
        int rowid = 0;

        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object [] objectArr = obj.get(key);
            int cellid = 0;

            for (Object object : objectArr){
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)object);
            }
        }
        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(
                new File(fileName));

        workbook.write(out);
        out.close();
    }
}
