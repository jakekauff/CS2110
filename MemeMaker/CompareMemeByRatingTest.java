import static org.junit.Assert.*;

import org.junit.Test;

public class CompareMemeByRatingTest {

	@Test
	public void compareTest() {
		CompareMemeByRating c = new CompareMemeByRating();
		
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		
		User u1 = new User("myUserName1");
		User u2 = new User("jake");
		
		Meme m1 = new Meme(b1,"caption1",u1);
		Meme m3 = new Meme(b1,"caption1",u1);
		Meme m4 = new Meme(b1,"caption1",u1);
		Meme m5 = new Meme(b1,"caption2",u1);
		Meme m6 = new Meme(b2,"caption1",u1);
		Meme m7 = new Meme(b1,"caption1",u2);
		
		Rating r1 = new Rating(u1,1);
		Rating r2 = new Rating(u1,1);
		Rating r3 = new Rating(u2,-1);
		Rating r4 = new Rating(u2,0);
		
		m1.addRating(r1); 
		m1.addRating(r2); // overall = 2
		
		m3.addRating(r1);
		m3.addRating(r2); // overall = 2
		
		m5.addRating(r1);
		m5.addRating(r2); // overall = 2
		
		m4.addRating(r3);
		m4.addRating(r4); // overall = -1
		
		m6.addRating(r1);
		m6.addRating(r2); // overall = 2
		
		m7.addRating(r1);
		m7.addRating(r2); // overall = 2
		
		
		/*
		 * Combinations:
		 * m1 & m3: same everything. should return 0.
		 * m1 & m4: different overallRating; should NOT return 0.
		 * m1 & m5: same until caption. should NOT return 0.
		 * m1 & m6: same until backgroundImage. should NOT return 0.
		 * m1 & m7: same until creator. should NOT return 0.
		 */
		assertNotEquals("The compare() method returned 0 for two memes with a different overallRating.",0,c.compare(m1,m4));
		assertNotEquals("The compare() method returned 0 for two memes that are the same until caption",0,c.compare(m1, m5));
		assertNotEquals("The compare() method returned 0 for two memes that are the same until backgroundImage",0,c.compare(m1, m6));
		assertNotEquals("The compare() method returned 0 for two memes that are the same until creator",0,c.compare(m1, m7));
		assertEquals("The compare() method did not return 0 for two identical memes.",0,c.compare(m1, m3));
	}

}
