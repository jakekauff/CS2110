import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;

public class User implements Comparable<User> {

	// Instance variables for User:

	private String userName;
	private ArrayList<Meme> memesCreated;
	private TreeSet<Meme> memesViewed;

	// Getters and Setters for instance variables:

	public String getUserName() {
		return userName;
	}

	public void setUserName(String u) {
		userName = u;
	}

	public ArrayList<Meme> getMemesCreated() {
		return memesCreated;
	}

	public void setMemesCreated(ArrayList<Meme> memesCreated) {
		this.memesCreated = memesCreated;
	}

	public ArrayList<Meme> getMemesViewed() {
		ArrayList<Meme> memesViewedArrayList = new ArrayList<Meme>();
		memesViewedArrayList.addAll(memesViewed);
		return memesViewedArrayList;
	}

	public void setMemesViewed(TreeSet<Meme> memesViewed) {
		this.memesViewed = memesViewed;
	}

	// User Methods: rateMeme(), createMeme(), deleteMeme(), shareMeme(),
	// rateNextMemeFromFeed(),
	// calculateReputation(), toString(), equals()

	public void rateMeme(Meme m, int rating) {
		memesViewed.add(m);

		Rating r = new Rating(this, rating);

		m.addRating(r);
	}

	public Meme createMeme(BackgroundImage bi, String caption) {

		Meme m = new Meme(bi, caption, this);
		memesCreated.add(m);
		return m;
	}

	public boolean deleteMeme(Meme m) {
		boolean result;
		if (memesCreated.contains(m) && m.getShared() == false) {
			memesCreated.remove(m);
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public void shareMeme(Meme m, Feed f) {
		m.setShared(true);
		f.getMemes().add(m);
	}

	public boolean rateNextMemeFromFeed(Feed f, int RatingScore) {
		Meme m = f.getNewMeme(this);
		if (m != null) {
			rateMeme(m, RatingScore);
			return true;
		}
		return false;

	}

	public double calculateReputation() {
		List<Double> memeRatings = new ArrayList<>();
		for (Meme m : this.memesCreated) {
			memeRatings.add(m.calculateOverallRating());
		}
		if (memesCreated.isEmpty()) {
			return 0.0;
		}

		//if (memeRatings.isEmpty()) {
		//	return 0.0;
		//}

		double sumRatings = 0.0;
		for (double d : memeRatings) {
			sumRatings += d;
		}
		return (double) sumRatings / (double) memesCreated.size();
	}

	@Override
	public String toString() {
		String statement1 = " has rated (";
		String statement2 = ") memes, (";
		String statement3 = ")";
		return this.getUserName() + statement1 + this.memesViewed.size() + statement2 + this.calculateReputation()
				+ statement3;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		}

		if (this == otherObject) {
			return true;
		}

		if (otherObject instanceof User) {
			User otherUser = (User) otherObject;

			if (this.getUserName() == otherUser.getUserName()) {
				return true;
			}
		}
		return false;
	}

	// Overloaded Constructor:
	public User(String u) {
		setUserName(u);
		memesCreated = new ArrayList<Meme>();
		memesViewed = new TreeSet<Meme>();
	}

	// Default Constructor:
	public User() {
		// empty, should have instance variables as parameters
		userName = "";
		memesCreated = new ArrayList<Meme>();
		memesViewed = new TreeSet<Meme>();
	}

	public int compareTo(User o) {
		
		int retVal = this.userName.compareTo(o.userName);
		if (retVal != 0)
			return retVal;

		retVal = (int) (o.memesCreated.size() - (this.memesCreated.size()));
		if (retVal != 0)
			return retVal;

		return 0;
	}
}