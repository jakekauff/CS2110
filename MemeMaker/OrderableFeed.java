import java.util.Collections;

public class OrderableFeed extends Feed {

	public void sortByCaption() {
		Collections.sort(this.getMemes());
	}

	public void sortByRating() {
		Collections.sort(getMemes(), new CompareMemeByRating());
	}

	public void sortByCreator() {
		Collections.sort(getMemes(), new CompareMemeByCreator());
	}
}
