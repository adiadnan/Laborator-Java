package MovieColection;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Collection implements Serializable {
	public ArrayList<Movie> movies= new ArrayList<Movie>(50);;
	
	public void Collection(){
		return;
	}
	
	public void addMovie(String name, String category, String day, String month, String year, String imbdid, String rating)
		throws NameException, NullException, CategoryException, DateException, RatingException, ImbdException{
		String aux;
		
		if (checkForNullInput(name) == true){
			throw new NullException(name);
		}
		else if (checkForCapitalLetter(name) == true){
			throw new NameException(name);
		}
		
		if (checkForNullInput(category) == true){
			throw new NullException(category);
		}
		else if (checkForCategory(category) == true){
			throw new CategoryException(name);
		}
		
		if (checkForNullInput(day) == true){
			throw new NullException(day);
		}
		int mday = Integer.parseInt(day);
		if (checkForDate(1, mday) == true){
			throw new DateException();
		}
		
		if (checkForNullInput(month) == true){
			throw new NullException(month);
		}
		int mmonth = Integer.parseInt(month);
		if (checkForDate(2, mmonth) == true){
			throw new DateException();
		}
		
		if (checkForNullInput(year) == true){
			throw new NullException(year);
		}
		int myear = Integer.parseInt(year);
		if (checkForDate(3, myear) == true){
			throw new DateException();
		}
		
		if (checkForNullInput(rating) == true){
			throw new NullException(rating);
		}
		Float mrating = Float.parseFloat(rating);
		if (checkForRating(mrating) == true){
			throw new RatingException(mrating);
		}
		
		if (checkForNullInput(imbdid) == true){
			throw new NullException(imbdid);
		}
		int mimbdid = Integer.parseInt(imbdid);
		if (checkForImbdId(mimbdid) == true){
			throw new ImbdException(mimbdid);
		}

		movies.add(new Movie(name, category, mday, mmonth, myear, mrating, mimbdid));
	}
	
	public void deleteMovie(String nume) throws NullException, NameException, NameDoesNotExistException{		
		
		if (checkForNullInput(nume) == true){
			throw new NullException(nume);
		}
		else if (checkForCapitalLetter(nume) == true){
			throw new NameException(nume);
		}
		
		int poz = checkForExistence(nume);
		if (poz == -1){
			throw new NameDoesNotExistException(nume);
		}
		
		movies.remove(poz);
	}
	
	public void makeReport(String fileName) throws NullException{
		if (checkForNullInput(fileName) == true){
			throw new NullException(fileName);
		}
		
		fileName += ".txt";
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
				
			Collections.sort(movies, new MovieComparator());
			
			writer.println("\n\n	   Comedii:");
			for (int i = 0; i < movies.size(); i++){
				if (movies.get(i).mCategory.equals("comedie")){
					writer.println("   Nume: " + movies.get(i).mName);
					writer.println("   Date of release: " + movies.get(i).mDay + "." + movies.get(i).mMonth + "." + movies.get(i).mYear);
					writer.println("   Rating on IMBD: "+ movies.get(i).mRating);
					writer.println("   IMBD link: " + movies.get(i).mImbd);
				}
			}
			writer.println("\n\n	   Drama:");
			for (int i = 0; i < movies.size(); i++){
				if (movies.get(i).mCategory.equals("drama")){
					writer.println("   Nume: " + movies.get(i).mName);
					writer.println("   Date of release: " + movies.get(i).mDay + "." + movies.get(i).mMonth + "." + movies.get(i).mYear);
					writer.println("   Rating on IMBD: "+ movies.get(i).mRating);
					writer.println("   IMBD link: " + movies.get(i).mImbd);
				}
			}
			writer.println("\n\n	   Actiune:");
			for (int i = 0; i < movies.size(); i++){
				if (movies.get(i).mCategory.equals("actiune")){
					writer.println("   Nume: " + movies.get(i).mName);
					writer.println("   Date of release: " + movies.get(i).mDay + "." + movies.get(i).mMonth + "." + movies.get(i).mYear);
					writer.println("   Rating on IMBD: "+ movies.get(i).mRating);
					writer.println("   IMBD link: " + movies.get(i).mImbd);
				}
			}
			writer.println("\n\n	   Thriller:");
			for (int i = 0; i < movies.size(); i++){
				if (movies.get(i).mCategory.equals("thriller")){
					writer.println("   Nume: " + movies.get(i).mName);
					writer.println("   Date of release: " + movies.get(i).mDay + "." + movies.get(i).mMonth + "." + movies.get(i).mYear);
					writer.println("   Rating on IMBD: "+ movies.get(i).mRating);
					writer.println("   IMBD link: " + movies.get(i).mImbd);
				}
			}
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public boolean checkForNullInput(String str){// throws NullException{
		if (str.length() == 0){
			//throw new NullException(str);
			return true;
		}
		return false;
	}
	public boolean checkForCapitalLetter(String name){// throws NameException, NullException{
		if (name.length() == 0){
			//throw new NullException(name);
			return true;
		}
		
		if (!Character.isUpperCase(name.charAt(0))){
			//throw new NameException(name);
			return true;
		}
		
		for (int i = 1; i < name.length(); i++){
			if (!Character.isLowerCase(name.charAt(i)) && !Character.isDigit(name.charAt(i))){
				//throw new NameException(name);
				return true;
			}
		}
		return false;
	}
	public boolean checkForCategory(String category){
		if (category.length() == 0){
			return true;
		}
		if (category.equals("comedie") || category.equals("drama") || category.equals("actiune") || category.equals("thriller")){
			return false;
		}
		return true;
	}
	public boolean checkForDate(int type, int date){
		if (type == 1 && (date < 1 || date > 31)){
			return true;
		}
		if (type == 2 && (date < 1 || date > 12)){
			return true;
		}
		if (type == 3 && (date < 1950 || date > 2015)){
			return true;
		}
		return false;
	}
	public boolean checkForRating(float rating){
		if (rating <= 0.0 || rating >= 10.0){
			return true;
		}
		return false;
	}
	public boolean checkForImbdId(int id){
		if (id < 1000 || id > 9999){
			return true;
		}
		return false;
	}
	public int checkForExistence(String nume){
		int ex = -1;
		for (int i = 0; i < movies.size(); i++){
			if (movies.get(i).mName.equals(nume)){
				ex = i;
				return ex;
			}
		}
		return -1;
	}

}
