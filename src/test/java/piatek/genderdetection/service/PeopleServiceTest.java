package piatek.genderdetection.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import piatek.genderdetection.algorithm.Algorithm;
import piatek.genderdetection.model.Gender;
import piatek.genderdetection.model.People;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class PeopleServiceTest {
    String nameFirst;
    String nameSecond;
    String nameThird;
    String nameFourth;
    String nameFifth;
    PeopleService peopleService;
    @Mock
    Algorithm algorithm;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        nameFirst = "Jan Maria Rokita";
        nameSecond = "Maria Jan Rokita";
        nameThird = "Chair";
        nameFourth = "Sam Daniel";
        nameFifth = "Marcel Laura Lena";
        peopleService = new PeopleService(new People(), algorithm);
    }

    @Test
    void guessGenderWithFirstVariant_Should_Return_Male_When_Name_IsRecognized_As_Male() {
//        given
        when(algorithm.binarySearch(ArgumentMatchers.any(),anyString()))
                .thenReturn(new ArrayList<>(Collections.singletonList("Jan")))
                .thenReturn(new ArrayList<>());
//        when
        Gender gender = peopleService.guessGenderWithFirstVariant(nameFirst);
//        then
        assertThat(gender, equalTo(Gender.MALE));
        verify(algorithm, times(2)).binarySearch(ArgumentMatchers.any(), anyString());
    }
    @Test
    void guessGenderWithFirstVariant_Should_Return_Female_When_Name_IsRecognized_As_Female() {
//        given
        when(algorithm.binarySearch(ArgumentMatchers.any(),anyString()))
                .thenReturn(new ArrayList<>())
                .thenReturn(new ArrayList<>(Collections.singletonList("Maria")));
//        when
        Gender gender = peopleService.guessGenderWithFirstVariant(nameSecond);
//        then
        assertThat(gender, equalTo(Gender.FEMALE));
        verify(algorithm, times(2)).binarySearch(ArgumentMatchers.any(), anyString());
    }
    @Test
    void guessGenderWithFirstVariant_Should_Return_Inconclusive_When_ThereIs_No_Such_Name() {
//        given
        when(algorithm.binarySearch(ArgumentMatchers.any(),anyString()))
                .thenReturn(new ArrayList<>())
                .thenReturn(new ArrayList<>());
//        when
        Gender gender = peopleService.guessGenderWithFirstVariant(nameThird);
//        then
        assertThat(gender, equalTo(Gender.INCONCLUSIVE));
    }
    @Test
    void guessGenderWithFirstVariant_Should_Return_Inconclusive_When_Name_BelongsTo_Both_Gender() {
//        given
        when(algorithm.binarySearch(ArgumentMatchers.any(),anyString()))
                .thenReturn(new ArrayList<>(Collections.singletonList("Sam")))
                .thenReturn(new ArrayList<>(Collections.singletonList("Sam")));
//        when
        Gender gender = peopleService.guessGenderWithFirstVariant(nameFourth);
//        then
        assertThat(gender, equalTo(Gender.INCONCLUSIVE));
        verify(algorithm, times(2)).binarySearch(ArgumentMatchers.any(), anyString());
    }


    @Test
    void guessGenderWithSecondVariant_Should_Return_Male_When_Name_Has_More_Male_SubNames() {
//        given
        String[] split = nameFourth.split(" ");
        int numberOfWords = split.length;
        when(algorithm.binarySearch(ArgumentMatchers.any(),anyString()))
                .thenReturn(new ArrayList<>(Arrays.asList("Sam", "Daniel")))
                .thenReturn(new ArrayList<>(Collections.singletonList("Sam")));
//        when
        Gender gender = peopleService.guessGenderWithSecondVariant(nameFourth);
//        then
        assertThat(gender, equalTo(Gender.MALE));
        verify(algorithm, times(2*numberOfWords)).binarySearch(ArgumentMatchers.any(), anyString());
    }
    @Test
    void guessGenderWithSecondVariant_Should_Return_Female_When_Name_Has_More_Female_SubNames() {
//        given
        String[] split = nameFifth.split(" ");
        int numberOfWords = split.length;
        when(algorithm.binarySearch(ArgumentMatchers.any(),anyString()))
                .thenReturn(new ArrayList<>(Collections.singletonList("Marcel")))
                .thenReturn(new ArrayList<>(Arrays.asList("Laura", "Lena")));
//        when
        Gender gender = peopleService.guessGenderWithSecondVariant(nameFifth);
//        then
        assertThat(gender, equalTo(Gender.FEMALE));
        verify(algorithm, times(2*numberOfWords)).binarySearch(ArgumentMatchers.any(), anyString());
    }
    @Test
    void guessGenderWithSecondVariant_Should_Return_Inconclusive_When_NameIs_Ambiguous() {
//        given
        String[] split = nameFirst.split(" ");
        int numberOfWords = split.length;
        when(algorithm.binarySearch(ArgumentMatchers.any(),anyString()))
                .thenReturn(new ArrayList<>(Collections.singletonList("Jan")))
                .thenReturn(new ArrayList<>(Collections.singletonList("Maria")));
//        when
        Gender gender = peopleService.guessGenderWithSecondVariant(nameFirst);
//        then
        assertThat(gender, equalTo(Gender.INCONCLUSIVE));
        verify(algorithm, times(2*numberOfWords)).binarySearch(ArgumentMatchers.any(), anyString());
    }
}