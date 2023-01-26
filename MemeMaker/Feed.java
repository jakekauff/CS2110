import java.util.ArrayList;

public class Feed {

	// Instance variables for Feed:

	private ArrayList<Meme> memes;

	// Getters and Setters for instance variables:

	public ArrayList<Meme> getMemes() {
		return memes;
	}

	public void setMemes(ArrayList<Meme> memes) {
		this.memes = memes;
	}

	// Feed Methods: getNewMeme(), toString()

	public Meme getNewMeme(User u) {
		Meme result = null;
		
		if (this.getMemes() == null) {
			result = null;
		}
		else {
			for (int i = 0; i < this.getMemes().size(); i++) {
				if ((u.getMemesViewed().contains(this.getMemes().get(i))==false)
					&&
					(u.getMemesCreated().contains(this.getMemes().get(i)))==false) {
					result = this.getMemes().get(i);
					return result;
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		String returnString = "";
		for (Meme m : memes) {
			returnString += (m.toString() + " \n ");
		}
		return returnString;
	}

	// Default Constructor
	public Feed() {
		// empty, should have instance variables as parameters
		memes = new ArrayList<Meme>();
	}

}