package microserviceTourGuide.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import microserviceTourGuide.beans.Trip;
import microserviceTourGuide.helper.InternalTestHelper;
import microserviceTourGuide.models.User;
import microserviceTourGuide.services.TripService;
import microserviceTourGuide.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TripController {

    private final static Logger logger = LoggerFactory.getLogger(TripController.class);
    private ObjectMapper mapper = new ObjectMapper();
    private TripService tripService;
    private UserService userService;
    private final InternalTestHelper internalTestHelper;

    public TripController(TripService tripService, UserService userService, InternalTestHelper internalTestHelper){
        this.tripService = tripService;
        this.userService = userService;
        this.internalTestHelper = internalTestHelper;
    }

    /**
     * You need to use the endpoint "/initializeUsers" once before using this endpoint
     */

    @GetMapping("/getTripDeals")
    public List<Trip> getTripDeals(@RequestParam String userName){
        logger.debug("Retrieving Trip deals of a user named : " + userName);
        User user = userService.findUserByUserName(internalTestHelper.getInternalUserMap(), userName);
        return tripService.getTripDeals(user);
    }
}
