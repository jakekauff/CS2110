import static org.junit.Assert.*;

import org.junit.Test;

public class BackgroundImageTest {

	@Test
	public void overloadedConstructorTest() {
		BackgroundImage b = new BackgroundImage("myImageFileName.jpeg","title","description");
		assertEquals("The imageFileName did not match the one provided to the constructor.","myImageFileName.jpeg",b.getImageFileName());
		assertEquals("The title did not match the one provided to the constructor.","title",b.getTitle());
		assertEquals("The description did not match the one provided to the constructor.","description",b.getDescription());
	}

	@Test
	public void toStringTest() {
		BackgroundImage b = new BackgroundImage("myImageFileName.jpeg","title","description");
		String expected = "title <description>";
		assertEquals("The toString() method did not return the expected string for the provided BackgroundImage",expected,b.toString());
	}
	
	@Test
	public void equalsTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b4 = new BackgroundImage("myImageFileName2.jpeg","title1","description1");
		BackgroundImage b5 = new BackgroundImage("myImageFileName1.jpeg","title2","description1");
		BackgroundImage b6 = new BackgroundImage("myImageFileName1.jpeg","title1","description2");
		User u = new User("Bayan");
		
		assertEquals("The equals() method did not return false for two different BackgroundImages",false,b1.equals(b2));
		assertEquals("The equals() method did not return true for two equivalent BackgroundImages",true,b1.equals(b3));
		assertEquals("The equals() method did not return false for a comparison with a BackgroundImage and a null object",false,b1.equals(null));
		assertEquals("The equals() method did not return false for a comparison with an Object of another type",false,b1.equals(u));
		assertEquals("The equals() method did not return false for BackgroundImages with different imageFileNames",false,b1.equals(b4));
		assertEquals("The equals() method did not return false for BackgroundImages with different titles",false,b1.equals(b5));
		assertEquals("The equals() method did not return false for BackgroundImages with different descriptions",false,b1.equals(b6));
	}
	
	@Test
	public void compareToTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b2 = new BackgroundImage("myImageFileName2.jpeg","title2","description2");
		BackgroundImage b3 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		BackgroundImage b4 = new BackgroundImage("myImageFileName1.jpeg","title1","description2");
		BackgroundImage b5 = new BackgroundImage("myImageFileName1.jpeg","title2","description1");
		
		int zero = 0;
		
		assertEquals("The compareTo() method did not return 0 for two equivalent BackgroundImages",zero,b1.compareTo(b3));
		assertNotEquals("The compareTo() method returned 0 for two BackgroundImages with different titles",zero,b1.compareTo(b2));
		assertNotEquals("The compareTo() method returned 0 for two BackgroundImages with different descriptions",zero,b3.compareTo(b4));
		assertNotEquals("The compareTo() method returned 0 for two BackgroundImages with different titles",zero,b3.compareTo(b5));
	}
	
	@Test
	public void setAndGetImageFileNameTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		String expected1 = "myImageFileName1.jpeg";
		assertEquals("The getImageFileName() method did not return the imageFileName provided to the constructor",expected1,b1.getImageFileName());
		
		b1.setImageFileName("newImageFileName");
		String expected2 = "newImageFileName";
		
		assertEquals("The setImageFileName() method did not set the imageFileName to the given String",expected2,b1.getImageFileName());
	}
	
	@Test
	public void setAndGetTitleTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		String expected1 = "title1";
		assertEquals("The getTitle() method did not return the title provided to the constructor",expected1,b1.getTitle());
		
		b1.setTitle("newTitle");
		String expected2 = "newTitle";
		
		assertEquals("The setTitle() method did not set the title to the given String",expected2,b1.getTitle());
	}
	
	@Test
	public void setAndGetDescriptionTest() {
		BackgroundImage b1 = new BackgroundImage("myImageFileName1.jpeg","title1","description1");
		
		String expected1 = "description1";
		assertEquals("The getDescription() method did not return the description provided to the constructor",expected1,b1.getDescription());
		
		b1.setDescription("newDescription");
		String expected2 = "newDescription";
		
		assertEquals("The setDescription() method did not set the description to the given String",expected2,b1.getDescription());
	}
}
