package microserviceTourGuide.beans;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class VisitedLocation {

    private UUID userId;
    private Date timeVisited;
    private double latitude;
    private double longitude;
    private double latitudeToRadian;
    private double longitudeToRadian;

    public VisitedLocation(UUID userId, double latitude, double longitude, Date timeVisited) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(timeVisited);

        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeVisited = timeVisited;
        latitudeToRadian = Math.toRadians(latitude);
        longitudeToRadian = Math.toRadians(longitude);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getTimeVisited() {
        return timeVisited;
    }

    public void setTimeVisited(Date timeVisited) {
        this.timeVisited = timeVisited;
    }

    public double getLatitudeToRadian() {
        return latitudeToRadian;
    }

    public void setLatitudeToRadian(double latitudeToRadian) {
        this.latitudeToRadian = latitudeToRadian;
    }

    public double getLongitudeToRadian() {
        return longitudeToRadian;
    }

    public void setLongitudeToRadian(double longitudeToRadian) {
        this.longitudeToRadian = longitudeToRadian;
    }
}
