package MovieColection;

public class ImbdException extends Exception {
	private int id;
	
	public ImbdException(int id){
		this.id = id;
	}
	
	public String toString(){
		return "		!!! The IMBD ID must have exactly 4 digits: " + this.id + ".";
	}
}
