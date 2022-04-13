package microserviceTourGuide.services;

import microserviceTourGuide.beans.VisitedLocation;
import microserviceTourGuide.models.Reward;
import microserviceTourGuide.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
public class RewardServiceTest {

	@Autowired
	private RewardService rewardService;

	@Test
	public void calculateAllRewardsTest(){
		//GIVEN
		User user = new User(UUID.randomUUID(), "Harkonnen", "0645656565", "Gebprime@hotmail.com");

		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), 33, -116, new Date()));
		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), 44, -111, new Date()));
		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), 36, -92, new Date()));

		int rewardPoints = 0;

		//WHEN
		rewardService.calculateAllRewards(user);
		for(Reward reward : user.getUserRewards()){
			rewardPoints += reward.getRewardPoints();
		}

		//THEN
		Assertions.assertTrue(rewardPoints > 0);
	}
}
