import java.util.Comparator;

public class CompareMemeByCreator implements Comparator<Meme> {

	public int compare(Meme m1, Meme m2) {
		int retVal = m1.getCreator().compareTo(m2.getCreator());
		if (retVal != 0)
			return retVal;

		retVal = (int) (m2.calculateOverallRating() - (m1.calculateOverallRating()));
		if (retVal != 0)
			return retVal;

		retVal = m1.getCaption().compareTo(m2.getCaption());
		if (retVal != 0)
			return retVal;

		retVal = m1.getBackgroundImage().compareTo(m2.getBackgroundImage());
		if (retVal != 0)
			return retVal;

		if (m1.getShared() && !m2.getShared()) {
			return -1;
		}
		if (!m1.getShared() && m2.getShared()) {
			return 1;
		}
		return 0;
	}

}
