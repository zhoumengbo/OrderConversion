import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class OutputExcel {
    public static void output(List<List<String>> CreateLists, String OutputPath) throws IOException {
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet= wb.createSheet("Sheet1");
        //记录列数
        int columnNum = CreateLists.get(0).size();
        //记录行数
        int rowNum = CreateLists.size();
        //在sheet里创建第一行
        for (int i = 0; i < rowNum ; i++){
            List<String> rowList = CreateLists.get(i);
            HSSFRow firstRow = sheet.createRow(i);
            for (int j = 0 ; j < columnNum ; j++){
                HSSFCell cell = firstRow.createCell(j);
                cell.setCellValue(rowList.get(j));
            }
        }
        FileOutputStream output=new FileOutputStream(OutputPath);
        wb.write(output);
        output.flush();
    }
}
