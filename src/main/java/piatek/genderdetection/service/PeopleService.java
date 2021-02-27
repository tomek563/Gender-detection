package piatek.genderdetection.service;

import org.springframework.stereotype.Service;
import piatek.genderdetection.model.People;
import piatek.genderdetection.model.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeopleService {
    private final People people;

    public PeopleService(People people) {
        this.people = people;
    }

    public People getPeople() {
        return people;
    }

    public Gender guessGenderWithFirstVariant(String name) {
        String[] s = name.split(" ");
        String firstPartOfName = s[0].toLowerCase();
        List<String> males = getMatchingGenderWithFirstVariant(firstPartOfName, people.getMale());
        List<String> females = getMatchingGenderWithFirstVariant(firstPartOfName, people.getFemale());
        return getGenderWithFirstVariant(males, females);
    }

    private Gender getGenderWithFirstVariant(List<String> males, List<String> females) {
        if (!males.isEmpty() && !females.isEmpty()) {
            return Gender.INCONCLUSIVE;
        } else return getGenderWithSecondVariant(!males.isEmpty(), !females.isEmpty());
    }

    private List<String> getMatchingGenderWithFirstVariant(String firstPartOfName, List<String> gender) {
        return gender.stream()
                .filter(maleName -> maleName.toLowerCase().equals(firstPartOfName))
                .collect(Collectors.toList());
    }

    public Gender guessGenderWithSecondVariant(String name) {
        List<String> male = getMatchingGenderWithSecondVariant(name, people.getMale());
        List<String> female = getMatchingGenderWithSecondVariant(name, people.getFemale());
        return getGenderWithSecondVariant(male.size() > female.size(), male.size() < female.size());
    }

    private Gender getGenderWithSecondVariant(boolean b1, boolean b2) {
        if (b1) {
            return Gender.MALE;
        } else if (b2) {
            return Gender.FEMALE;
        } else {
            return Gender.INCONCLUSIVE;
        }
    }

    private List<String> getMatchingGenderWithSecondVariant(String name, List<String> gender) {
        String[] s = name.split(" ");
        List<String> collect = new ArrayList<>();
        for (String s1 : s) {
            for (String person : gender) {
                if (s1.toLowerCase().equals(person.toLowerCase())) {
                    collect.add(person);
                }
            }
        }
        return collect;
    }


    public List<String> getAllAvailableMaleTokens() {
        return people.getMale();
    }

    public List<String> getAllAvailableFemaleTokens() {
        return people.getFemale();
    }
}
