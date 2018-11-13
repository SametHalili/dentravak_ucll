package be.ucll.dentravak.controller;

import be.ucll.dentravak.model.Sandwich;
import be.ucll.dentravak.repository.SandwichRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SandwichController {
    private SandwichRepository sandwichRepository;

    public SandwichController(SandwichRepository sandwichRepository) {
        this.sandwichRepository = sandwichRepository;
    }

    @RequestMapping("/sandwiches")
    public List<Sandwich> getLunches() {
        return sandwichRepository.findAll();
    }
}