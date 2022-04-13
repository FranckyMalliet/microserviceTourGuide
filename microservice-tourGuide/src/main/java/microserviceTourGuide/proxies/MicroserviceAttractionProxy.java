package microserviceTourGuide.proxies;

import microserviceTourGuide.beans.Attraction;
import microserviceTourGuide.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "attraction", url = "${url.attraction:http://attraction}", configuration = FeignConfig.class)
public interface MicroserviceAttractionProxy {

    @GetMapping(value = "/Attractions")
    List<Attraction> findAllAttractions();

    @GetMapping(value = "/fiveClosestTouristAttractions")
    List<List<String>> fiveClosestTouristAttractions(@RequestParam double latitude,
                                                     @RequestParam double longitude);

    @GetMapping(value = "/createAttractionRewardPoints")
    int createAttractionRewardPoints();
}
