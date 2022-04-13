package microserviceTourGuide.controllers;

import microserviceTourGuide.beans.VisitedLocation;
import microserviceTourGuide.helper.InternalTestHelper;
import microserviceTourGuide.services.UserService;
import microserviceTourGuide.services.VisitedLocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class VisitedLocationController {

    private final static Logger logger = LoggerFactory.getLogger(VisitedLocationController.class);
    private VisitedLocationService visitedLocationService;
    private UserService userService;
    private final InternalTestHelper internalTestHelper;
    private final ObjectMapper mapper = new ObjectMapper();

    public VisitedLocationController(VisitedLocationService visitedLocationService, UserService userService, InternalTestHelper internalTestHelper){
        this.visitedLocationService = visitedLocationService;
        this.userService = userService;
        this.internalTestHelper = internalTestHelper;
    }

    /**
     * You need to use the endpoint "/initializeUsers" once before using each of these endpoints
     */

    @GetMapping("/getLastVisitedLocation")
    public VisitedLocation getLastVisitedLocation(@RequestParam String userName) {
        VisitedLocation visitedLocation = visitedLocationService.getUserLastVisitedLocation(userService.
                findUserByUserName(internalTestHelper.getInternalUserMap(), userName));
        logger.debug("Retrieving location of a user named : " + userName);
        return visitedLocation;
    }

    @GetMapping("/getAllUsersCurrentVisitedLocations")
    public String getAllUsersCurrentVisitedLocations() throws JsonProcessingException {
        Map<String, List<Double>> usersMostRecentLocations = visitedLocationService.findAllMostRecentUsersVisitedLocations(internalTestHelper.getInternalUserMap());

        logger.debug("Retrieving all users most recent location");
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usersMostRecentLocations);
    }
}
