import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**  @author Daniel Wolosiuk
**   Date: May 11, 2021
**   FileName: MasterHashParser.java
**   Description: Combines HashHandler and DictionaryParser to
**   take the word, hash it, compare it, and move onto the next word,
**   all into one convenient method. 
*/

public class MasterHashParser extends HashHandler 
{
	/*============================([ CrackHash ])============================*/
	/** Precondition: hash is a valid hash.                                  */
	/** Precondition: nameOfFile is the name of an actual file in the working*/
	/** directory.                                                           */
	/** Precondition: instance is a valid type of hash recognized by         */
	/** java's MessageDigest digest method.                                  */
	/** @param hash: the hash you're trying to crack.                        */
	/** @param nameOfFile: the name of the file with your dictionary word    */
	/** list.                                                                */
	/** @param instance: the type of hash that @param hash is.               */
	/** @param verbose: whether or not to display every individual hash in   */
	/** the wordlist while it is being processed.                            */
	/** @return the cracked passphrase if present in file of nameOfFile      */
	/*=======================================================================*/
	public static String crackHash(String hash, String nameOfFile, String instance, boolean verbose) throws FileNotFoundException, NoSuchAlgorithmException
	{
		// Cannot use scanner. Runs out of space in ram. 
		// Traverse everything using Stream. Or BufferedReader. 
		String password = "";
		try(BufferedReader br = new BufferedReader(new FileReader(nameOfFile)))
		{
			String line;
			String currentHash;
			while((line = br.readLine()) != null) // set line to next line, make sure it isn't null. 
			{
				// Convert the current line into byte[]
				byte[] encoded = stringToBytes(line, instance);
				// Convert the byte[] to hex, aka the hashed version. 
				currentHash = bytesToHex(encoded);
				if(verbose)
				{
					System.out.print(line);
					for(int i = line.length(); i < 20; i++)
						System.out.print(" ");
					System.out.println(currentHash);
				}
				if(currentHash.equals(hash))
				{
					password = line;
					break;
				}
			}
		}
		catch(IOException e)
		{
			System.out.println("Error detected in crackHash method.");
			e.printStackTrace();
		}
		
		
		return password;
		
	}
}
