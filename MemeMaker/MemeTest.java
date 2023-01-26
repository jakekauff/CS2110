import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MemeTest {

	@Test
	public void defaultConstructorTest() {
		Meme m = new Meme();
		assertNotNull("backgroundImage was not instantiated",m.getBackgroundImage());
		assertNotNull("creator was not instantiated.",m.getCreator());
		assertNotNull("caption was not instantiated",m.getCaption());
		assertEquals("captionVerticalAlign was not instantiated too 'bottom'","bottom",m.getCaptionVerticalAlign());
		assertNotNull("ratings was not instantiated",m.getRatings());
		assertNotNull("captionVerticalAlign was not instantiated",m.getCaptionVerticalAlign());
		assertNotNull("shared was not instantiated",m.getShared());
	}
	@Test
	public void overloadedConstructorTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		User u1 = new User("myUserName1");
		Meme m1 = new Meme(b1,"caption1",u1);
		
		
		assertEquals("The backgroundImage provided did not match the one provided to the constructor",b1,m1.getBackgroundImage());
		assertEquals("The caption provided did not match the one provided to the constructor","caption1",m1.getCaption());
		assertEquals("The creator provided did not match the one provided to the constructor",u1,m1.getCreator());
		assertNotNull("ratings was not instantiated",m1.getRatings());
		assertNotNull("captionVerticalAlign was not instantiated",m1.getCaptionVerticalAlign());
		assertNotNull("shared was not instantiated",m1.getShared());
	}

	@Test
	public void addRatingTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		User u1 = new User("myUserName1");
		Meme m1 = new Meme(b1,"caption1",u1);
		Rating r1 = new Rating(u1,1);
		Rating r2 = new Rating(new User("jake"),1);
		Rating r3 = new Rating(new User("joe"),-1);
		
		m1.setRatings(new Rating[] {r1,r2,r3,r2,r3,r2,r1,r3,r2,r1});
		
		boolean actual = m1.addRating(r2);
		
		m1.addRating(r2);
		
		boolean expected = true;
		
		assertEquals("The addRating() method did not add the rating passed to the method.",expected,actual);
	}
	
	@Test
	public void calculateOverallRatingTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		User u1 = new User("myUserName1");
		Meme m1 = new Meme(b1,"caption1",u1);
		Rating r1 = new Rating(u1,1);
		Rating r2 = new Rating(u1,1);
		Rating r3 = new Rating(u1,-1);
		
		Rating[] newRatings = new Rating[] {r1,r2,r3,r2,r3,r2,r1,r3,r2,r1}; // sum = 4.0
		
		m1.setRatings(newRatings);
		
		double expected = 4.0;
		
		assertEquals("The calculateOverallRating() method did not return the correct overallRating for the given meme and its ratings.",expected,m1.calculateOverallRating(),0.1);
	}
	
	@Test
	public void equalsTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		
		User u1 = new User("myUserName1");
		User u2 = new User("myUserName2");
		
		Meme m1 = new Meme(b1,"caption1",u1);
		Meme m2 = new Meme(b2,"caption2",u2);
		Meme m3 = new Meme(b1,"caption1",u1);
		Meme m4 = new Meme(b2,"caption1",u1);
		Meme m5 = new Meme(b1,"caption2",u1);
		Meme m6 = new Meme(b1,"caption1",u2);
		
		assertEquals("The equals() method did not return false for two completely different Meme's",false,m1.equals(m2));
		assertEquals("The equals() method did not return true for two identical Meme's",true,m1.equals(m3));
		assertEquals("The equals() method did not return false for a meme compared to a null Object",false,m2.equals(null));
		assertEquals("The equals() method did not return false for a meme compared to an Object of another type.",false,m1.equals(u1));
		assertEquals("The equals() method did not return false for memes with different backgroundImage's",false,m3.equals(m4));
		assertEquals("The equals() method did not return false for memes with different caption's",false,m3.equals(m5));
		assertEquals("The equals() method did not return false for memes with different creator's",false,m3.equals(m6));
	}
	
	@Test
	public void compareToTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName3.jpeg","title3","description3");
		
		User u1 = new User("myUserName1");
		User u2 = new User("myUserName1");
		
		Meme m1 = new Meme(b1,"caption1",u1);
		Meme m2 = new Meme(b2,"caption2",u2);
		Meme m3 = new Meme(b1,"caption1",u1);
		Meme m4 = new Meme(b2,"caption2",u2);
		Meme m5 = new Meme(b2,"caption2",u2);
		Meme m6 = new Meme(b3,"caption1",u1);
		
		Rating r1 = new Rating(u1,1);
		Rating r2 = new Rating(u1,1);
		Rating r3 = new Rating(u2,-1);
		Rating r4 = new Rating(u2,0);
		
		m1.addRating(r1);
		m1.addRating(r2);
		
		m3.addRating(r1);
		m3.addRating(r2);
		
		m2.addRating(r3);
		m2.addRating(r2);
		
		m5.addRating(r3);
		m5.addRating(r2);
		
		m4.addRating(r3);
		m4.addRating(r4);
		
		m1.setShared(true);
		m3.setShared(true);
		m2.setShared(false);
		m5.setShared(true);
		
		/* Combinations:
		 * m1 & m3 have the same everything. Should return 0.
		 * m1 & m2 have different captions. Should NOT return 0.
		 * m2 & m4 have different overallRatings. Should NOT return 0.
		 * m2 & m5: m2.getShared() = false but m5.getShared = true. Should return 1.
		 * m5 & m2: backwards from line above, should return -1.
		 * m3 & m6: have different backgroundImages, should NOT return 0*/
		
		assertEquals("The compareTo() method did not return 0, when two provided memes are identical.",0,m1.compareTo(m3));
		assertNotEquals("The compareTo() method returned 0 when the two provided memes have different everything",0,m1.compareTo(m2));
		assertNotEquals("The compareTo() method returned 0 when the two provided memes have different values for getOverallRating().",0,m2.compareTo(m4));
		assertEquals("The compareTo() method did not return 1 even though this.getShared is false and o.getShared is true.",1,m2.compareTo(m5));
		assertEquals("The compareTo() method did not return -1 even though this.getShared is true and o.getShared is false.",-1,m5.compareTo(m2));
		assertNotEquals("The compareTo() method returned 0 even though the two memes have different backgroundImages",0,m3.compareTo(m6));
	}
	
	@Test
	public void setAndGetCreatorTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		User u1 = new User("myUserName1");
		User u2 = new User("myOtherUserName");
		Meme m = new Meme(b1, "myCaption",u1);
		
		User getExpected = new User("myUserName1");
		User getActual = m.getCreator();
		
		assertEquals("The getCreator() method did not return the creator provided to the constructor",getExpected,getActual);
		
		m.setCreator(u2);
		
		User setExpected = new User("myOtherUserName");
		User setActual = m.getCreator();
		
		assertEquals("The setCreator() method did not set the creator to the provided User.",setExpected,setActual);
	}
	
	@Test
	public void setAndGetBackgroundImageTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		User u1 = new User("myUserName1");
		Meme m = new Meme(b1, "myCaption",u1);
		
		BackgroundImage getExpected = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage getActual = m.getBackgroundImage();
		
		assertEquals("The getBackgroundImage() method did not return the BackgroundImage provided to the constructor",getExpected,getActual);
		
		m.setBackgroundImage(b2);
		
		BackgroundImage setExpected = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage setActual = m.getBackgroundImage();
		
		assertEquals("The setBackgroundImage() method did not set the backgroundImage to the provided BackgroundImage.",setExpected,setActual);
	}
	
	@Test
	public void setAndGetRatingsTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		User u1 = new User("myUserName1");
		Meme m = new Meme(b1, "myCaption",u1);
		Rating r1 = new Rating(u1,1);
		Rating r2 = new Rating(new User("jake"),1);
		Rating r3 = new Rating(new User("joe"),-1);
		
		Rating[] getExpected = new Rating[10];
		Rating[] getActual = m.getRatings();
		
		assertArrayEquals("The getRatings() method did not return the ratings provided to the constructor",getExpected,getActual);
		
		Rating[] newRatings = new Rating[] {r1,r2,r3,r2,r3,r2,r1,r3,r2,r1};
		
		m.setRatings(newRatings);
		
		Rating[] setExpected = new Rating[] {r1,r2,r3,r2,r3,r2,r1,r3,r2,r1};
		Rating[] setActual = m.getRatings();
		
		assertArrayEquals("The setRatings() method did not set ratings to the provided Rating[].",setExpected,setActual);
	}
	
	@Test
	public void setAndGetCaptionTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		User u1 = new User("myUserName1");
		Meme m = new Meme(b1, "myCaption",u1);
		
		String getExpected = "myCaption";
		String getActual = m.getCaption();
		
		assertEquals("The getCaption() method did not return the caption provided to the constructor",getExpected,getActual);
		
		m.setCaption("newCaption");
		
		String setExpected = "newCaption";
		String setActual = m.getCaption();
		
		assertEquals("The setCaption() method did not set the caption to the provided String.",setExpected,setActual);
	}
	
	@Test
	public void setAndGetCaptionVerticalAlignTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		User u1 = new User("myUserName1");
		Meme m = new Meme(b1, "myCaption",u1);
		
		String getExpected = "bottom";
		String getActual = m.getCaptionVerticalAlign();
		
		assertEquals("The getCaptionVerticalAlign() method did not return the correct captionVerticalAlign",getExpected,getActual);
		
		m.setCaptionVerticalAlign("top");
		
		boolean setExpected = true;
		boolean setActual = m.setCaptionVerticalAlign("top");
		
		assertEquals("The setCaptionVerticalAlign() method did not set the captionVerticalAlign to the provided String.",setExpected,setActual);
		
		m.setCaptionVerticalAlign("middle");
		
		setExpected = true;
		setActual = m.setCaptionVerticalAlign("middle");
		
		assertEquals("The setCaptionVerticalAlign() method did not set the captionVerticalAlign to the provided String.",setExpected,setActual);
		
		m.setCaptionVerticalAlign("bottom");
		
		setExpected = true;
		setActual = m.setCaptionVerticalAlign("bottom");
		
		assertEquals("The setCaptionVerticalAlign() method did not set the captionVerticalAlign to the provided String.",setExpected,setActual);
		
		m.setCaptionVerticalAlign("random");
		
		setExpected = false;
		setActual = m.setCaptionVerticalAlign("random");
		
		assertEquals("The setCaptionVerticalAlign() method set the captionVerticalAlign to an unacceptable String.",setExpected,setActual);
	}
	
	@Test
	public void setAndGetSharedTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		User u1 = new User("myUserName1");
		Meme m = new Meme(b1, "myCaption",u1);
		
		boolean getExpected = false;
		boolean getActual = m.getShared();
		
		assertEquals("The getShared() method did not return the correct boolean",getExpected,getActual);
		
		m.setShared(true);
		
		boolean setExpected = true;
		boolean setActual = m.getShared();
		
		assertEquals("The setCaptionVerticalAlign() method did not set the captionVerticalAlign to the provided String.",setExpected,setActual);
	}
	
	
}
