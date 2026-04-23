package pr1;

public class BookTag implements Comparable<BookTag>{

	// ATTRIBUTES
	private String left;
	private int mid;
	private String right;

	// CONSTRICTOR
	public BookTag (String left, int mid, String right) {
		check(left, mid, right);
		this.left = left.toUpperCase();
		this.mid = mid;
		this.right = right.toUpperCase();
	}

	// TODO: ASK IF IT'S POSSIBLE TO PUT GETTERS & SETTERS

	// GETTERS
	public String getLeft(){ return this.left; }
	public int getMid() { return mid; }
	public String getRight() { return right; }

	// ************
	// OTHER FUNCTIONS
	// ************

	@Override
	public int compareTo(BookTag other) {

		// ASCENDING ORDER:
		// "this" obj. First == Negative number
		// "this" obj. Second == Positive number

		// Low

		// If it can resolve with checking "left"
		if( this.left.compareTo(other.getLeft()) != 0){

			// This.left is lower than other.getLeft() --> "this" go first --> Return negative
			if (this.left.compareTo(other.getLeft()) < 0){
				return -1;
			}

			// This.left is higher than other.getLeft() --> "this" go second --> Return positive
			else{
				return 1;
			}
		} // Cannot resolve

		// If it can resolve with checking "mid"
		else if( this.mid - other.getMid() != 0 ){

			// This.mid is lower than other.getMid() --> "this" go first --> Return negative
			if(this.mid - other.getMid() < 0){
				return -1;
			}

			// This.mid is higher than other.getMid() --> "this" go second --> Return positive
			else{
				return 1;
			}

		}// Cannot resolve

		// If it can resolve with checking "right"
		else if( this.right.compareTo(other.getRight()) != 0 ){

			// This.right is lower than other.getRight() --> "this" go second --> Return positive
			if(this.right.compareTo(other.getRight()) < 0){
				return 1;
			}

			// This.right is higher than other.getRight() --> "this" go first --> Return negative
			else{
				return -1;
			}
		} // Cannot resolve

		// The books are the same
		return 0;

		/* Booktags are sorted as follows:
		 	- first go booktags with lowest left attribute. If left attributes cannot discriminate...
		 	- ... first go booktags with the lowest mid attribute. If mid cannot discriminate...
		 	- ... first go booktags with HIGHEST right attribute. 
		 */
	}
	
	@Override
	public boolean equals (Object arg) {
		BookTag other;
		if (arg == this) {
			return true;
		}
		if (!(arg instanceof BookTag)) {
			return false;
		}
		other = (BookTag) arg;
		return (this.compareTo(other) == 0);
		
	}
	
	@Override
	public String toString () {
		return "["+left+"-"+mid+"-"+right+"]";
	}
	
	
	@Override
	public int hashCode () {
		return left.hashCode()%mid+right.hashCode()%mid;
	}
	
	
	@Override
	public BookTag clone() {
		return new BookTag(new String(left), mid, new String(right));
	}

	
	private static void check (String left, int mid, String right) {
		if (left.length()!=4) throw new IllegalArgumentException("Bad left size: "+left);
		if (mid<10 || mid>99) throw new IllegalArgumentException("Bad mid number: "+mid);
		if (right.length()!=2) throw new IllegalArgumentException("Bad right size: "+left);
	}
	
}
