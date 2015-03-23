package MovieColection;

public class DateException extends Exception {

	public DateException(){
		return;
	}
	
	public String toString(){
		return "		!!! The date of release is incorect.";
	}
}
