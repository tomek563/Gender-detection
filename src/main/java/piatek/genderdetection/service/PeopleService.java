package piatek.genderdetection.service;

import piatek.genderdetection.algorithm.Search;
import piatek.genderdetection.data.NamesReader;
import piatek.genderdetection.model.Gender;
import piatek.genderdetection.model.People;

import java.util.ArrayList;
import java.util.List;

public abstract class PeopleService {
    private final NamesReader reader;
    private final Search search;

    public PeopleService(NamesReader reader, Search search) {
        this.reader = reader;
        this.search = search;
    }

    public abstract Gender guessGenderWithFirstVariant(String name);

    public abstract Gender guessGenderWithSecondVariant(String name);

    protected List<String> getAllMatchingNames(List<String> gender, String fullName) {
        return search.search(gender, fullName);
    }

    public People getPeople() {
        return new People(getFemales(), getMales());
    }

    public List<String> getMales() {
        return getAllNames(Gender.MALE);
    }

    public List<String> getFemales() {
        return getAllNames(Gender.FEMALE);
    }

    private List<String> getAllNames(Gender gender) {
        if (gender == Gender.FEMALE) {
            return reader.getFemales();
        } else if (gender == Gender.MALE) {
            return reader.getMales();
        }
        return new ArrayList<>();
    }

}
