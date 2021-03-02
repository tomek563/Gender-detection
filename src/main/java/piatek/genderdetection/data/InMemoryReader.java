package piatek.genderdetection.data;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import piatek.genderdetection.model.People;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component(value = "memory")
public class InMemoryReader implements NamesReader {
    private final People people;

    public InMemoryReader() {
        people = new People(prepareFemaleList(), prepareMaleList());
    }

    @Override
    public List<String> getMales() {
        return people.getMale();
    }

    @Override
    public List<String> getFemales() {
        return people.getFemale();
    }

    private List<String> prepareMaleList() {
        return new ArrayList<>(Arrays.asList(
                "Antoni", "Jan", "Jakub", "Aleksander", "Szymon", "Franciszek", "Filip", "Mikołaj",
                "Wojciech", "Adam", "Kacper", "Daniel", "Stanisław", "Marcel", "Leon", "Michał", "Nikodem",
                "Tymon", "Ignacy", "Wiktor", "Igor", "Sam"
        ));
    }

    private List<String> prepareFemaleList() {
        return new ArrayList<>(Arrays.asList(
                "Zuzanna", "Julia", "Zofia", "Maja", "Hanna", "Lena", "Alicja", "Maria", "Oliwia",
                "Amelia", "Wiktoria", "Aleksandra", "Halina", "Antonina", "Laura", "Emilia", "Pola", "Marcelina",
                "Natalia", "Liliana", "Iga", "Sam"
        ));
    }
}
