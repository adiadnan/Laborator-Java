package MovieColection;

import java.util.Comparator;

public class MovieComparator implements Comparator<Movie> {
	@Override
	  public int compare(Movie m1, Movie m2) {
	    if (m1.mRating - m2.mRating < 0){
	    	return 1;
	    }
	    else if (m1.mRating - m2.mRating == 0){
	    	return 0;
	    }
	    else if (m1.mRating - m2.mRating > 0){
	    	return -1;
	    }
	    return 0;
	   }
}
