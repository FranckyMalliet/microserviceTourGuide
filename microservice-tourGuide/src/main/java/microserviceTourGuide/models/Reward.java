package microserviceTourGuide.models;


import microserviceTourGuide.beans.Attraction;
import microserviceTourGuide.beans.VisitedLocation;

public class Reward {

	private VisitedLocation visitedLocation;
	private Attraction attraction;
	private int rewardPoints;

	public Reward(VisitedLocation visitedLocation, Attraction attraction, int rewardPoints) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
		this.rewardPoints = rewardPoints;
	}

	public VisitedLocation getVisitedLocation() {
		return visitedLocation;
	}

	public Attraction getAttraction() {
		return attraction;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}
