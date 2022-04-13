package microserviceTourGuide.proxies;

import microserviceTourGuide.beans.VisitedLocation;
import microserviceTourGuide.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "visited-location", url =  "${url.visited-location:http://visited-location}", configuration = FeignConfig.class)
public interface MicroserviceVisitedLocationProxy {

    @GetMapping(value = "/createVisitedLocation")
    VisitedLocation createVisitedLocation(@RequestParam("userId") UUID userId);
}
