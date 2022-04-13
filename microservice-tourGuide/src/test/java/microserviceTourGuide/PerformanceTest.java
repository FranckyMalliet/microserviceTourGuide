package microserviceTourGuide;

import microserviceTourGuide.helper.InternalTestHelper;
import microserviceTourGuide.models.User;
import microserviceTourGuide.services.RewardService;
import microserviceTourGuide.services.VisitedLocationService;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import microserviceTourGuide.tracker.Tracker;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class PerformanceTest {

	private final static Logger logger = LoggerFactory.getLogger(PerformanceTest.class);

	@Autowired
	private VisitedLocationService visitedLocationService;

	@Autowired
	private RewardService rewardService;

	@Autowired
	private InternalTestHelper internalTestHelper;

	/*
	 * A note on performance improvements:
	 *     
	 *     The number of users generated for the high volume tests can be easily adjusted via this method:
	 *     
	 *     		InternalTestHelper.setInternalUserNumber(100000);
	 *     
	 *     
	 *     These tests can be modified to suit new solutions, just as long as the performance metrics
	 *     at the end of the tests remains consistent. 
	 * 
	 *     These are performance metrics that we are trying to hit:
	 *     
	 *     highVolumeTrackLocation: 100,000 users within 15 minutes:
	 *     		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     *
     *     highVolumeGetRewards: 100,000 users within 20 minutes:
	 *          assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	 */

	@Test
	public void highVolumeTrackLocation() {
		logger.info("TestMode enabled");
		logger.info("Initializing users");

		// Users should be incremented up to 100,000, and test finishes within 15 minutes
		InternalTestHelper.setInternalUserNumber(100000);
		//internalTestHelper.initializeInternalUsers();
		LocalTime localTime = LocalTime.now();
		List<User> allUsers = internalTestHelper.initializeInternalUsers();
		logger.info("Finished initializing users" + " number of users " + allUsers.size() + " " + localTime.getNano());

		Tracker tracker = new Tracker(visitedLocationService, allUsers);
		Runtime.getRuntime().addShutdownHook(new Thread(tracker::stopTracking));

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		allUsers.parallelStream().forEach(user -> {
			rewardService.calculateAllRewards(user);
		});


		tracker.stopTracking();
		stopWatch.stop();

		System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
		Assertions.assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));

		allUsers.clear();
	}

	@Test
	public void highVolumeGetRewards() {
		logger.info("TestMode enabled");
		logger.debug("Initializing users");

		// Users should be incremented up to 100,000, and test finishes within 20 minutes
		InternalTestHelper.setInternalUserNumber(100);
		List<User> allUsers =  internalTestHelper.initializeInternalUsers();
		logger.info("Finished initializing users");

		Tracker tracker = new Tracker(visitedLocationService, allUsers);
		Runtime.getRuntime().addShutdownHook(new Thread(tracker::stopTracking));

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

	    allUsers.parallelStream().forEach(user -> {
	    	rewardService.calculateAllRewards(user);
		});

		stopWatch.stop();
		tracker.stopTracking();

		System.out.println("highVolumeGetRewards: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds."); 
		Assertions.assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));

		allUsers.clear();
	}
}
