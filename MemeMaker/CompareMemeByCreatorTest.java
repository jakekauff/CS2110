import static org.junit.Assert.*;

import org.junit.Test;

public class CompareMemeByCreatorTest {
	
	@Test
	public void compareTest() {
		CompareMemeByCreator c = new CompareMemeByCreator();
		
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName3.jpeg","title3","description3");
		
		User u1 = new User("myUserName1");
		User u2 = new User("myUserName2");
		
		Meme m1 = new Meme(b1,"caption1",u1);
		Meme m2 = new Meme(b2,"caption2",u2);
		Meme m3 = new Meme(b1,"caption1",u1);
		Meme m4 = new Meme(b2,"caption2",u2);
		Meme m5 = new Meme(b2,"caption1",u2);
		Meme m6 = new Meme(b3,"caption1",u1);
		Meme m7 = new Meme(b2,"caption1",u1);
		Meme m8 = new Meme(b2,"caption1",u1);
		
		Rating r1 = new Rating(u1,1);
		Rating r2 = new Rating(u1,1);
		Rating r3 = new Rating(u2,-1);
		Rating r4 = new Rating(u2,0);
		
		m1.addRating(r1); 
		m1.addRating(r2); // overall = 2
		
		m3.addRating(r1);
		m3.addRating(r2); // overall = 2
		
		m2.addRating(r3);
		m2.addRating(r2); // overall = 0
		
		m5.addRating(r3);
		m5.addRating(r2); // overall = 0
		
		m4.addRating(r3);
		m4.addRating(r4); // overall = -1
		
		m6.addRating(r2);
		m6.addRating(r4); // overall = 1
		
		m7.addRating(r2);
		m7.addRating(r4); // overall = 1
		
		m8.addRating(r2);
		m8.addRating(r4); // overall = 1
		
		m1.setShared(true);
		m3.setShared(true);
		m2.setShared(true);
		m5.setShared(true);
		m7.setShared(false);
		m8.setShared(true);
		
		/*
		 * Combinations:
		 * m1 & m2: different Creator, should NOT return 0. stops after first comparison.
		 * m1 & m6: same Creator, different overallRating. should NOT return 0. stops after second comparison.
		 * m2 & m5: same Creator, same overallRating, different caption. should NOT return 0. stops after third comparison.
		 * m6 & m7: same Creator, overallRating, and caption; different backgroundImage. should NOT return 0. stops after fourth comparison.
		 * m7 & m8: identical except for shared. if m7 goes first, should return 1; if m8 goes first, should return -1.
		 * m1 & m3: identical. should return 0.
		 */
		assertNotEquals("The compare() method returned 0 for two memes with a different creator.",0,c.compare(m1,m2));
		assertNotEquals("The compare() method returned 0 for two memes with the same creator but different overallRatings.",0,c.compare(m1, m6));
		assertNotEquals("The compare() method returned 0 for two memes with same creator & overallRating but different captions",0,c.compare(m2, m5));
		assertNotEquals("The compare() method returned 0 for two memes with same creator, overallRating, and caption, but different backgroundImage's",0,c.compare(m6, m7));
		assertEquals("The compare() method did not return 1 for two identical memes but with the first having shared equal to false and the second true",1,c.compare(m7,m8));
		assertEquals("The compare() method did not return -1 for two identical memes but with the first having shared equal to true and the second false",-1,c.compare(m8,m7));
		assertEquals("The compare() method did not return 0 for two identical memes with the same shared status (both true).",0,c.compare(m1, m3));
		m3.setShared(false);
		m1.setShared(false);
		assertEquals("The compare() method did not return 0 for two identical memes with the same shared status (false)",0,c.compare(m1, m3));
	}

}
