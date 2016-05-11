import java.util.ArrayList;

/**
 * Item class which holds strings of information
 * @author sarahtrop
 *
 */
public class Item {
	
	public String title;
	public String desc;
	public String effect;
	public String more;
	public int totalpoints;
	public ArrayList<String> lookups;
	
	/**
	 * Blank constructor
	 */
	public Item() { }
	
	/**
	 * Constructor for Item class
	 * @param title		a String
	 * @param desc		a String
	 * @param effect	a String
	 * @param more		a String
	 * @param points	an integer
	 */
	public Item(String title, String desc, String effect, String more, int points) {
		this.title = title;
		this.desc = desc;
		this.effect = effect;
		this.more = more;
		this.totalpoints = points;
		lookups = new ArrayList<>();
	}
	
	/**
	 * Updates the lookups field
	 * @param list
	 */
	public void updateLookups(ArrayList<String> list) { lookups = list; }
	
	/**
	 * Method for adding possible lookups for items
	 * @param list	an ArrayList of Items
	 * @return		an ArrayList of Items
	 */
	public void addLookups() {
			String title = this.title.toLowerCase();
			ArrayList<String> lookups = new ArrayList<>();
			lookups.add(title);
			
			String split[] = title.split(" ");
			if (split.length > 1) {
				split = stripName(split);
				for (int i = 0; i < split.length; i++) {
					lookups.add(split[i]);
				}
				if (split.length > 1) { lookups.add(split[0] + " " + split[1]); }
			}
			this.updateLookups(lookups);
	}
	
	/**
	 * Strips the name from an items title
	 * @param arr	an array of Strings
	 * @return		an array of Strings
	 */
	public String[] stripName(String arr[]) {
		if (arr[0].contains("'s")) {
			String newArr[] = new String[arr.length - 1];
			for (int i = 0; i < arr.length - 1; i++) {
				newArr[i] = arr[i+1];
			}
			return newArr;
		} else {
			return arr;
		}
	}
}
