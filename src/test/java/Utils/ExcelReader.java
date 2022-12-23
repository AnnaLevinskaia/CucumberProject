package Utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    static Workbook book;
    static Sheet sheet;

    //method to open Exel
    public static void openExel(String filePath){
        try {
            FileInputStream fis=new FileInputStream(filePath);

            book=new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //open the sheet
    public static void getSheet(String sheetName){
        sheet= book.getSheet(sheetName);
    }

    //giving total numbers of rows
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }

    //giving total numbers of columns
    public static int getColsCount(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    //giving data from cell in string format
    public static String getCellData(int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

    //this method will return list of maps having all the data from exel file
    public static List<Map<String, String>> exelListIntoMap(String filePath, String sheetName){
        openExel(filePath);
        getSheet(sheetName);

        //creating a list of map for all the rows
        List<Map<String, String>> listData=new ArrayList<>();

        //loops - outer loop is always take care of rows
        for (int row = 1; row < getRowCount(); row++) {
            //creating a map for every row
            Map<String, String> map=new LinkedHashMap<>();

            for (int col = 0; col < getColsCount(row); col++) {
                map.put(getCellData(0,col), getCellData(row, col)); //0-for key, for the top info of sheet
                //first return key, second getCellData value
            }
            listData.add(map);
        }
        return listData;
    }

}
