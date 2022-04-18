package microserviceTourGuide.controllers;

import microserviceTourGuide.services.VisitedLocationService;
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
public class VisitedLocationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private VisitedLocationService visitedLocationService;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenAUserName_ReturnHisLastVisitedLocation() throws Exception {
        //GIVEN
        String userName = "internalUser1";

        //THEN
        mockMvc.perform(get("/getLastVisitedLocation")
                        .param("userName", userName))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnUrl_ReturnAListOfAllUsersLastVisitedLocation() throws Exception {
        //THEN
        mockMvc.perform(get("/getAllUsersCurrentVisitedLocations"))
                .andExpect(status().isOk());
    }
}
