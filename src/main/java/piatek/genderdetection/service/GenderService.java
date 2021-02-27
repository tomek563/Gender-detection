package piatek.genderdetection.service;

import org.springframework.stereotype.Service;
import piatek.genderdetection.model.Gender;
import piatek.genderdetection.model.Sex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenderService {
    private final Gender gender;

    public GenderService(Gender gender) {
        this.gender = gender;
    }

    public Sex guessGenderWithFirstVariant(String name) { // TODO: 27.02.2021 ignoreCase
        String[] s = name.split(" ");
        String firstPartOfName = s[0];
        List<String> males = getMatchingGenderWithFirstVariant(firstPartOfName, gender.getMale());
        List<String> females = getMatchingGenderWithFirstVariant(firstPartOfName, gender.getFemale());
        return getGenderWithFirstVariant(males, females);
    }

    private Sex getGenderWithFirstVariant(List<String> males, List<String> females) {
        if (!males.isEmpty() && !females.isEmpty()) {
            return Sex.INCONCLUSIVE;
        } else return getGenderWithSecondVariant(!males.isEmpty(), !females.isEmpty());
    }

    private List<String> getMatchingGenderWithFirstVariant(String firstPartOfName, List<String> gender) {
        return gender.stream()
                .filter(maleName -> maleName.equals(firstPartOfName))
                .collect(Collectors.toList());
    }

    public Sex guessGenderWithSecondVariant(String name) {
        List<String> male = getMatchingGenderWithSecondVariant(name, gender.getMale());
        List<String> female = getMatchingGenderWithSecondVariant(name, gender.getFemale());
        return getGenderWithSecondVariant(male.size() > female.size(), male.size() < female.size());
    }

    private Sex getGenderWithSecondVariant(boolean b1, boolean b2) {
        if (b1) {
            return Sex.MALE;
        } else if (b2) {
            return Sex.FEMALE;
        } else {
            return Sex.INCONCLUSIVE;
        }
    }

    private List<String> getMatchingGenderWithSecondVariant(String name, List<String> gender) {
        String[] s = name.split(" ");
        List<String> collect = new ArrayList<>();
        for (String s1 : s) {
            for (String person : gender) {
                if (s1.equals(person)) {
                    collect.add(person);
                }
            }
        }
        return collect;
    }

    public List<Gender> getAllAvailableTokens() {
        return new ArrayList<>();
    }
}
