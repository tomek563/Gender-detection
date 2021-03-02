package piatek.genderdetection.algorithm;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BinarySearch implements Search {
    public List<String> search(List<String> elements, String fullName) {
        Collections.sort(elements);
        List<String> foundElements = new ArrayList<>();
        int firstIndex = 0;
        int lastIndex = elements.size() - 1;
        while (firstIndex <= lastIndex) {
            int searchingWordIndex = firstIndex + (lastIndex - firstIndex) / 2;
            // Check if name is present at mid
            if (elements.get(searchingWordIndex).toLowerCase().equals(fullName)) {
                foundElements.add(elements.get(searchingWordIndex));
                return foundElements;
            }
            // If name greater, ignore left half
            if (elements.get(searchingWordIndex).toLowerCase().compareTo(fullName) < 0) {
                firstIndex = searchingWordIndex + 1;
            }
            // If name is smaller, ignore right half
            else {
                lastIndex = searchingWordIndex - 1;
            }
        }
        return foundElements;
    }
}
