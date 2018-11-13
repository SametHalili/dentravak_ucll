package be.ucll.dentravak.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import be.ucll.dentravak.model.Sandwich;
<<<<<<< HEAD
import be.ucll.dentravak.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> b920efc973cfa4467d7458a0e2b5b1f570a9ed41
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SandwichController {
    @Autowired
    private SandwichRepository sandwichRepository;

    @RequestMapping("/lunches")
    public List<Sandwich> getLunches() {
        return sandwichRepository.getAll();
    }
}