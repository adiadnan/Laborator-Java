package MovieColection;

public class RatingException extends Exception {
	private float rating;
	
	public RatingException(float rating){
		this.rating = rating;
	}
	
	public String toString(){
		return "		!!! The rating must be between 0.0 and 10.0: " + this.rating + ".";
	}
}
