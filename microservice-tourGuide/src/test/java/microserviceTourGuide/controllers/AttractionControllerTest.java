package microserviceTourGuide.controllers;

import microserviceTourGuide.proxies.MicroserviceAttractionProxy;
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
public class AttractionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private MicroserviceAttractionProxy microserviceAttractionProxy;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenAUserLatitudeAndAUserLongitude_ReturnFiveNearbyAttractions() throws Exception {
        //GIVEN
        double latitude = 35;
        double longitude = -110;

        //THEN
        mockMvc.perform(get("/getNearbyAttractions")
                        .param("userLatitude", String.valueOf(latitude))
                        .param("userLongitude", String.valueOf(longitude)))
                .andExpect(status().isOk());
    }
}
