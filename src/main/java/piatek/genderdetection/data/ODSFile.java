package piatek.genderdetection.data;

import com.github.miachm.sods.Range;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ODSFile {
    private static final String urlMales = "src/main/resources/static/males.ods";
    private static final String urlFemales = "src/main/resources/static/females.ods";

    public ODSFile() {
    }

    public static String getUrlMales() {
        return urlMales;
    }

    public static String getUrlFemales() {
        return urlFemales;
    }

    public static List<String> getSheet(String url){
        List<String> content = new ArrayList<>();
        try {
            SpreadSheet spread = new SpreadSheet(new File(url));
            List<Sheet> sheets = spread.getSheets();
            for (Sheet sheet : sheets) {
                Range range = sheet.getDataRange();
                for (Object[] value : range.getValues()) {
                    content.add(value[0].toString());
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return content;
    }
}
