package microserviceTourGuide.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import microserviceTourGuide.proxies.MicroserviceAttractionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttractionController {

    private final static Logger logger = LoggerFactory.getLogger(AttractionController.class);
    private MicroserviceAttractionProxy microserviceAttractionProxy;
    private final ObjectMapper mapper = new ObjectMapper();

    public AttractionController(MicroserviceAttractionProxy microserviceAttractionProxy){
        this.microserviceAttractionProxy = microserviceAttractionProxy;
    }

    @GetMapping("/getNearbyAttractions")
    public String getNearbyAttractions(@RequestParam double userLatitude, @RequestParam double userLongitude) throws JsonProcessingException {
        List<List<String>> fiveClosestAttractionsInformation = microserviceAttractionProxy.fiveClosestTouristAttractions(userLatitude, userLongitude);

        logger.debug("Retrieving the five closest attractions to the user");
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fiveClosestAttractionsInformation);
    }
}
