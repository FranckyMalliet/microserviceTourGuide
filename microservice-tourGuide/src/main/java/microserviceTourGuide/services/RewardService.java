package microserviceTourGuide.services;

import microserviceTourGuide.beans.Attraction;
import microserviceTourGuide.models.Reward;
import microserviceTourGuide.models.User;
import microserviceTourGuide.proxies.MicroserviceAttractionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RewardService {

	private final static Logger logger = LoggerFactory.getLogger(RewardService.class);
	private MicroserviceAttractionProxy microserviceAttractionProxy;
	private List<Attraction> attractions;
	private int attractionProximityRange = 5000;

	public RewardService(MicroserviceAttractionProxy microserviceAttractionProxy) {
		this.microserviceAttractionProxy = microserviceAttractionProxy;
		attractions = microserviceAttractionProxy.findAllAttractions();
	}

	/**
	 * This method calculate reward from all user's visitedLocation
	 * and give reward points for each if the visitedLocation is in
	 * attraction proximity range.
	 * @param user
	 */

	public void calculateAllRewards(User user){

		user.getVisitedLocation().forEach(visitedLocation -> {
			attractions.forEach(attraction -> {

				double distance = getDistanceBetweenAttractionAndLocation(
						attraction.getLatitudeToRadian(), attraction.getLongitudeToRadian(),
						visitedLocation.getLatitudeToRadian(), visitedLocation.getLongitudeToRadian());

				if(distance <= attractionProximityRange){
					Reward userReward = new Reward(visitedLocation, attraction, createAttractionRewardPoints());
					user.addUserReward(userReward);
					logger.debug("adding reward to user points " + userReward.getRewardPoints());
				}
			});
		});
	}

	/**
	 * This method is used to give a random number to a reward object
	 * @return a random integer
	 */

	public int createAttractionRewardPoints() {
		int randomInt = ThreadLocalRandom.current().nextInt(1, 1000);

		logger.debug("RandomInt = " + randomInt);
		return randomInt;
	}

	private double getDistanceBetweenAttractionAndLocation(double attractionLatitude, double attractionLongitude, double locationLatitude, double locationLongitude) {

		double angle = Math.acos(Math.sin(attractionLatitude) * Math.sin(locationLatitude)
				+ Math.cos(attractionLatitude) * Math.cos(locationLatitude) * Math.cos(attractionLongitude - locationLongitude));

		double nauticalMiles = 60 * Math.toDegrees(angle);
		double statuteMiles = nauticalMiles * 1.15077945;

		return statuteMiles;
	}
}
