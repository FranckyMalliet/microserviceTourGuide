package microserviceTourGuide.services;

import microserviceTourGuide.beans.VisitedLocation;
import microserviceTourGuide.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VisitedLocationService {

    private final static Logger logger = LoggerFactory.getLogger(VisitedLocationService.class);

    /**
     * This method use a list of visitedLocation from a user and retrieve the last visitedLocation.
     * Then, create a visitedLocation and calculate a reward.
     * @param user
     * @return a visitedLocation object
     */

    public VisitedLocation getUserLastVisitedLocation(User user) {
        VisitedLocation visitedLocation = findTheMostRecentVisitedLocation(user);

        logger.debug("Retrieve the last visitedLocation" + visitedLocation.getUserId() + " from user : " + user.getUserId());
        return visitedLocation;
    }

    /**
     * This method use a list of user and add the last visitedLocation latitude
     * and longitude of each user into a map.
     * @return a map list of double values
     */

    public Map<String, List<Double>> findAllMostRecentUsersVisitedLocations(List<User> userList){
        Map<String, List<Double>> usersMostRecentLocations = new HashMap<>();

        for(User user : userList){
            String userId = String.valueOf(user.getUserId());

            VisitedLocation visitedLocation = findTheMostRecentVisitedLocation(user);

            List<Double> visitedLocationCoordinates = new ArrayList<>();
            visitedLocationCoordinates.add(visitedLocation.getLatitude());
            visitedLocationCoordinates.add(visitedLocation.getLongitude());

            usersMostRecentLocations.put(userId, visitedLocationCoordinates);
        }

        return usersMostRecentLocations;
    }

    /**
     * This method use a collector to iterate through the list of visitedLocation of a user.
     * Find the last visitedLocation by his most recent date.
     * @param user
     * @return the most recent visitedLocation
     */

    private VisitedLocation findTheMostRecentVisitedLocation(User user){
        return Collections.max(user.getVisitedLocation(), Comparator.comparing(VisitedLocation::getTimeVisited));
    }
}