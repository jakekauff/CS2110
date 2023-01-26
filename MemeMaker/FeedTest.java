import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.Test;

public class FeedTest {
	
	@Test
	public void defaultConstructorTest() {
		Feed f = new Feed();
		ArrayList<Meme> myMemes = new ArrayList<Meme>();
		assertEquals("The default constructer did not instantiate the memes instance variable.",myMemes,f.getMemes());
	}
	
	@Test
	public void getNewMemeTest() {
		Feed f = new Feed();
		
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u1 = new User("myUserName1");
		User u2 = new User("myUserName1");
		
		Meme m1 = new Meme(b1,"caption1",u1);
		Meme m2 = new Meme(b2,"caption2",u2);
		Meme m3 = new Meme(b3,"caption3",u2);
		
		ArrayList<Meme> myMemes = new ArrayList<Meme>();
		myMemes.add(m1);
		myMemes.add(m2);
		myMemes.add(m3);
		
		
		
		TreeSet<Meme> myMemesViewed = new TreeSet<Meme>();
		myMemesViewed.add(m1);
		myMemesViewed.add(m2);
		u1.setMemesViewed(myMemesViewed);
		
		assertNull("",f.getNewMeme(u1));
		f.setMemes(myMemes);
		assertEquals("The getNewMeme() method did not return an appropriate Meme.",m3,f.getNewMeme(u1));
		
		Feed f2 = new Feed();
		f2.setMemes(null);
		assertEquals("The getNewMeme() method did not return null for a feed that has a null memes ArrayList Object.",null,f2.getNewMeme(u2));
		
	}

	@Test
	public void toStringTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u = new User("myUserName");
		
		Meme m1 = new Meme(b1,"caption1",u);
		Meme m2 = new Meme(b2,"caption2",u);
		Meme m3 = new Meme(b3,"caption3",u);
		
		Feed f = new Feed();
		
		ArrayList<Meme> myMemes = new ArrayList<Meme>();
		myMemes.add(m1);
		myMemes.add(m2);
		myMemes.add(m3);
		f.setMemes(myMemes);
		
		String expected = m1.toString() + " \n " + m2.toString() + " \n " + m3.toString() + " \n ";
		
		assertEquals("The toString() method did not return the appropriate String for the given feed.",expected,f.toString());
	}
	
	@Test
	public void setAndGetMemesTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		User u = new User("myUserName");
		
		Meme m1 = new Meme(b1,"caption1",u);
		Meme m2 = new Meme(b2,"caption2",u);
		Meme m3 = new Meme(b3,"caption3",u);
		
		Feed f = new Feed();
		
		ArrayList<Meme> getExpected = new ArrayList<Meme>();
		assertEquals("The getMemes() method did not return the appropriate ArrayList<Meme>",getExpected,f.getMemes());
		
		ArrayList<Meme> setList = new ArrayList<Meme>();
		setList.add(m1);
		setList.add(m2);
		setList.add(m3);
		
		ArrayList<Meme> setList2 = null;
		f.setMemes(setList2);
		assertEquals("the setMemes() method did not set the feed's memes object to the passed ArrayList<Meme>",null,f.getMemes());
	}
}
