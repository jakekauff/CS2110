import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class OrderableFeedTest {

	
	@Test
	public void sortByCaptionTest() {
		OrderableFeed f = new OrderableFeed();
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u = new User("myUserName");
		
		Meme m1 = new Meme(b1,"def",u);
		Meme m2 = new Meme(b2,"abc",u);
		Meme m3 = new Meme(b3,"123",u);
		
		ArrayList<Meme> myMemes = new ArrayList<Meme>();
		myMemes.add(m1);
		myMemes.add(m2);
		myMemes.add(m3);
		f.setMemes(myMemes);
		
		f.sortByCaption();
		Collections.sort(myMemes);

		
		assertEquals("",myMemes,f.getMemes());
	}
	
	@Test
	public void sortByRatingTest() {
		OrderableFeed f = new OrderableFeed();
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u = new User("myUserName");
		
		Meme m1 = new Meme(b1,"def",u);
		Meme m2 = new Meme(b2,"abc",u);
		Meme m3 = new Meme(b3,"123",u);
		
		Rating r1 = new Rating(u,0);
		Rating r2 = new Rating(u,-1);
		Rating r3 = new Rating(u,1);
		
		m1.addRating(r1);
		m2.addRating(r2);
		m3.addRating(r3);
		
		ArrayList<Meme> myMemes = new ArrayList<Meme>();
		myMemes.add(m1);
		myMemes.add(m2);
		myMemes.add(m3);
		f.setMemes(myMemes);
		
		f.sortByRating();
		Collections.sort(myMemes, new CompareMemeByRating());

		
		assertEquals("",myMemes,f.getMemes());
	}
	
	@Test
	public void sortByCreatorTest() {
		OrderableFeed f = new OrderableFeed();
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u = new User("myUserName");
		User u2 = new User("zzzz");
		User u3 = new User("aaaa");
		
		Meme m1 = new Meme(b1,"def",u2);
		Meme m2 = new Meme(b2,"abc",u);
		Meme m3 = new Meme(b3,"123",u3);
		
		ArrayList<Meme> myMemes = new ArrayList<Meme>();
		myMemes.add(m1);
		myMemes.add(m2);
		myMemes.add(m3);
		f.setMemes(myMemes);
		
		f.sortByCreator();
		Collections.sort(myMemes, new CompareMemeByCreator());

		
		assertEquals("",myMemes,f.getMemes());
	}
	
}
