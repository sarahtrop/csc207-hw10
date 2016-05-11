import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Person class, extending the Item class for the game
 * @author sarahtrop
 *
 */
public class Person extends Item {
	
	/**
	 * Person constructor, defined by the Item constructor in the super class
	 * @param title		a String
	 * @param desc		a String
	 * @param effect	a String
	 * @param more		a String
	 * @param points	an integer
	 */
	public Person(String title, String desc, String effect, String more, int points) {
		super(title, desc, effect, more, points);
	}
	
	/**
	 * Gets the people info an makes Persons from it
	 * @return	an array of Persons
	 * @throws FileNotFoundException
	 */
	public static Person[] findPeople() throws FileNotFoundException {
		ArrayList<ArrayList<String>> peopleInfo = parsePeople();
		Person array[] = new Person[3];
		
		Person Lucy = new Person(peopleInfo.get(0).get(0), peopleInfo.get(0).get(1), 
				peopleInfo.get(0).get(2), peopleInfo.get(0).get(3), Integer.parseInt(peopleInfo.get(0).get(4))); 
		Person Khan = new Person(peopleInfo.get(1).get(0), peopleInfo.get(1).get(1),
				peopleInfo.get(1).get(2), peopleInfo.get(1).get(3), Integer.parseInt(peopleInfo.get(1).get(4))); 
		Person Mala = new Person(peopleInfo.get(2).get(0), peopleInfo.get(2).get(1), 
				peopleInfo.get(2).get(2), peopleInfo.get(2).get(3), Integer.parseInt(peopleInfo.get(2).get(4))); 
		
		Lucy.addLookups();
		Mala.addLookups();
		Khan.addLookups();
		
		array[0] = Lucy;
		array[1] = Khan;
		array[2] = Mala;
		
		return array;
	}
	
	/**	
	 * Parses the people information from an input file
	 * @return	an ArrayList of ArrayLists of Strings
	 * @throws FileNotFoundException
	 */
	public static ArrayList<ArrayList<String>> parsePeople() throws FileNotFoundException {
		Scanner dataIn = new Scanner(new File("data/people.txt"));
		
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
	
	/**
	 * Updates the lookups field
	 * @param list
	 */
	@Override
	public void updateLookups(ArrayList<String> list) { lookups = list; }
	
	/**
	 * Method for adding possible lookups for items
	 * @param list	an ArrayList of Items
	 * @return		an ArrayList of Items
	 */
	@Override
	public void addLookups() {
			String title = this.title.toLowerCase();
			ArrayList<String> lookups = new ArrayList<>();
			lookups.add(title);
			
			if (title.equals("lucy evans")) { lookups.add("lucy"); }
			else if (title.equals("dr. mala khan")) { 
				lookups.add("mala");
				lookups.add("mala khan");
			}
			this.updateLookups(lookups);
	}
}
