import static org.junit.Assert.*;

import org.junit.Test;

public class RatingTest {
	
	@Test
	public void defaultConstructorTest() {
		Rating r = new Rating();
		assertEquals("The default constructor did not initialize score to the int 0.",0,r.getScore());
	}
	
	@Test
	public void overloadedConstructorTest() {
		User u = new User("jake");
		Rating r = new Rating(u,1);
		
		assertEquals("The constructor did not initialize the user to the User object passed into the constructor.",u,r.getUser());
		assertEquals("The constructor did not set the score to the int passed into the constructor.",1,r.getScore());
		
	}

	@Test
	public void toStringTest() {
		User u = new User("Bayan");
		Rating r1 = new Rating(u,1);
		Rating r2 = new Rating(u,-1);
		Rating r3 = new Rating(u,0);
		Rating r4 = new Rating(u,10);
		
		String expectedReturn1 = "Bayan rated as an upvote";
		String expectedReturn2 = "Bayan rated as a downvote";
		String expectedReturn3 = "Bayan rated as a pass";
		String expectedReturn4 = null;
		
		assertEquals("The toString() method did not return a string matching that expected for a +1 rating",expectedReturn1,r1.toString());
		assertEquals("The toString() method did not return a string matching that expected for a -1 rating",expectedReturn2,r2.toString());
		assertEquals("The toString() method did not return a string matching that expected for a 0 rating",expectedReturn3,r3.toString());
		assertEquals("The toString() method did not return a string matching that expected for an out-of-bounds rating",expectedReturn4,r4.toString());
	}
	
	@Test
	public void equalsTest() {
		User u = new User("Bayan");
		Rating r1 = new Rating(u,1);
		Rating r2 = new Rating(u,-1);
		Rating r3 = new Rating(u,1);
		User u1 = new User("idk");
		Rating r5 = null;
		Rating r6 = new Rating(u1,1);
		
		assertEquals("The equals() method did not return false for comparison with a null object.",false,r1.equals(r5));
		assertEquals("The equals() method did not return true for two identical ratings",true,r1.equals(r3));
		assertEquals("The equals() method did not return false for two ratings with different score.",false,r1.equals(r2));
		assertEquals("The equals() method did not return false fot two ratings with different User",false,r1.equals(r6));
		assertEquals("The equals() method did not return false for comparison with an Object of another type.",false,r1.equals(u1));
	}
	
	@Test
	public void setAndGetScoreTest() {
		User u = new User("Bayan");
		Rating r = new Rating(u,1);
		
		assertEquals("The getScore() method did not return the score expected for the given rating",1,r.getScore());
		
		r.setScore(0);
		assertEquals("The setScore() method did not return true when setting the score to 0.",true,r.setScore(0));
		assertEquals("The setScore() method did not set the score to the given int",0,r.getScore());
		
		r.setScore(-1);
		assertEquals("The setScore() method did not return true when setting the score to -1.",true,r.setScore(-1));
		assertEquals("The setScore() method did not set the score to the given int",-1,r.getScore());
		
		r.setScore(1);
		assertEquals("The setScore() method did not return true when setting the score to 1.",true,r.setScore(1));
		assertEquals("The setScore() method did not set the score to the given int",1,r.getScore());
		
		r.setScore(10);
		assertEquals("The setScore() method did not return false when setting the score to an int that wasn't -1, 0, or 1.",false,r.setScore(10));
		assertNotEquals("The setScore() method set the score to the given int even though it was out of bounds",10,r.getScore());
	}
	
	@Test
	public void setAndGetUserTest() {
		User u = new User("Bayan");
		User u2 = new User("Jake");
		Rating r = new Rating(u,1);
		
		assertEquals("The getUser() method did not return the User assigned to the user instance variable",u,r.getUser());
		
		r.setUser(u2);
		
		assertEquals("The setUser() method did not set the user to the given User object.",u2,r.getUser());
	}
}
