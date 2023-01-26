import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.Test;

public class UserTest {
	
	@Test
	public void overloadedConstructorTest() {
		User u = new User("myUserName");
		assertEquals("The userName provided did not match the one provided to the constructor", "myUserName",u.getUserName());
		assertNotNull("memesViewed was not instantiated.", u.getMemesViewed());
		assertNotNull("memesCreated was not instantiated.", u.getMemesCreated());
	}
	
	@Test
	public void rateMemeTest() { 
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		User u1 = new User("myUserName1");
		Meme m1 = new Meme(b1,"caption1",u1);
		Rating r1 = new Rating(u1,1);
		Rating r2 = new Rating(new User("jake"),1);
		Rating r3 = new Rating(new User("joe"),-1);
		
		m1.setRatings(new Rating[] {r1,r2,r3,r2,r3,r2,r1,r3,r2,r1});
		
		User u2 = new User("Bayan");
		Rating r4 = new Rating(u2,1);
		
		u2.rateMeme(m1, 1);
		
		assertEquals("The rateMeme() method did not add the rating by the given user to the end of the array",r4,m1.getRatings()[9]);
	}

	@Test
	public void createMemeTest() {
		User u = new User("myUserName");
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		u.createMeme(b1, "myCaption");
		
		Meme m = new Meme(b1,"myCaption",u);
		
		assertEquals("The meme was not created and added to memesCreated",true,u.getMemesCreated().contains(m));
	}
	
	@Test
	public void deleteMemeTest() {
		User u = new User("myUserName");
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		Feed f = new Feed();
		
		Meme m1 = new Meme(b1,"myCaption1",u);
		Meme m2 = new Meme(b1,"myCaption2",u);
		Meme m3 = new Meme(b1,"myNewCaption",u);
		
		u.shareMeme(m1, f);
		
		u.createMeme(b1, "myCaption1");
		u.shareMeme(u.getMemesCreated().get(0), f);
		u.createMeme(b1, "myCaption2");
		
		u.deleteMeme(m1);
		u.deleteMeme(m2);
		assertEquals("The deleteMeme() method deleted the meme even though it was shared.",true,u.getMemesCreated().contains(m1));
		assertEquals("The deleteMeme() method did not delete the given meme",false,u.getMemesCreated().contains(m2));
		assertEquals("The deleteMeme() method did not return false even though the meme is not in memesCreated.",false,u.deleteMeme(m3));
	}
	
	@Test
	public void shareMemeTest() {
		User u = new User("myUserName");
		Feed f = new Feed();
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		Meme m1 = new Meme(b1,"myCaption1",u);
		
		u.shareMeme(m1,f);
		
		assertEquals("The shareMeme() method failed to set the given meme's shared to true",true,m1.getShared());
		assertEquals("The shareMeme() method failed to add the given meme to the given feed's memes ArrayList",true,f.getMemes().contains(m1));
	}
	
	@Test
	public void rateNextMemeFromFeedTest() {
		Feed f = new Feed();
		
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u1 = new User("myUserName1");
		User u2 = new User("myUserName2");
		
		Meme m1 = new Meme(b1,"caption1",u2);
		
		ArrayList<Meme> feedMemes = new ArrayList<Meme>();
		feedMemes.add(m1);
		
		f.setMemes(feedMemes);
		
		u1.rateNextMemeFromFeed(f, 1);
		
		assertEquals("The rateNextMemeFromFeed() method did not assign a rating to the next meme in the feed.",0.0,f.getMemes().get(0).calculateOverallRating(),1.0);
		
		Feed f2 = new Feed();
		f2.getMemes().add(null);
		boolean actual = u2.rateNextMemeFromFeed(f2, 0);
		u2.rateNextMemeFromFeed(f2, 0);
		boolean expected = false;
		
		assertEquals("The rateNextMemeFromFeed() allowed the rating of a null Object.",expected,actual);
	}
	
	@Test
	public void calculateReputationTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u1 = new User("myUserName1");
		User u2 = new User("myUserName2");
		
		// empty memesCreated
		assertEquals("The calculateReputation() method did not return 0.0 for a user with empty memesCreated",0.0,u1.calculateReputation(),0.0);
		Meme m1 = new Meme(b1,"caption1",u1);
		Meme m2 = new Meme(b2,"caption2",u1);
		Meme m3 = new Meme(b3,"caption3",u1);
		
		ArrayList<Meme> myMemesCreated = new ArrayList<Meme>();
		myMemesCreated.add(m1);
		myMemesCreated.add(m2);
		myMemesCreated.add(m3);
		
		u1.setMemesCreated(myMemesCreated);
		
		// has no ratings
		assertEquals("The calculateReputation did not return 0.0 for a user whose memes have no ratings",0.0,u1.calculateReputation(),0.0);
		
		Rating r1 = new Rating(u2,1);
		m1.addRating(r1); 
		m2.addRating(r1);
		m3.addRating(r1); // should have a sum of 3. 3/3 = 1.0
		
