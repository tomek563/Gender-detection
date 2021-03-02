package piatek.genderdetection.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import piatek.genderdetection.algorithm.Search;
import piatek.genderdetection.data.NamesReader;
import piatek.genderdetection.model.Gender;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleServiceImpl extends PeopleService {

    public PeopleServiceImpl(@Qualifier("memory") NamesReader reader, Search search) {
        super(reader, search);
    }

    @Override
    public Gender guessGenderWithFirstVariant(String name) {
        String[] splitNames = name.split(" ");
        int indexOfFirstElement = 0;
        String firstPartOfName = splitNames[indexOfFirstElement].toLowerCase();
        List<String> males = getMatchingGenderWithFirstVariant(getMales(),firstPartOfName);
        List<String> females = getMatchingGenderWithFirstVariant(getFemales(),firstPartOfName);
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
        return getAllMatchingNames(gender, firstPartOfName);
    }

    @Override
    public Gender guessGenderWithSecondVariant(String name) {
        List<String> male = getMatchingGenderWithSecondVariant(getMales(),name);
        List<String> female = getMatchingGenderWithSecondVariant(getFemales(),name);
        return getGenderWithSecondVariant(male.size() > female.size(), male.size() < female.size());
    }

    private List<String> getMatchingGenderWithSecondVariant(List<String> people, String name) {
        String[] splitNames = name.split(" ");
        List<String> matchedNames = new ArrayList<>();
        for (String partOfSplitedName : splitNames) {
            matchedNames.addAll(getAllMatchingNames(people, partOfSplitedName.toLowerCase()));
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
}
