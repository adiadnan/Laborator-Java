package MovieColection;

public class CommandException extends Exception {
	private String number;
	
	public CommandException(String number){
		this.number = number;
	}
	
	public String toString(){
		return "	!!! The command must be a digit between 1 and 6: " + this.number + ".";
	}
}
