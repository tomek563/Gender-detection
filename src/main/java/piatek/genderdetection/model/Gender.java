package piatek.genderdetection.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gender {
    private final Sex sex;
    private List<String> female;
    private List<String> male;


    public Gender(Sex sex) {
        this.female = prepareFemaleList();
        this.male = prepareMaleList();
        this.sex = sex;
    }

    public Sex getSex() {
        return sex;
    }

    public List<String> getFemale() {
        return female;
    }

    public void setFemale(List<String> female) {
        this.female = female;
    }

    public List<String> getMale() {
        return male;
    }

    public void setMale(List<String> male) {
        this.male = male;
    }

    private List<String> prepareMaleList() {
        return new ArrayList<>(Arrays.asList(
                "Antoni", "Jan", "Jakub", "Aleksander", "Szymon", "Franciszek", "Filip", "Mikołaj",
                "Wojciech", "Adam", "Kacper", "Stanisław", "Marcel", "Leon", "Michał", "Nikodem",
                "Tymon", "Ignacy", "Wiktor", "Igor"
        ));
    }
    private List<String> prepareFemaleList() {
        return new ArrayList<>(Arrays.asList(
                "Zuzanna", "Julia", "Zofia", "Maja", "Hanna", "Lena", "Alicja", "Maria", "Oliwia",
                "Amelia", "Wiktoria", "Aleksandra", "Antonina", "Laura", "Emilia", "Pola", "Marcelina",
                "Natalia", "Liliana", "Iga"
        ));
    }
}
