package piatek.genderdetection.algorithm;

import org.springframework.stereotype.Component;

import java.util.List;

public interface Algorithm {
    List<String> binarySearch(List<String> elements, String name);
}
