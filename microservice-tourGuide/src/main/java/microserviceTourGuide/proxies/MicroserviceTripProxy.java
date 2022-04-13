package microserviceTourGuide.proxies;

import microserviceTourGuide.beans.Trip;
import microserviceTourGuide.config.FeignConfig;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "trip", url =  "${url.trip:http://trip}", configuration = FeignConfig.class)
public interface MicroserviceTripProxy {

    @GetMapping(value = "/getTripName")
    String getTripName();

    @GetMapping(value = "/getTripsPrices")
    List<Trip> getTripsPrices(@RequestParam UUID attractionId,
                              @RequestParam int adults,
                              @RequestParam int children,
                              @RequestParam int nightsStay,
                              @RequestParam int rewardsPoints);
}