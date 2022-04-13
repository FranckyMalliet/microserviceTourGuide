package microserviceTourGuide.services;

import microserviceTourGuide.beans.Trip;
import microserviceTourGuide.models.Reward;
import microserviceTourGuide.models.User;
import microserviceTourGuide.proxies.MicroserviceTripProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    private MicroserviceTripProxy microserviceTripProxy;

    public TripService(MicroserviceTripProxy microserviceTripProxy) {
        this.microserviceTripProxy = microserviceTripProxy;
    }

    /**
     * This method calculate the amount of reward points of a user,
     * after that, it will gather trip prices and the user preferences
     * into a list.
     * @param user
     * @return a list of trip objects
     */

    public List<Trip> getTripDeals(User user) {
        int cumulativeRewardPoints = user.getUserRewards().stream().mapToInt(Reward::getRewardPoints).sum();

        List<Trip> tripList = microserviceTripProxy.getTripsPrices(
                user.getUserId(),
                user.getUserPreferences().getNumberOfAdults(),
                user.getUserPreferences().getNumberOfChildren(),
                user.getUserPreferences().getTripDuration(), cumulativeRewardPoints);
        user.setTripDeals(tripList);

        return tripList;
    }
}
