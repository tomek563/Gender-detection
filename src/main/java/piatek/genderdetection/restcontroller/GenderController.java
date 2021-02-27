package piatek.genderdetection.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import piatek.genderdetection.model.Gender;
import piatek.genderdetection.model.Sex;
import piatek.genderdetection.service.GenderService;

import java.util.List;

@RestController
@RequestMapping("/api/gender")
public class GenderController {
    private final GenderService genderService;

    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }
    @GetMapping("/1")
    public ResponseEntity<Sex> guessGenderWithFirstVariant(@RequestParam String name) {
        Sex sex = genderService.guessGenderWithFirstVariant(name);
        return ResponseEntity.ok(sex);
    }
    @GetMapping("/2")
    public ResponseEntity<Sex> guessGenderWithSecondVariant(@RequestParam String name) {
        Sex sex = genderService.guessGenderWithSecondVariant(name);
        return ResponseEntity.ok(sex);
    }
    @GetMapping("/tokens")
    public ResponseEntity<List<Gender>> getAllTokensList() {
        List<Gender> allAvailableTokens = genderService.getAllAvailableTokens();
        return ResponseEntity.ok(allAvailableTokens);
    }
}
