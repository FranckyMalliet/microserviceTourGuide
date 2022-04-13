package microserviceTourGuide.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import microserviceTourGuide.helper.InternalTestHelper;
import microserviceTourGuide.models.Reward;
import microserviceTourGuide.models.User;
import microserviceTourGuide.services.RewardService;
import microserviceTourGuide.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RewardController {

    private final static Logger logger = LoggerFactory.getLogger(RewardController.class);
    private RewardService rewardService;
    private UserService userService;
    private final InternalTestHelper internalTestHelper;
    private ObjectMapper mapper = new ObjectMapper();

    public RewardController(RewardService rewardService, UserService userService, InternalTestHelper internalTestHelper){
        this.rewardService = rewardService;
        this.userService = userService;
        this.internalTestHelper = internalTestHelper;
    }

    /**
     * You need to use the endpoint "/initializeUsers" once before using this endpoint
     */

    @GetMapping("/getRewards")
    public List<Reward> getRewards(@RequestParam String userName){
        User user = userService.findUserByUserName(internalTestHelper.getInternalUserMap(), userName);
        rewardService.calculateAllRewards(user);
        List<Reward> userRewardInformation = user.getUserRewards();

        logger.debug("Retrieving rewards of a user named : " + userName);
        return userRewardInformation;
    }
}
