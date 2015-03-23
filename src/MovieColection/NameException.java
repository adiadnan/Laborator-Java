package MovieColection;

public class NameException extends Exception {
	private String name;
	
	public NameException(String name){
		this.name = name;
	}
	
	public String toString(){
		return "The name of the movie must begin with a capital letter and continue with small ones: " + this.name + ".";
	}
}
