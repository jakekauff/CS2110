import java.util.ArrayList;
import java.util.Arrays;

public class Meme implements Comparable<Meme> {

	// Instance variables for Meme:

	private User creator;
	private BackgroundImage backgroundImage;
	private Rating[] ratings;
	private String caption;
	private String captionVerticalAlign;
	private boolean shared;
	private int count = 0;

	// Getters and Setters for instance variables:

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public BackgroundImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(BackgroundImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public Rating[] getRatings() {
		return ratings;
	}

	public void setRatings(Rating[] ratings) {
		this.ratings = ratings;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getCaptionVerticalAlign() {
		return captionVerticalAlign;
	}

	public boolean setCaptionVerticalAlign(String captionVerticalAlign) {
		if (captionVerticalAlign == "top") {
			this.captionVerticalAlign = captionVerticalAlign;
			return true;
		} else if (captionVerticalAlign == "middle") {
			this.captionVerticalAlign = captionVerticalAlign;
			return true;
		} else if (captionVerticalAlign == "bottom") {
			this.captionVerticalAlign = captionVerticalAlign;
			return true;
		} else {
			return false;
		}
	}

	public boolean getShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}
	// Meme Methods: addRating(), calculateOverallRating(), toString(), equals()

	public boolean addRating(Rating r) {
		for (int i = 1; i < 9; i++) {
			ratings[i - 1] = ratings[i];
		}
		ratings[9] = r;
		
		return true;
	}

	public double calculateOverallRating() {
		double overallRating = 0.0;
		for (int i = 0; i <= 9; i++) {
			if (ratings[i] != null) {
				overallRating += ratings[i].getScore();
			}else {
				overallRating += 0.0;
			}
		}
		return overallRating;
	}

	private double numberOfPositiveOneScores() {
		double positiveOne = 0.0;
		for (int i = 0; i < ratings.length; i++) {
			if (ratings[i] != null) {
				if (ratings[i].getScore() == 1.0) {
					positiveOne += 1.0;
				}
			}
		}
		return positiveOne;
	}

	private double numberOfNegativeOneScores() {
		double negativeOne = 0.0;
		for (int i = 0; i < ratings.length; i++) {
			if (ratings[i] != null) {
				if (ratings[i].getScore() == -1.0) {
					negativeOne += 1.0;
				}
			}
		}
		return negativeOne;
	}

	@Override
	public String toString() {
		return this.backgroundImage.getTitle() + " <" + this.backgroundImage.getDescription() + "> " + "'"
				+ this.caption + "' " + this.calculateOverallRating() + " [+1: "
				+ (int) this.numberOfPositiveOneScores() + ", -1: " + (int) this.numberOfNegativeOneScores() + "]"
				+ " - created by " + this.creator.getUserName();
	}

	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		}

		if (this == otherObject) {
			return true;
		}

		if (otherObject instanceof Meme) {
			Meme otherMeme = (Meme) otherObject;

			if (this.getBackgroundImage().equals(otherMeme.getBackgroundImage())
					&& this.getCaption().equals(otherMeme.getCaption())
					&& this.getCreator().equals(otherMeme.getCreator())) {

				return true;
			}
		}
		return false;
	}

	// Constructor
	public Meme(BackgroundImage bi, String cap, User u) {
		setBackgroundImage(bi);
		setCaption(cap);
		setCreator(u);
		ratings = new Rating[10];
		captionVerticalAlign = "bottom";
		shared = false;
	}

	// Default Constructor
	public Meme() {
		// empty, should have instance variables as parameters
		backgroundImage = new BackgroundImage();
		creator = new User();
		caption = " ";
		captionVerticalAlign = "bottom";
		shared = false;
		ratings = new Rating[10];
	}

	@Override
	public int compareTo(Meme o) {
		int retVal = this.caption.compareTo(o.caption);
		if (retVal != 0)
			return retVal;

		retVal = this.backgroundImage.compareTo(o.backgroundImage);
		if (retVal != 0)
			return retVal;

		retVal = (int) (o.calculateOverallRating() - (this.calculateOverallRating()));
		if (retVal != 0)
			return retVal;

		if (this.getShared() && !o.getShared()) {
			return -1;
		}
		if (!this.getShared() && o.getShared()) {
			return 1;
		}
		return 0;
	}
}