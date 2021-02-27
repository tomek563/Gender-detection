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

    public Sex guessGenderWithFirstVariant(String name) {
        String[] s = name.split(" ");
        String firstPartOfName = s[0];
        List<String> males = getMatchingGender(firstPartOfName, gender.getMale());
        List<String> females = getMatchingGender(firstPartOfName, gender.getFemale());
        return getGender(males, females);
    }

    private Sex getGender(List<String> males, List<String> females) {
        if (!males.isEmpty() && !females.isEmpty()) {
            return Sex.INCONCLUSIVE;
        } else if(!males.isEmpty()) {
            return Sex.MALE;
        } else if (!females.isEmpty()) {
            return Sex.FEMALE;
        } else {
            return Sex.INCONCLUSIVE;
        }
    }

    private List<String> getMatchingGender(String firstPartOfName, List<String> gender) {
        return gender.stream()
                .filter(maleName -> maleName.equals(firstPartOfName))
                .collect(Collectors.toList());
    }

    public Sex guessGenderWithSecondVariant(String name) {
        String[] s = name.split(" ");
        return Sex.INCONCLUSIVE;
    }

    public List<Gender> getAllAvailableTokens() {
        return new ArrayList<>();
    }
}
