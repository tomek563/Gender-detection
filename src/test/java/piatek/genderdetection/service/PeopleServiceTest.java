package piatek.genderdetection.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import piatek.genderdetection.model.Gender;
import piatek.genderdetection.model.People;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PeopleServiceTest {
    String nameFirst;
    String nameSecond;
    String nameThird;
    String nameFourth;
    String nameFifth;
    PeopleService peopleService;

    @BeforeEach
    public void setUp() {
        nameFirst = "Jan Maria Rokita";
        nameSecond = "Maria Jan Rokita";
        nameThird = "Chair";
        nameFourth = "Sam Daniel";
        nameFifth = "Marcel Laura Lena";
        peopleService = new PeopleService(new People());
    }

    @Test
    void guessGenderWithFirstVariant_Should_Return_Male_When_Name_IsRecognized_As_Male() {
//        given
//        when
        Gender gender = peopleService.guessGenderWithFirstVariant(nameFirst);
//        then
        assertThat(gender, equalTo(Gender.MALE));
    }
    @Test
    void guessGenderWithFirstVariant_Should_Return_Female_When_Name_IsRecognized_As_Female() {
//        given
//        when
        Gender gender = peopleService.guessGenderWithFirstVariant(nameSecond);
//        then
        assertThat(gender, equalTo(Gender.FEMALE));
    }
    @Test
    void guessGenderWithFirstVariant_Should_Return_Inconclusive_When_ThereIs_No_Such_Name() {
//        given
//        when
        Gender gender = peopleService.guessGenderWithFirstVariant(nameThird);
//        then
        assertThat(gender, equalTo(Gender.INCONCLUSIVE));
    }
    @Test
    void guessGenderWithFirstVariant_Should_Return_Inconclusive_When_Name_BelongsTo_Both_Gender() {
//        given
//        when
        Gender gender = peopleService.guessGenderWithFirstVariant(nameFourth);
//        then
        assertThat(gender, equalTo(Gender.INCONCLUSIVE));
    }


    @Test
    void guessGenderWithSecondVariant_Should_Return_Male_When_Name_Has_More_Male_SubNames() {
//        given
//        when
        Gender gender = peopleService.guessGenderWithSecondVariant(nameFourth);
//        then
        assertThat(gender, equalTo(Gender.MALE));
    }
    @Test
    void guessGenderWithSecondVariant_Should_Return_Female_When_Name_Has_More_Female_SubNames() {
//        given
//        when
        Gender gender = peopleService.guessGenderWithSecondVariant(nameFifth);
//        then
        assertThat(gender, equalTo(Gender.FEMALE));
    }
    @Test
    void guessGenderWithSecondVariant_Should_Return_Inconclusive_When_NameIs_Ambiguous() {
//        given
//        when
        Gender gender = peopleService.guessGenderWithSecondVariant(nameFirst);
//        then
        assertThat(gender, equalTo(Gender.INCONCLUSIVE));
    }
}