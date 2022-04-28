import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
public class CreateSpaces {

     public String sendData(int sheetNum, int rowNum,int colNum) throws IOException {
            String excelFilePath = "C:\\Users\\anuragkumar5\\Desktop\\Files\\HU_COW_MAIN_ASSIGNMENT\\src\\test\\resources\\create_spaces.xlsx";
            FileInputStream fis = new FileInputStream(excelFilePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(sheetNum);
            XSSFRow row = null;
            XSSFCell cell = null;
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);
            String send = cell.getStringCellValue();
            return send;
        }
    }

