package be.ucll.dentravak.controller;

import be.ucll.dentravak.model.Sandwich;
import be.ucll.dentravak.model.SandwichPreferences;
import be.ucll.dentravak.repository.SandwichRepository;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
public class SandwichController {
    private SandwichRepository sandwichRepository;

    @Inject
    private DiscoveryClient discoveryClient;

    @Inject
    private RestTemplate restTemplate;

    public SandwichController(SandwichRepository sandwichRepository) {
        this.sandwichRepository = sandwichRepository;
    }

    @GetMapping("/getpreferences/{emailAddress}")
    public SandwichPreferences getPreferences(@PathVariable String emailAddress) throws RestClientException, ServiceUnavailableException {
        URI service = recommendationServiceUrl()
                .map(s -> s.resolve("/recommendation/recommend/" + emailAddress))
                .orElseThrow(ServiceUnavailableException::new);
        return restTemplate
                .getForEntity(service, SandwichPreferences.class)
                .getBody();
    }

    public Optional<URI> recommendationServiceUrl() {
        return discoveryClient.getInstances("recommendation")
                .stream()
                .map(si -> si.getUri())
                .findFirst();
    }

    @RequestMapping("/sandwiches")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Sandwich> getSandwiches() {
        try {
            SandwichPreferences preferences = getPreferences("ronald.dehuysser@ucll.be");
            //TODO: sort allSandwiches by float in preferences
            Iterable<Sandwich> allSandwiches = sandwichRepository.findAll();

            return allSandwiches;
        } catch (ServiceUnavailableException e) {
            return sandwichRepository.findAll();
        }
    }

    @RequestMapping("/sandwiches/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Sandwich getSandwiches(@PathVariable("id") UUID id) {
        return sandwichRepository.findById(id).get();
    }

    @RequestMapping(value = "/sandwiches", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Sandwich addSandwich(@RequestBody Sandwich newSandwich) {
        sandwichRepository.save(newSandwich);
        return newSandwich;
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.PUT)
    public Sandwich updateSandwich(@PathVariable("id") UUID id, @RequestBody Sandwich updatedSandwich) {
        if(sandwichRepository.existsById(id) && updatedSandwich.getId().equals(id))
            sandwichRepository.save(updatedSandwich);

        return updatedSandwich;
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.DELETE)
    public void deleteSandwich(@PathVariable("id") UUID id) {
        if(sandwichRepository.existsById(id)) sandwichRepository.delete(sandwichRepository.findById(id).get());
    }
}