public class Rating {

	//Instance variables for Rating:
	
	private int score;
	private User user;
	
	//Getters and setters for instance variables:
	
	public int getScore() {
		return score;
	}
	public boolean setScore(int score) {
		if(score == 1) {
			this.score = score;
			return true;
		}
		else if(score == -1) {
			this.score = score;
			return true;
		}
		else if(score == 0) {
			this.score = score;
			return true;
		}
		else {
			return false;
		}
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	//Rating Methods: toString(), equals()
	
	@Override
	public String toString() {
		String returnStatement = "";
		if(score == 1) {
			returnStatement = this.getUser().getUserName() + " rated as an upvote";
			return returnStatement;
			
		}
		else if(score == -1) {
			returnStatement = this.getUser().getUserName() + " rated as a downvote";
			return returnStatement;
		} 
		else if(score == 0) {
			returnStatement = this.getUser().getUserName() + " rated as a pass";
			return returnStatement;
		}
		else {
			return null;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		//STUB/TODO
		//returns boolean value
		if (o == null)
			return false;
		
		if(o instanceof Rating) {
			Rating r2 = (Rating) o;
			if(this.score == r2.score && this.user.equals(r2.user))
				return true;
			
			else {
				return false;
				}
		
		}
		else
			return false;
	}
	
	//Constructor
	public Rating(User user, int score) {
	
		this.user = user;
		this.score = score;
	}
	
	//Default Constructor
	public Rating() {
		//empty, should have instance variables as parameters
		score = 0;
	}
	
}