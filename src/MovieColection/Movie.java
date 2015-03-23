package MovieColection;

import java.io.Serializable;

public class Movie implements Serializable {
	
	public enum Genre {
		comedie, drama, thriller, actiune
	};
	
	public String mName, mCategory;
	public int mDay, mMonth, mYear;
	public float mRating;
	public String mImbd;
	
	/**
	 * This constructor requires all fields to be passed as parameters.
	 * 
	 * @param mName contains the name of the movie and must start with a capital letter
	 * @param mCategory contains the category of the movie and must contain only small letters
	 * @param mDay contains the day of release and must be smaller then 32 and bigger then 0
	 * @param mMonth contains the month of release and must be smaller then 13 and bigger then 0
	 * @param mYear contains the year of release and must be smaller then 2016 and bigger then 1950
	 * @param mRating contains the rating and must be in the interval (0, 10]
	 * @param mImbdId contains the id of the movie from imbd (only digits)
	 */
	public Movie(String mName, String mCategory, int mDay, int mMonth, int mYear, float mRating, int mImbdId){
		this.mName = mName;
		this.mCategory = mCategory;
		this.mDay = mDay;
		this.mMonth = mMonth;
		this.mYear = mYear;
		this.mRating = mRating;
		this.mImbd = "";
		this.mImbd += "http://www.imdb.com/title/tt";
		this.mImbd += Integer.toString(mImbdId);
	}

}
	