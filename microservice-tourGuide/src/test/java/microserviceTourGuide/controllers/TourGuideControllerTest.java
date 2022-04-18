package microserviceTourGuide.controllers;

import microserviceTourGuide.helper.InternalTestHelper;
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
public class TourGuideControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private InternalTestHelper internalTestHelper;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenAnUrl_InitializeAListOfUsers() throws Exception {
        //THEN
        mockMvc.perform(get("/initializeUsers"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAnUrl_SendAGreetingsMessage() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk());
    }
}
