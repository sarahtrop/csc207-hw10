import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Player class for the game, stores information about the user
 * @author sarahtrop
 *
 */
public class Player {
	
	private int turns;
	private int waits;
	private int points;
	public Room room;
	private Inventory inventory;
	public Person people[];
	
	/**
	 * Constructor sets all counters to 0, and builds the house
	 * @throws FileNotFoundException 
	 */
	public Player() throws FileNotFoundException {
		turns = 0;
		waits = 0;
		points = 0;
		room = new Room();
		room = room.buildHouse();
		people = Person.findPeople(); // Lucy = 0, Khan = 1, Mala = 2
		inventory = new Inventory();
		updateInventory();
	}
	
	/**
	 * Method adds the points from an item to a players total points
	 * @param item
	 */
	public void addPoints(Item item) { points += item.points; }
	/**
	 * Method gets the players total points
	 * @return
	 */
	public int getPoints() { return points; }
	/**
	 * Method changes the waits based on the players commands
	 * @param command	a String
	 */
	public void checkWaits(String command) {
		if (command.toLowerCase().equals("wait")) { waits++; }
		else { waits = 0; }
	}
	/**
	 * Gets the number of waits the player has used in a row
	 * @return	an integer
	 */
	public int getWaits() { return waits; }
	/**
	 * Increments the turn counter
	 */
	public void nextTurn() { turns++; }
	/**
	 * Gets the total number of turns
	 * @return	an integer
	 */
	public int getTurns() { return turns; }
	/**
	 * Checks if the room is equal to the current one
	 * @param title	a String
	 * @return		a boolean
	 */
	public boolean checkRoom(String title) { return room.checkRoom(title); }
	
	/**
	 * Method to update the inventory in each room
	 */
	public void updateInventory() { inventory.update(room); }
	/**
	 * Method to add an item to the inventory
	 * @param item
	 */
	public void addToInventory(Item item) { inventory.add(item); }
	
	/**
	 * Method to get the inventory
	 * @return	an Inventory
	 */
	public Inventory getInventory() { return inventory;	}
	
	/** 
	 * Method to get a person for the right place
	 * @param name	a String
	 * @return		a Person
	 */
	public Person getPerson(String name) {
		if (name.equals("Lucy")) { return people[0]; } 
		else if (name.equals("Khan")) { return people[1]; } 
		else if (name.equals("Mala")) { return people[2]; }
		else { return null; }
	}
}
