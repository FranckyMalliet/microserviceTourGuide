package microserviceTourGuide.controllers;

import microserviceTourGuide.helper.InternalTestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TourGuideController {

    private final static Logger logger = LoggerFactory.getLogger(TourGuideController.class);
    private InternalTestHelper internalTestHelper;

    public TourGuideController(InternalTestHelper internalTestHelper){
        this.internalTestHelper = internalTestHelper;
    }

    @GetMapping("/initializeUsers")
    public String initializeUsers(){
        logger.info("Users initialized");
        internalTestHelper.initializeInternalUsers();
        return "Users Initialized";
    }

    @GetMapping("/index")
    public String index() {
        return "Greetings from TourGuide!";
    }
}
