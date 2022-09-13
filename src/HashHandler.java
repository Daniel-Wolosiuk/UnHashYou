import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**  @author Daniel Wolosiuk
**   Date: May 11, 2021
**   FileName: HashHandler.java
**   Description: Does the hashing magic. 
*/

public class HashHandler
{
	/*=============([ bytesToHex ])=============*/
	/** Precondition: byte[] hash is not null   */
	/** Postcondition: converts byte[] hash     */
	/** encoding of a String through hex, giving*/
	/** phonetic hash.                          */
	/** @param the array of bytes to convert    */
	/** @return the converted array of bytes    */
	/** through hex                             */
	/*==========================================*/
	// Works with SHA-256 (sha2, 256 size, 64 rounds)
	// Works with all other algorithms implemented in program. 
	public static String bytesToHex(byte[] hash)
	{
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for(int index = 0; index < hash.length; index++)
		{
			String hex = Integer.toHexString(0xff & hash[index]);
			if(hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	/*=============([ stringToBytes ])=============*/
	/** Precondition: toHash, instance not null    */
	/** Postcondition: converts toHash to encoded  */
	/** byte format, through hash type instance    */
	/** @param toHash: the String to convert to    */
	/** bytes                                      */
	/** @param instance: the type of hash to       */
	/** convert toHash into (byte[] form)          */
	/** @return toHash in byte form of hash type   */
	/** instance.                                  */
	/*=============================================*/
	public static byte[] stringToBytes(String toHash, String instance) throws NoSuchAlgorithmException
	{
		MessageDigest digester = MessageDigest.getInstance(instance);
		byte[] encodedHash = digester.digest(toHash.getBytes(StandardCharsets.UTF_8));
		return encodedHash;
	}
	
	/*=============([ getCompareScore ])=============*/
	/** Precondition: hash1, hash2 both not null     */
	/** Precondition: hash1 and hash2 are same length*/
	/** Precondition: hash1 and hash2 are same hash  */
	/** type.                                        */
	/** @param hash1, hash2 hashes to compare        */
	/** @return the amount of letters in the same    */
	/** spot of both hashes                          */
	/*===============================================*/
	public static int getCompareScore(String hash1, String hash2)
	{
		int score = 0;
		for(int index = 0; index < hash1.length(); index++)
		{
			if(hash1.charAt(index) == hash2.charAt(index))
				score++;
		}
		return score;
	}
}
