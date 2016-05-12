import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextAdventure {
	
	/**
	 * Main driver for the text adventure game, based on The Harmony of the Spheres by Salmon Rushdie
	 * @param args	an array of strings
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		boolean playing = true;
		Player player = new Player();
		String command = new String();
		boolean finished = true;
		
		printWelcome();
		
		while(playing) {
			if (player.getTurns() == 0) { player.room.enteredRoom(); }
			
			if (player.checkRoom("Lucy and Eliot's Guest Bedroom") && finished) {
				System.out.println();
				System.out.println("=====");
				System.out.println("You have " + player.getPoints() + " points! Do you know what happened to Eliot Crane?");
				String answer = in.nextLine();
				if (answer.toLowerCase().equals("yes")) {
					System.out.println("Good job! Maybe now someone can finish that Glendower manuscript.");
					playing = false;
				} else {
					finished = false;
				}
			} else {
				System.out.println(player.getTurns() + " =====");
				command = in.nextLine();
				if (command.toLowerCase().equals("done")) { 
					System.out.println("You have " + player.getPoints() + " points!");
					System.out.println("Good job! Maybe now someone can finish that Glendower manuscript.");
					playing = false; 
				}
				else {
					player.checkWaits(command);
					Parser.parseCommands(command, player);
				}
			}
		}
	}
	
	/**
	 * Prints the welcome message and options for the user of the game at the start
	 */
	public static void printWelcome() {
		System.out.println("Welcome to the aftermath of Salmon Rushdie's 'Harmony of the Spheres'. "
				+ "Your goal is to find out what happened to Eliot Crane. "
				+ "You may look around the house he shared with his wife Lucy Evans, and talk to anyone you find in the house.");
		
		System.out.println();
		System.out.println("You can use these commands to navigate:");
		System.out.println("Wait");
		System.out.println("Go <north/south>");
		System.out.println("Talk to <item/person>");
		System.out.println("More from <person>");
		System.out.println("Pick up <item>");
		System.out.println("Look at <item/person>");
		System.out.println("Use <item/person>");
		System.out.println("Describe the room");
		System.out.println("Done (when you think you know what happened to Eliot Crane)");
		
		System.out.println("Lets begin!");
		System.out.println();
		System.out.println("In the time of the Jubilee, the writer Eliot Crane had just moved to "
				+ "the small Welsh town of R with his wife, the newspaper editor Lucy Evans. "
				+ "Eliot was working on a book about occultist groups, entitled 'The Harmony of the Spheres.");
	}
}
