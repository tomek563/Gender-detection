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
        String[] splitNames = name.split(" ");
        int indexOfFirstElement = 0;
        String firstPartOfName = splitNames[indexOfFirstElement].toLowerCase();
        List<String> males = getMatchingGenderWithFirstVariant(firstPartOfName, people.getMale());
        List<String> females = getMatchingGenderWithFirstVariant(firstPartOfName, people.getFemale());
        return getGenderWithFirstVariant(males, females);
    }

    private Gender getGenderWithFirstVariant(List<String> males, List<String> females) {
        /*condition for name in the same gender eg. Daniel, Sam */
        if (!males.isEmpty() && !females.isEmpty()) {
            return Gender.INCONCLUSIVE;
        } else if (!males.isEmpty()) {
            return Gender.MALE;
        } else if (!females.isEmpty()) {
            return Gender.FEMALE;
        } else {
            return Gender.INCONCLUSIVE;
        }
    }

    private List<String> getMatchingGenderWithFirstVariant(String firstPartOfName, List<String> gender) {
        return gender.stream()
                .filter(genderName -> genderName.toLowerCase().equals(firstPartOfName))
                .collect(Collectors.toList());
    }

    private Gender getGenderWithSecondVariant(boolean moreMen, boolean moreWomen) {
        if (moreMen) {
            return Gender.MALE;
        } else if (moreWomen) {
            return Gender.FEMALE;
        } else {
            return Gender.INCONCLUSIVE;
        }
    }

    public Gender guessGenderWithSecondVariant(String name) {
        List<String> male = getMatchingGenderWithSecondVariant(name, people.getMale());
        List<String> female = getMatchingGenderWithSecondVariant(name, people.getFemale());
        return getGenderWithSecondVariant(male.size() > female.size(), male.size() < female.size());
    }

    private List<String> getMatchingGenderWithSecondVariant(String name, List<String> people) {
        String[] splitNames = name.split(" ");
        List<String> matchedNames = new ArrayList<>();
        for (String partOfSplitedName : splitNames) {
            for (String person : people) {
                if (partOfSplitedName.toLowerCase().equals(person.toLowerCase())) {
                    matchedNames.add(person);
                }
            }
        }
        return matchedNames;
    }


    public List<String> getAllAvailableMaleTokens() {
        return people.getMale();
    }

    public List<String> getAllAvailableFemaleTokens() {
        return people.getFemale();
    }
}
