package piatek.genderdetection.data;

import com.github.miachm.sods.Range;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(value = "ods")
public class ODSFileReader implements NamesReader {
    private static final String urlMales = "src/main/resources/static/males.ods";
    private static final String urlFemales = "src/main/resources/static/females.ods";

    @Override
    public List<String> getMales() {
        return getSheet(urlMales);
    }

    @Override
    public List<String> getFemales() {
        return getSheet(urlFemales);
    }

    private List<String> getSheet(String url) {
        // TODO: 02.03.2021 jak przeglądac plik csv bez ładowania całosci do pamieci (without loading into memory)
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
