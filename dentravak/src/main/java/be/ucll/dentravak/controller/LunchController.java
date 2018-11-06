package be.ucll.dentravak.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import be.ucll.dentravak.model.Lunch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LunchController {
    @RequestMapping("/lunches")
    public List<Lunch> getLunches() {
        return null;
    }
}