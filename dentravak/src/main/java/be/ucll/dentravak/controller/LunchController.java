package be.ucll.dentravak.controller;

import java.util.List;

import be.ucll.dentravak.model.Sandwich;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LunchController {
    @RequestMapping("/lunches")
    public List<Sandwich> getLunches() {
        return Sandwich.LunchBuilder.getListOfRandomLunches(10);
    }
}