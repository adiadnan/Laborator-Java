package MovieColection;

public class NameDoesNotExistException extends Exception {
	private String name;
	
	public NameDoesNotExistException(String name){
		this.name = name;
	}
	
	public String toString(){
		return "	!!! The name does not exist in the database: " + this.name + ".";
	}
}
