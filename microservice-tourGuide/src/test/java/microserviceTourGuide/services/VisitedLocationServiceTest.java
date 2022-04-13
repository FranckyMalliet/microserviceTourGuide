package microserviceTourGuide.services;

import microserviceTourGuide.beans.VisitedLocation;
import microserviceTourGuide.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class VisitedLocationServiceTest {

    @Autowired
    private VisitedLocationService visitedLocationService;

    @Test
    public void getUserLastVisitedLocationTest() {
        //GIVEN
        User user = new User(UUID.randomUUID(), "Johnson", "0645656565", "Shadowrun@Cyberpunk.com");
        user.addToVisitedLocations(new VisitedLocation(user.getUserId(), 33, -116, new Date()));
        user.addToVisitedLocations(new VisitedLocation(user.getUserId(), 44, -111, new Date()));
        user.addToVisitedLocations(new VisitedLocation(user.getUserId(), 36, -92, new Date()));

        //WHEN
        VisitedLocation visitedLocation = visitedLocationService.getUserLastVisitedLocation(user);

        //THEN
        Assertions.assertNotNull(visitedLocation);
    }

    @Test
    public void findAllMostRecentUsersVisitedLocationsTest() {
        //GIVEN
        User user = new User(UUID.randomUUID(), "Sauron", "0645656565", "Mordor@middleEarth.com");
        user.addToVisitedLocations(new VisitedLocation(user.getUserId(), 33, -116, new Date()));
        user.addToVisitedLocations(new VisitedLocation(user.getUserId(), 44, -111, new Date()));
        user.addToVisitedLocations(new VisitedLocation(user.getUserId(), 36, -92, new Date()));

        List<User> userList = new ArrayList<>();

        //WHEN
        Map<String, List<Double>> usersMostRecentLocations = visitedLocationService.findAllMostRecentUsersVisitedLocations(userList);

        //THEN
        Assertions.assertNotNull(usersMostRecentLocations);
    }
}
