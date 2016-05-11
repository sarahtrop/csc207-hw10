import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser class that takes commands from the user in the TextAdventure game and interprets those commands
 * @author sarahtrop
 *
 */
public class Parser {
	
	/**
	 * Parses commands provided by the player to determine what happens next in the game.
	 * @param command	a string
	 * @param player	a Player
	 * @throws FileNotFoundException 
	 */
	public static void parseCommands(String command, Player player) throws FileNotFoundException {
		String[] parsed = command.toLowerCase().split(" ");
		String item = new String();
		
		if (parsed.length > 1) {
			String temp = parsed[1].toLowerCase();
			if (temp.equals("to") || temp.equals("at") || temp.equals("up") || temp.equals("from")) {
				if (parsed[2].toLowerCase().equals("dr.")) { item = parsed[3]; }
				else { item = parsed[2]; }
			} else { item = temp; }
		} else { item = parsed[0]; }
		
		if (item.toLowerCase().equals("wait")) {
			System.out.println("...");
			waitTurn(player);
		} else if (item.toLowerCase().equals("describe")) { 
			player.room.enteredRoom();
		} else if (item.toLowerCase().equals("north") || item.toLowerCase().equals("south")) {
			actionTurn(player, parsed[0], item);
		} else {
			actionTurn(player, parsed[0], item);
		}
	}
	
	/**
	 * Method that conducts a turn in which no action is selected
	 * @param player	a Player
	 * @throws FileNotFoundException 
	 */
	public static void waitTurn(Player player) throws FileNotFoundException {
		checkWaits(player);
		extraInfo(player.getTurns());
		player.nextTurn();
	}
	
	/**
	 * Method that performs actions
	 * @param player	a Player
	 * @param action	a String
	 * @param item		a String
	 * @throws FileNotFoundException 
	 */
	public static void actionTurn(Player player, String action, String item) throws FileNotFoundException {
		checkWaits(player);
		extraInfo(player.getTurns());
		action(player, action, item);
		player.nextTurn();
	}
	
	/**
	 * Checks if the number of waits is appropriate to print the special info.
	 * @param player	a Player
	 */
	public static void checkWaits(Player player) {
		if (player.getWaits() == 2 && !player.getInventory().contains("Khan")) {
			if (player.room.checkRoom("Lucy and Eliot's Living Room")) {
				System.out.println("Khan and his wife Mala enter the room. Khan sits heavily in the armchair, and Mala stands behind him, watching him."
						+ "Khan picks up the heavily earmarked book from the sidetable and begins to read."
						+ " Mala is Khan's wife, and Eliot's ex.. something. She may have information for you. ");
				player.addToInventory(player.getPerson("Khan"));
				player.addToInventory(player.getPerson("Mala"));
			} else if (player.room.checkRoom("Lucy and Eliot's Bedroom") && !player.getInventory().contains("Lucy")) {
				System.out.println("Lucy walks in from the living room and sits heavily on the bed. "
						+ "She doesn't seem to notice you, as she twirls her wedding ring around her finger.");
				player.addToInventory(player.getPerson("Lucy"));
			}
		}
	}
	
	/**
	 * Finds the correct action to perform based on the command and Player status
	 * @param player	a Player
	 * @param action	a string
	 * @param item		a string
	 * @param currItem	an Item
	 */
	public static void action(Player player, String action, String item) {
		Item currItem;
		Inventory inventory = player.getInventory();
		if (inventory.contains(item)) { currItem = player.room.getItem(item); }
		else { 
			System.out.println("That item is not in this room, try something else.");
			return;
		}
		switch (action) {
		case "go":
			Room move = player.room.moveRoom(item);
			if (move != null) { 
				player.room = move;
				player.room.enteredRoom();
				player.updateInventory();
			}
			break;
		case "talk":
			if (inventory.talkTo(currItem)) {
				if (currItem.title.equals("Lucy Evans") || currItem.title.equals("Khan")) { player.room.south.openRoom(); }
			}
			break;
		case "pick":
			inventory.pickUp(currItem);
			break;
		case "more":
			inventory.more((Person)currItem);
			player.addPoints(currItem);
			break;
		case "look":
			inventory.lookAt(currItem);
			break;
		default:
			System.out.println("Invalid command, try again");
			break;
		}
	}
	
	/**
	 * Prints extra information for the user based on the turn number
	 * @param turn	an integer
	 * @throws FileNotFoundException 
	 */
	public static void extraInfo(int turn) throws FileNotFoundException {
		Scanner dataIn = new Scanner(new File("data/extra.txt"));
		
		ArrayList<String> text = new ArrayList<>();
		while (dataIn.hasNextLine()) {
			String line = dataIn.nextLine();
			text.add(line);
		}
		dataIn.close();
		
		switch(turn) {
		case 3:
			System.out.println();
			System.out.println("---- " + text.get(0));
			break;
		case 5:
			System.out.println();
			System.out.println("---- " + text.get(1));
			break;
		case 7:
			System.out.println();
			System.out.println("---- " + text.get(2));
			break;
		case 9:
			System.out.println();
			System.out.println("---- " + text.get(3));
			break;
		case 10:
			System.out.println();
			System.out.println("---- " + text.get(4));
			break;
		default:
			break;
		}
	}
	
}
