package microserviceTourGuide.models;

import microserviceTourGuide.beans.Trip;
import microserviceTourGuide.beans.VisitedLocation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;
    private String phoneNumber;
    private String emailAddress;
    private Date latestLocationTimestamp;
    private UserPreferences userPreferences = new UserPreferences();
    private List<VisitedLocation> visitedLocations = new ArrayList();
    private List<Reward> userRewards = new ArrayList();
    private List<Trip> tripDeals = new ArrayList();

    public User(UUID userId, String userName, String phoneNumber, String emailAddress) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getLatestLocationTimestamp() {
        return latestLocationTimestamp;
    }

    public void setLatestLocationTimestamp(Date latestLocationTimestamp) {
        this.latestLocationTimestamp = latestLocationTimestamp;
    }

    public UserPreferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    public List<VisitedLocation> getVisitedLocation() {
        return this.visitedLocations;
    }

    public void setVisitedLocation(List<VisitedLocation> visitedLocations) {
        this.visitedLocations = visitedLocations;
    }

    public void addUserReward(Reward userReward) {
            this.userRewards.add(userReward);
    }

    public List<Reward> getUserRewards() {
        return this.userRewards;
    }

    public void setUserRewards(List<Reward> userRewards) {
        this.userRewards = userRewards;
    }

    public void addToVisitedLocations(VisitedLocation visitedLocation) {
        this.visitedLocations.add(visitedLocation);
    }

    public void clearVisitedLocations() {
        this.visitedLocations.clear();
    }

    public VisitedLocation getLastVisitedLocation() {
        return this.visitedLocations.get(this.visitedLocations.size() - 1);
    }

    public void setTripDeals(List<Trip> tripDeals) {
        this.tripDeals = tripDeals;
    }

    public List<Trip> getTripDeals() {
        return this.tripDeals;
    }
}
