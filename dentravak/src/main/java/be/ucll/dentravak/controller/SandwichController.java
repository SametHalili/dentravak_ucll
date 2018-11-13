package be.ucll.dentravak.controller;

import be.ucll.dentravak.model.Sandwich;
import be.ucll.dentravak.repository.SandwichRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
public class SandwichController {
    private SandwichRepository sandwichRepository;

    public SandwichController(SandwichRepository sandwichRepository) {
        this.sandwichRepository = sandwichRepository;
    }

    @RequestMapping("/sandwiches")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Sandwich> getSandwiches() {
        return sandwichRepository.findAll();
    }


    @RequestMapping(value = "/sandwiches", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addSandwich(@RequestBody Sandwich newSandwich) {
        sandwichRepository.save(newSandwich);
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.PUT)
    public void updateSandwich(@PathVariable("id") UUID id, @RequestBody Sandwich updatedSandwich) {
        if(sandwichRepository.existsById(id) && updatedSandwich.getId().equals(id))
            sandwichRepository.save(updatedSandwich);
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.DELETE)
    public void deleteSandwich(@PathVariable("id") UUID id) {
        if(sandwichRepository.existsById(id)) sandwichRepository.delete(sandwichRepository.findById(id).get());
    }
}