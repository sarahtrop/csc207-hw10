import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Room class that holds the information about rooms for the game
 * @author sarahtrop
 *
 */
public class Room {
	
	private String title;
	public String desc;
	private ArrayList<Item> items;
	public Room north;
	public Room south;
	private boolean open;
	
	/**
	 * empty constructor
	 */
	public Room() {
		open = false;
	}
	
	/**
	 * Constructor which adds the pertinent info for a room
	 * @param title	a String
	 * @param desc	a String
	 * @param items	an ArrayList of items
	 * @param open	a boolean
	 */
	public Room(String title, String desc, ArrayList<Item> items, boolean open) {
		this.title = title;
		this.desc = desc;
		this.items = items;
		this.open = open;
	}
	
	/**
	 * Sets the connected rooms for a room
	 * @param north	a Room
	 * @param south	a Room
	 */
	public void setRoom(Room north, Room south) {
		this.north = north;
		this.south = south;
	}
	
	/**
	 * Opens a room
	 */
	public void openRoom() {
		open = true;
	}
	
	/**
	 * Prints the description of a room when the player enters it
	 */
	public void enteredRoom() {
		System.out.println(desc);
	}
	
	/**
	 * Moves the player to the next room
	 * @param direction	a String
	 * @return			a Room
	 */
	public Room moveRoom(String direction) {
		if (direction.toLowerCase().equals("north")) {
			if (north.open == false) {
				System.out.println("This room seems to be locked, try something else.");
				return null;
			} 
			return north;
		} else if (direction.toLowerCase().equals("south")) {
			if (south.open == false) {
				System.out.println("This room seems to be locked, try something else.");
				return null;
			} 
			return south;
		} else {
			System.out.println("Invalid direction, try again");
			return null;
		}
	}
	
	/**
	 * Checks if the room is equal to the string
	 * @param title	a String
	 * @return		a boolean
	 */
	public boolean checkRoom(String title) {
		return this.title.equals(title);
	}
	
	/**
	 * Gets the appropriate item from the room
	 * @param item	a String
	 * @return		an Item
	 */
	public Item getItem(String item) {
		if (item.toLowerCase().equals("mala")) { item = "Dr. Mala Khan"; }
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).title.toLowerCase().equals(item.toLowerCase())) { return items.get(i); }
		}
		return null;
	}
	
	/**
	 * Gets the items for this room
	 * @return
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * Method making Rooms from parsed strings
	 * @return	a Room
	 * @throws FileNotFoundException
	 */
	public Room buildHouse() throws FileNotFoundException {
		ArrayList<ArrayList<String>> roomInfo = parseRooms();
		ArrayList<ArrayList<Item>> itemInfo = populateItems();
		
		Room guestBedroom = new Room(roomInfo.get(3).get(0), roomInfo.get(3).get(1), itemInfo.get(3), false);
		Room bedroom = new Room(roomInfo.get(2).get(0), roomInfo.get(2).get(1), itemInfo.get(2), false);
		Room livingRoom = new Room(roomInfo.get(1).get(0), roomInfo.get(1).get(1), itemInfo.get(1), true);
		Room diningRoom = new Room(roomInfo.get(0).get(0), roomInfo.get(0).get(1), itemInfo.get(0), true);
		
		guestBedroom.setRoom(bedroom, null);
		bedroom.setRoom(livingRoom, guestBedroom);
		livingRoom.setRoom(diningRoom, bedroom);
		diningRoom.setRoom(null, livingRoom);
		
		return diningRoom;
	}

	/**
	 * Method making Items from parsed strings
	 * @return	an ArrayList of ArrayLists of Items
	 * @throws FileNotFoundException
	 */
	public ArrayList<ArrayList<Item>> populateItems() throws FileNotFoundException {
		ArrayList<ArrayList<String>> itemInfo = parseItems();
		ArrayList<Item> dining = new ArrayList<>();
		ArrayList<Item> living = new ArrayList<>();
		ArrayList<Item> bedroom = new ArrayList<>();
		ArrayList<Item> guest = new ArrayList<>();
		Item temp = new Item();
		
		for (ArrayList<String> lst : itemInfo) {
			temp = new Item(lst.get(1), lst.get(2), lst.get(3), lst.get(4), Integer.parseInt(lst.get(5)));
			switch(lst.get(0)) {
				case "D":
					dining.add(temp);
					break;
				case "L":
					living.add(temp);
					break;
				case "B":
					bedroom.add(temp);
					break;
				case "G":
					guest.add(temp);
					break;
				default:
					break;
			}
		}
		
		ArrayList<ArrayList<Item>> allItems = new ArrayList<>();
		allItems.add(dining);
		allItems.add(living);
		allItems.add(bedroom);
		allItems.add(guest);
		return allItems;
	}
	
	/**
	 * Parses the room information from an input file
	 * @return	an ArrayList of ArrayLists of Strings
	 * @throws FileNotFoundException
	 */
	public ArrayList<ArrayList<String>> parseRooms() throws FileNotFoundException {
		Scanner dataIn = new Scanner(new File("data/rooms.txt"));
		
		ArrayList<ArrayList<String>> rooms = new ArrayList<>();
		ArrayList<String> lineLst;
		while (dataIn.hasNextLine()) {
			String line = dataIn.nextLine();
			String[] lineArray = line.split("\t");
			lineLst = new ArrayList<>();
			for (int j = 0; j < lineArray.length; j++) {
				lineLst.add(lineArray[j]);
			}
			rooms.add(lineLst);
		}
		dataIn.close();
		return rooms;
	}
	
	/**	
	 * Parses the item information from an input file
	 * @return	an ArrayList of ArrayLists of Strings
	 * @throws FileNotFoundException
	 */
	public ArrayList<ArrayList<String>> parseItems() throws FileNotFoundException {
		Scanner dataIn = new Scanner(new File("data/items.txt"));
		
		ArrayList<ArrayList<String>> items = new ArrayList<>();
		ArrayList<String> lineLst;
		while (dataIn.hasNextLine()) {
			String line = dataIn.nextLine();
			String[] lineArray = line.split("\t");
			lineLst = new ArrayList<>();
			for (int j = 0; j < lineArray.length; j++) {
				lineLst.add(lineArray[j]);
			}
			items.add(lineLst);
		}
		dataIn.close();
		return items;
	}

}
