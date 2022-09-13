import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**  @author Daniel Wolosiuk
**   Date: May 11, 2021
**   FileName: DictionaryParser.java
**   Description: Parses a word list to usable Strings. 
*/

public class DictionaryParser
{
	/*=============([ parseDictionary ])=============*/
	/** Precondition: nameOfFile is not null         */
	/** Precondition: nameOfFile exists as a file    */
	/** Postcondition: returns an ArrayList<String>  */
	/** of every word in file nameOfFile             */
	/** @param nameOfFile: the name of the file to   */
	/** parse into the ArrayList                     */
	/** @return the ArrayList of Strings with all    */
	/** values from file of name nameOfFile          */
	/*===============================================*/
	/* Note: will not work the same as MasterHashParser's crackHash method. 
	 * This method will store as many elements as possible from the wordlist
	 * into the ram of the device. If the device runs out of ram, only a 
	 * fraction of the words in the word list can be checked. 
	 * 
	 * MasterHashParser's crackHash method does not share this problem because
	 * it uses a buffered reader; hashing, checking, and scanning every element
	 * in the word list live, without actually storing more than one element
	 * at a time into the ram. 
	 */
	@SuppressWarnings("resource")
	public static ArrayList<String> parseDictionary(String nameOfFile) throws FileNotFoundException
	{
		ArrayList<String> words = new ArrayList<String>();
		Scanner in = new Scanner(new File(nameOfFile));
		while(in.hasNextLine())
			words.add(in.nextLine());
		return words;
		
	}
}
