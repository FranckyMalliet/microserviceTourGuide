package microserviceTourGuide.helper;

import microserviceTourGuide.beans.VisitedLocation;
import microserviceTourGuide.models.User;
import microserviceTourGuide.models.UserPreferences;
import microserviceTourGuide.proxies.MicroserviceVisitedLocationProxy;
import microserviceTourGuide.services.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class InternalTestHelper {

	private final static Logger logger = LoggerFactory.getLogger(InternalTestHelper.class);
	private MicroserviceVisitedLocationProxy microserviceVisitedLocationProxy;
	private RewardService rewardService;

	public InternalTestHelper(MicroserviceVisitedLocationProxy microserviceVisitedLocationProxy, RewardService rewardService){
		this.microserviceVisitedLocationProxy = microserviceVisitedLocationProxy;
		this.rewardService = rewardService;
	}

	// Set this default up to 100,000 for testing
	private static int internalUserNumber = 100;
	
	public static void setInternalUserNumber(int internalUserNumber) {
		InternalTestHelper.internalUserNumber = internalUserNumber;
	}

	public static int getInternalUserNumber() {
		return internalUserNumber;
	}

	/**********************************************************************************
	 *
	 * Methods Below: For Internal Testing
	 *
	 **********************************************************************************/

	private static final String tripPricerApiKey = "test-server-api-key";
	// Database connection will be used for external users, but for testing purposes internal users are provided and stored in memory
	private List<User> internalUserMap = new ArrayList<>();

	public List<User> initializeInternalUsers() {
		IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
			String userName = "internalUser" + i;
			String phone = "000";
			String email = userName + "@tourGuide.com";
			User user = new User(UUID.randomUUID(), userName, phone, email);
			//create random visitedLocation objects
			generateUserLocationHistory(user);
			createRandomUserPreferences(user);

			internalUserMap.add(user);
		});

		logger.debug("Created " + internalUserMap + " internal test users.");
		return internalUserMap;
	}

	public List<User> getInternalUserMap(){
		return internalUserMap;
	}

	private void generateUserLocationHistory(User user) {
		IntStream.range(0, 3).forEach(i-> {
			user.addToVisitedLocations(createVisitedLocation(user.getUserId()));
		});
	}

	private void createRandomUserPreferences(User user){
		UserPreferences userPreferences = new UserPreferences();
		userPreferences.setTripDuration((int) (Math.random()* (10-1) + 1));
		userPreferences.setNumberOfAdults((int) (Math.random()*(4-1) + 1));
		userPreferences.setNumberOfChildren((int) (Math.random()*(4-1) + 1));
		userPreferences.setTicketQuantity(userPreferences.getNumberOfAdults() + userPreferences.getNumberOfChildren());

		user.setUserPreferences(userPreferences);
	}

	/**
	 * Using a UUID userID, this method create a visitedLocation object
	 * with random longitude and latitude.
	 * @param userId
	 * @return a visitedLocation object
	 */

	public VisitedLocation createVisitedLocation(UUID userId) {
		VisitedLocation visitedLocation = new VisitedLocation(userId, generateRandomLatitude(), generateRandomLongitude(), getRandomTime());

		logger.debug("Creating a new VisitedLocation, userId : " + visitedLocation.getUserId()
				+ ", latitude : " + visitedLocation.getLatitude()
				+ ", longitude : " + visitedLocation.getLongitude()
				+ ", time : " + visitedLocation.getTimeVisited());
		return visitedLocation;
	}

	private double generateRandomLongitude() {
		double leftLimit = -180;
		double rightLimit = 180;
		return Math.random() * (rightLimit - leftLimit) + leftLimit;
	}

	private double generateRandomLatitude() {
		double leftLimit = -85.05112878;
		double rightLimit = 85.05112878;
		return Math.random() * (rightLimit - leftLimit) + leftLimit;
	}

	private Date getRandomTime() {
		LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
		return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
	}
}
