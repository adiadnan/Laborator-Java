package MovieColection;

public class CategoryException extends Exception {
	private String category;
	
	public CategoryException(String category){
		this.category = category;
	}
	
	public String toString(){
		return "		!!! The category must be one of the following: comedie / drama /"
				+ " actiune / thriller. You typed in " + this.category + ".";
	}
}
