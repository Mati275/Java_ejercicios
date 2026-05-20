package tracker;


public class BookTag implements Comparable<BookTag>{

	private String left;
	private int mid;
	private String right;

	public BookTag (String left, int mid, String right) {
		check(left, mid, right);
		this.left = left.toUpperCase();
		this.mid = mid;
		this.right = right.toUpperCase();
	}


	@Override
	public int compareTo(BookTag other) {
		/* Booktags are sorted as follows:
	 	- first go booktags with lowest left attribute. If left attributes cannot discriminate...
	 	- ... first go booktags with the lowest mid attribute. If mid cannot discriminate...
	 	- ... first go booktags with HIGHEST right attribute. 
	 */

		/* COMPLETE */
		if (! left.equals(other.left)){
			return left.compareTo(other.left);
		}

		else if( mid != other.mid ){
			return mid - other.mid;
		}

		else {
			return -(this.right.compareTo(other.right));
		}
	}

	@Override
	public boolean equals (Object arg) {
		try {
			return this.compareTo((BookTag)arg)==0;
		}
		catch(ClassCastException cce) {
			return false;
		}
	}

	@Override
	public String toString () {
		return "["+left+"-"+mid+"-"+right+"]";
	}


	@Override
	public int hashCode () {
		return left.hashCode()%mid+right.hashCode()%mid;
	}

	private static void check (String left, int mid, String right) {
		if (left.length()!=4) throw new IllegalArgumentException("Bad left size: "+left);
		if (mid<10 || mid>99) throw new IllegalArgumentException("Bad mid number: "+mid);
		if (right.length()!=2) throw new IllegalArgumentException("Bad right size: "+left);
	}

	@Override
	public BookTag clone() {
		return new BookTag(new String(left), mid, new String(right));
	}

}