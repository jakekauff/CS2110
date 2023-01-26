
public class BackgroundImage implements Comparable<BackgroundImage> {

	// Instance variables for BackgroundImage:

	private String imageFileName;
	private String title;
	private String description;

	// Getters and Setters for instance variables:

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// BackgroundImage Methods: toString(), equals()

	@Override
	public String toString() {

		return this.title + " <" + description + ">";
	}

	@Override
	public boolean equals(Object o) {

		if (o == null)
			return false;

		if (o instanceof BackgroundImage) {
			BackgroundImage b2 = (BackgroundImage) o;
			if (this.imageFileName.equals(b2.imageFileName) && this.title.equals(b2.title)
					&& this.description.equals(b2.description)) {
				return true;
			} else
				return false;
		} else {
			return false;
		}
	}

	// Constructor
	public BackgroundImage(String imageFileName, String title, String description) {

		this.imageFileName = imageFileName;
		this.title = title;
		this.description = description;
	}

	// Default Constructor
	public BackgroundImage() {

		this.imageFileName = " ";
		this.title = " ";
		this.description = " ";
	}

	@Override
	public int compareTo(BackgroundImage o) {
		int retVal = this.imageFileName.compareTo(o.imageFileName);
		if (retVal != 0)
			return retVal;

		retVal = this.title.compareTo(o.title);
		if (retVal != 0)
			return retVal;

		retVal = this.description.compareTo(o.description);
		if (retVal != 0)
			return retVal;

		return 0;
	}
}