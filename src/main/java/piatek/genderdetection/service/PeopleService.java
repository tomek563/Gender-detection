package piatek.genderdetection.service;

import org.springframework.stereotype.Service;
import piatek.genderdetection.algorithm.Algorithm;
import piatek.genderdetection.model.People;
import piatek.genderdetection.model.Gender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeopleService {
    // TODO: 28.02.2021 binary search
    private final People people;
    private final Algorithm algorithm;

    public PeopleService(People people, Algorithm algorithm) {
        this.people = people;
        this.algorithm = algorithm;
    }

    public People getPeople() {
        return people;
    }

    public Gender guessGenderWithFirstVariant(String name) {
        String[] splitNames = name.split(" ");
        int indexOfFirstElement = 0;
        String firstPartOfName = splitNames[indexOfFirstElement].toLowerCase();
        List<String> males = getMatchingGenderWithFirstVariant(people.getMale(),firstPartOfName);
        List<String> females = getMatchingGenderWithFirstVariant(people.getFemale(),firstPartOfName);
        return getGenderWithFirstVariant(males, females);
    }

    private Gender getGenderWithFirstVariant(List<String> males, List<String> females) {
        if (!males.isEmpty() && !females.isEmpty()) {/*condition for name in the same gender eg. Daniel, Sam */
            return Gender.INCONCLUSIVE;
        } else if (!males.isEmpty()) {
            return Gender.MALE;
        } else if (!females.isEmpty()) {
            return Gender.FEMALE;
        } else {
            return Gender.INCONCLUSIVE;
        }
    }

    private List<String> getMatchingGenderWithFirstVariant(List<String> gender, String firstPartOfName) {
        return algorithm.binarySearch(gender, firstPartOfName);
    }

    public Gender guessGenderWithSecondVariant(String name) {
        List<String> male = getMatchingGenderWithSecondVariant(people.getMale(),name);
        List<String> female = getMatchingGenderWithSecondVariant(people.getFemale(),name);
        return getGenderWithSecondVariant(male.size() > female.size(), male.size() < female.size());
    }

    private List<String> getMatchingGenderWithSecondVariant(List<String> people, String name) {
        String[] splitNames = name.split(" ");
        List<String> matchedNames = new ArrayList<>();
        for (String partOfSplitedName : splitNames) {
            matchedNames.addAll(algorithm.binarySearch(people, partOfSplitedName.toLowerCase()));
        }
        return matchedNames;
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


    public List<String> getAllAvailableMaleTokens() {
        return people.getMale();
    }

    public List<String> getAllAvailableFemaleTokens() {
        return people.getFemale();
    }
}