		assertEquals("The calculateReputation() method failed to accurately add overallRatings and divide by the number of memes and return this as a double.",1.0,u1.calculateReputation(),0.0);
		
	}
	
	@Test
	public void toStringTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u1 = new User("myUserName1");
		
		Meme m1 = new Meme(b1,"caption1",u1);
		Meme m2 = new Meme(b2,"caption2",u1);
		Meme m3 = new Meme(b3,"caption3",u1);
		
		TreeSet<Meme> myMemesViewed = new TreeSet<Meme>();
		myMemesViewed.add(m1);
		myMemesViewed.add(m2);
		myMemesViewed.add(m3);
		
		ArrayList<Meme> myMemesCreated = new ArrayList<Meme>();
		
		u1.setMemesCreated(myMemesCreated);
		u1.setMemesViewed(myMemesViewed);
		
		String expected = "myUserName1 has rated (3) memes, (0.0)";
		assertEquals("The toString() method did not return the expected String for the given User",expected,u1.toString());
	}
	
	@Test
	public void equalsTest() {
		User u1 = new User("Jake");
		User u2 = new User("Jake");
		User u3 = null;
		Feed f = new Feed();
		
		assertEquals("The equals() method did not return true for two identical users", true, u1.equals(u2));
		assertEquals("The equals() method did not return false for comparison with a null Object.",false,u1.equals(u3));
		assertEquals("The equals() method did not return false for comparison with an Object of another type.",false,u1.equals(f));
	}
	
	@Test
	public void compareToTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u1 = new User("Jake");
		User u2 = new User("Jake");
		User u3 = new User("Jake");
		User u4 = new User("Bayan");
		
		Meme m1 = new Meme(b1,"caption1",u1);
		Meme m2 = new Meme(b2,"caption2",u1);
		Meme m3 = new Meme(b3,"caption3",u1);
		
		ArrayList<Meme> memesCreated1 = new ArrayList<Meme>();
		ArrayList<Meme> memesCreated2 = new ArrayList<Meme>();
		ArrayList<Meme> memesCreated3 = new ArrayList<Meme>();
		
		memesCreated1.add(m3);
		memesCreated1.add(m2);
		u1.setMemesCreated(memesCreated1);
		
		memesCreated2.add(m3);
		memesCreated2.add(m1);
		u2.setMemesCreated(memesCreated2);
		
		memesCreated3.add(m1);
		memesCreated3.add(m3);
		memesCreated3.add(m2);
		u3.setMemesCreated(memesCreated3);
		
		
		
		/*
		 * Combinations:
		 * u1 & u2 : exactly the same -- return 0
		 * u1 & u3 : same userName, different size of memesCreated -- should NOT return 0
		 * u1 & u4 : different userName -- should NOT return 0
		 */
		
		assertEquals("The compareTo() method did not return 0 for two User's with the same userName and same amount of memesCreated.",0,u1.compareTo(u2));
		assertNotEquals("The compareTo() method returned 0 even though the two User's have a different amount of memesCreated",0,u1.compareTo(u3));
		assertNotEquals("The compareTo() method returned 0 even though the two User's have different userNames.",0,u1.compareTo(u4));
	}
	
	@Test
	public void setAndGetUserNameTest() {
		User u = new User("Jake");
		
		assertEquals("The getUserName() method did not return the correct String userName", "Jake",u.getUserName());
		
		u.setUserName("Bayan");
		
		assertEquals("The setUserName() method failed to set userName to the given String.","Bayan",u.getUserName());
	}
	
	@Test
	public void setAndGetMemesCreatedTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u1 = new User("Jake");
		
		Meme m1 = new Meme(b1,"caption1",u1);
		Meme m2 = new Meme(b2,"caption2",u1);
		Meme m3 = new Meme(b3,"caption3",u1);
		
		ArrayList<Meme> memesCreated1 = new ArrayList<Meme>();
		memesCreated1.add(m1);
		memesCreated1.add(m2);
		
		u1.setMemesCreated(memesCreated1);
		assertEquals("The getMemesCreated() method failed to return the appropriate ArrayList",memesCreated1,u1.getMemesCreated());
		
		memesCreated1.add(m3);
		u1.setMemesCreated(memesCreated1);
		assertEquals("The setMemesCreated() method failed to set memesCreated to the given ArrayList",memesCreated1,u1.getMemesCreated());
	}
	
	@Test
	public void setAndGetMemesViewedTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u1 = new User("Jake");
		
		Meme m1 = new Meme(b1,"caption1",u1);
		Meme m2 = new Meme(b2,"caption2",u1);
		Meme m3 = new Meme(b3,"caption3",u1);
		
		TreeSet<Meme> memesViewed1 = new TreeSet<Meme>();
		memesViewed1.add(m1);
		memesViewed1.add(m2);
		
		u1.setMemesViewed(memesViewed1);
		ArrayList<Meme> memesViewedFromGet = new ArrayList<Meme>();
		memesViewedFromGet.add(m1);
		memesViewedFromGet.add(m2);
		assertEquals("The getMemesViewed() method failed to return the appropriate ArrayList",memesViewedFromGet,u1.getMemesViewed());
		
		memesViewed1.add(m3);
		memesViewedFromGet.add(m3);
		u1.setMemesViewed(memesViewed1);
		assertEquals("The setMemesViewed() method failed to set memesViewed to the given ArrayList",memesViewedFromGet,u1.getMemesViewed());
	}
	
}
