package microserviceTourGuide.services;

import microserviceTourGuide.beans.Trip;
import microserviceTourGuide.helper.InternalTestHelper;
import microserviceTourGuide.models.User;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @Test
    public void getTripDeals() {
        InternalTestHelper.setInternalUserNumber(0);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        user.getUserPreferences().setAttractionProximity(1000);
        user.getUserPreferences().setLowerPricePoint(Money.of(250, "USD"));
        user.getUserPreferences().setHighPricePoint(Money.of(5000, "USD"));
        user.getUserPreferences().setTripDuration(7);
        user.getUserPreferences().setTicketQuantity(7);
        user.getUserPreferences().setNumberOfAdults(3);
        user.getUserPreferences().setNumberOfChildren(4);

        List<Trip> tripDeals = tripService.getTripDeals(user);

        Assertions.assertNotNull(tripDeals);
        Assertions.assertTrue(tripDeals.size() > 0);
        Assertions.assertEquals(1000, user.getUserPreferences().getAttractionProximity());
        Assertions.assertEquals("USD " + 250, user.getUserPreferences().getLowerPricePoint().toString());
        Assertions.assertEquals("USD " + 5000, user.getUserPreferences().getHighPricePoint().toString());
        Assertions.assertEquals(7, user.getUserPreferences().getTripDuration());
        Assertions.assertEquals(7, user.getUserPreferences().getTicketQuantity());
        Assertions.assertEquals(3, user.getUserPreferences().getNumberOfAdults());
        Assertions.assertEquals(4, user.getUserPreferences().getNumberOfChildren());
    }
}
