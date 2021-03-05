package piatek.genderdetection.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class People {
    private final List<String> female;
    private final List<String> male;

    public People() {
        female = new ArrayList<>();
        male = new ArrayList<>();
    }

    public People(List<String> female, List<String> male) {
        this.female = female;
        this.male = male;
    }

    public List<String> getFemale() {
        return female;
    }

    public List<String> getMale() {
        return male;
    }

}
