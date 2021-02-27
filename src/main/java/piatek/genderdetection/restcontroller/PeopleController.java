package piatek.genderdetection.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import piatek.genderdetection.model.Gender;
import piatek.genderdetection.service.PeopleService;

import java.util.List;

@RestController
@RequestMapping("/api/gender")
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }
    @GetMapping("/1")
    public ResponseEntity<Gender> guessGenderWithFirstVariant(@RequestParam String name) {
        Gender gender = peopleService.guessGenderWithFirstVariant(name);
        return ResponseEntity.ok(gender);
    }
    @GetMapping("/2")
    public ResponseEntity<Gender> guessGenderWithSecondVariant(@RequestParam String name) {
        Gender gender = peopleService.guessGenderWithSecondVariant(name);
        return ResponseEntity.ok(gender);
    }
    @GetMapping("/tokens/male")
    public ResponseEntity<List<String>> getAllMaleTokensList() {
        List<String> allAvailableTokens = peopleService.getAllAvailableMaleTokens();
        return ResponseEntity.ok(allAvailableTokens);
    }
    @GetMapping("/tokens/female")
    public ResponseEntity<List<String>> getAllFemaleTokensList() {
        List<String> allAvailableTokens = peopleService.getAllAvailableFemaleTokens();
        return ResponseEntity.ok(allAvailableTokens);
    }
}
