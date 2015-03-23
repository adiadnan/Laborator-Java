package MovieColection;

public class NullException extends Exception {
	private String name;
	
	public NullException(String name){
		this.name = name;
	}
	
	public String toString(){
		return "The string must contain at least a letter: " + this.name + ".";
	}
}
