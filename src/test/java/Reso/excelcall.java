package Reso;

import java.io.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import org.testng.annotations.Test;
//class to call the excel sheet and use the data in the sheet
public class excelcall
{
    public JSONObject excel()throws FileNotFoundException,IOException
    {

        String path = "C:\\Users\\anuragkumar5\\Desktop\\Files\\HU_COW_MAIN_ASSIGNMENT\\src\\main\\resources\\UserData.csv";
        //accesses the csv file
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        JSONArray arr = new JSONArray();
        JSONObject input = new JSONObject();
        while ((line = br.readLine()) != null)
        {
            String[] values = line.split(",");
            input.put("space_id", values[0]);
            input.put("space_name", values[1]);
            input.put("space_address", values[2]);
            input.put("hour_duration", values[3]);
            input.put("price_per_hour", values[4]);
            input.put("user_id", values[5]);
            input.put("user_name", values[6]);
            input.put("email", values[7]);
            input.put("org_email", values[8]);
        }

        return input;

    }
}