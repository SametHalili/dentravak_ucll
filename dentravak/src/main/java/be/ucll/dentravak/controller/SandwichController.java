package be.ucll.dentravak.controller;

import be.ucll.dentravak.model.Sandwich;
import be.ucll.dentravak.model.SandwichPreferences;
import be.ucll.dentravak.repository.SandwichRepository;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.google.common.collect.Lists;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

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
            ArrayList<Sandwich> allSandwiches = Lists.newArrayList(sandwichRepository.findAll());

            ArrayList<UUID> orderSandwichIds = new ArrayList<>();

            addIdsInOrder(preferences, orderSandwichIds);

            for(int i = 0; i < orderSandwichIds.size(); i++) {
                for(int j = 0; j < allSandwiches.size(); j++) {
                    if(orderSandwichIds.get(i).equals(allSandwiches.get(j).getId())) {
                        Collections.swap(allSandwiches, i, j);
                    }
                }
            }

            return allSandwiches;
        } catch (ServiceUnavailableException e) {
            return sandwichRepository.findAll();
        }
    }

    private void addIdsInOrder(SandwichPreferences preferences, ArrayList<UUID> sandwichIds) {
        if(preferences.size() > 1) {
            float smallestRating = -1;
            for (UUID key : preferences.keySet()) {
                float currentRating = preferences.getRatingForSandwich(key);
                if (smallestRating < currentRating) {
                    smallestRating = currentRating;
                    sandwichIds.add(key);
                    preferences.remove(key);
                    addIdsInOrder(preferences, sandwichIds);
                    break;
                }
            }
        } else {
            sandwichIds.add((UUID) preferences.keySet().toArray()[0]);
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