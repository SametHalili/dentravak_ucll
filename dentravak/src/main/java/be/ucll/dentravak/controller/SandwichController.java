package be.ucll.dentravak.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import be.ucll.dentravak.model.Sandwich;
import be.ucll.dentravak.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SandwichController {
    @Autowired
    private SandwichRepository sandwichRepository;

    @RequestMapping("/sandwiches")
    public List<Sandwich> getLunches() {
        return sandwichRepository.findAll();
    }
}