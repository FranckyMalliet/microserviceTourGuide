package microserviceTourGuide.controllers;

import microserviceTourGuide.services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TripControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private TripService tripService;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenAUserName_ReturnAListOfTrips() throws Exception {
        //GIVEN
        String userName = "internalUser1";

        //THEN
        mockMvc.perform(get("/getTripDeals")
                        .param("userName", userName))
                .andExpect(status().isOk());
    }
}
