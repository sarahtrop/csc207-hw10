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
	public int points;
	
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
		this.points = points;
	}
}
