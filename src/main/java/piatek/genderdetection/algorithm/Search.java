package piatek.genderdetection.algorithm;

import org.springframework.stereotype.Component;

import java.util.List;

public interface Search {
    List<String> search(List<String> elements, String name);
}
