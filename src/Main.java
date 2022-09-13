import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**  @author Daniel Wolosiuk
**   Date: May 11, 2021
**   FileName: Main.java
**   Description: Uses a dictionary attack on hashes to crack them. 
**   Will not crack pass phrase if pass phrase is not in the dictionary file. 
**   I will use rockyou.txt as my dictionary. It is a site from about a decade ago
**   that worked with myspace that got hacked and all passwords got leaked in plaintext.
**   There are about 14 million passwords in that file, so for a dictionary attack,
**   it is a go-to. 
*/

/************************************************************************************************/
/* 																								*/
/* 									      WORKS CITED											*/
/*																								*/
/*  Information about the hashing process: 														*/
/*  https://www.2brightsparks.com/resources/articles/introduction-to-hashing-and-its-uses.html	*/
/*  																							*/
/*  One such hashing algorithm/method (which was not used in this project) (hashmaps):			*/
/*  https://appdividend.com/2019/09/27/hashing-in-java-example-java-hashing-tutorial/			*/
/*  																							*/
/*  Hashing in java using third party and default libraries: 									*/	
/*  https://www.baeldung.com/sha-256-hashing-java												*/
/*  																							*/
/*  Using MessageDigest and SHAUtils in java:													*/
/*  https://mkyong.com/java/java-sha-hashing-example/											*/
/*  																							*/
/*  																							*/
/************************************************************************************************/


public class Main
{
	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException
	{
		/* +------------------------------------------------------------+ */
		/* | Print a nice splash for the CLI.                           | */
		/* +------------------------------------------------------------+ */
		
		System.out.println(" _   _       _   _           _  __   __          \n" 
		+ "| | | |_ __ | | | | __ _ ___| |_\\ \\ / /__  _   _ \n"
		+ "| | | | '_ \\| |_| |/ _` / __| '_ \\ V / _ \\| | | |\n"
		+ "| |_| | | | |  _  | (_| \\__ \\ | | | | (_) | |_| | \n"
		+ " \\___/|_| |_|_| |_|\\__,_|___/_| |_|_|\\___/ \\__,_| \n");
		
		/* +------------------------------------------------------------+ */
		/* | A few example hashes for testing.                          | */
		/* +------------------------------------------------------------+ */
		// Plaintext: PASSWORD1234
		// SHA-256 Hash: c7835124c2b3a20dd40bb3892748224bdfb9c943fff5850fbdb6d2b4149d8a70
		// SHA-512 Hash: 693c067a684b38268d50c2bce74217178e9baa76217e69526e010ba1387dac4fb217a6ed4d282f18e1dcfd096cee03b62c206a3c4f84aa8bf9841c938f740fe1
		// MD5 Hash: 579f276ad2a77fd1698c38e3ad4d20a7
		
		/* +------------------------------------------------------------+ */
		/* | Receiving some input on configuration on how the           | */
		/* | scan will work.                                            | */
		/* +------------------------------------------------------------+ */
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Input hash: \n>>");
		String hash = in.nextLine();
		
		/* +------------------------------------------------------------+ */
		/* | Asking for input on the type of hash you are trying to     | */
		/* | crack.                                                     | */
		/* +------------------------------------------------------------+ */
		
		System.out.println("Hash instance: ");
		System.out.println();
		System.out.println("     [ 0 ] SHA-1");
		System.out.println("     [ 1 ] SHA-224");
		System.out.println("     [ 2 ] SHA-256");
		System.out.println("     [ 3 ] SHA-384");
		System.out.println("     [ 4 ] SHA-512");
		System.out.println("     [ 5 ] MD2");
		System.out.println("     [ 6 ] MD5");
		System.out.print("\n>>");
		int instanceNum = in.nextInt();
		in.nextLine();
		String instance = "";
		
		/* +------------------------------------------------------------+ */
		/* | Configuring that input to a recognizable type by           | */
		/* | MasterHashParser.java's crackHash method.                  | */
		/* +------------------------------------------------------------+ */
		switch(instanceNum)
		{
			case 0: instance = "SHA-1";
					break;
			case 1: instance = "SHA-224";
					break;
			case 2: instance = "SHA-256";
					break;
			case 3: instance = "SHA-384";
					break;
			case 4: instance = "SHA-512";
					break;
			case 5: instance = "MD2";
					break;
			case 6: instance = "MD5";
					break;
		}
		
		/* +------------------------------------------------------------+ */
		/* | Asking whether or not to use verbose mode.                 | */
		/* | Verbose mode will display every hash of every entry in     | */
		/* | the dictionary file, which by default is rockyou.txt       | */
		/* +------------------------------------------------------------+ */
		
		System.out.print("Verbose mode? [y/n] \n>>");
		String verbStr = in.nextLine();
		
		/* +------------------------------------------------------------+ */
		/* | Processing verbStr into a boolean from a String.           | */
		/* +------------------------------------------------------------+ */
		
		boolean verbose; 
		if(verbStr.equalsIgnoreCase("y"))
			verbose = true;
		else
			verbose = false;
		
		/* +------------------------------------------------------------+ */
		/* | Running the scan. If the password can be cracked using     | */
		/* | this wordlist, it will be output. If not, it will not be.  | */
		/* +------------------------------------------------------------+ */
		in.close();
		System.out.println("Running scan...");
		String password = MasterHashParser.crackHash(hash, "rockyou.txt", instance, verbose);
		System.out.println("\n[-] - [-] - [-] - [-] - [-] - [-] - [-] - [-] - [-] - [-] - [-]");
		if(password.length() > 0)
		{
			System.out.println("Password cracked!");
			System.out.println("Password: " + password);
		}
		else
		{
			System.out.println("Passphrase not found in wordlist.");
		}
	}
}
