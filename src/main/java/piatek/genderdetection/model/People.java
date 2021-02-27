package piatek.genderdetection.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class People {
    private final List<String> female;
    private final List<String> male;

    public People() {
        this.female = prepareFemaleList();
        this.male = prepareMaleList();
    }

    public List<String> getFemale() {
        return female;
    }

    public List<String> getMale() {
        return male;
    }

//    private List<String> prepareMaleList() {
//        return ODS.getSheet(ODS.getUrlMales());
//    }
    private List<String> prepareMaleList() {
        return new ArrayList<>(Arrays.asList(
                "Antoni", "Jan", "Jakub", "Aleksander", "Szymon", "Franciszek", "Filip", "Mikołaj",
                "Wojciech", "Adam", "Kacper", "Stanisław", "Marcel", "Leon", "Michał", "Nikodem",
                "Tymon", "Ignacy", "Wiktor", "Igor"
        ));
    }

//    private List<String> prepareFemaleList() {
//        return ODS.getSheet(ODS.getUrlFemales());
//    }
    private List<String> prepareFemaleList() {
        return new ArrayList<>(Arrays.asList(
                "Zuzanna", "Julia", "Zofia", "Maja", "Hanna", "Lena", "Alicja", "Maria", "Oliwia",
                "Amelia", "Wiktoria", "Aleksandra", "Antonina", "Laura", "Emilia", "Pola", "Marcelina",
                "Natalia", "Liliana", "Iga"
        ));
    }
}
