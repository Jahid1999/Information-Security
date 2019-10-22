package AES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

public class Decryption 
{
	
	KeyExpansion KE = new KeyExpansion();
	
	
	public void SubRoundKey(char[] state, char[] roundKey) 
	{
		
		for (int i = 0; i < 16; i++) 
		{
			state[i] ^= roundKey[i];
		}
		
	}

	
	public void InverseMixColumns(char[] state) 
	{
		
		char[] tmp=new char[16];

		tmp[0] = (char) (KE.tb.mul14[state[0]] ^ KE.tb.mul11[state[1]] ^ KE.tb.mul13[state[2]] ^ KE.tb.mul9[state[3]]);
		tmp[1] = (char) (KE.tb.mul9[state[0]] ^ KE.tb.mul14[state[1]] ^ KE.tb.mul11[state[2]] ^ KE.tb.mul13[state[3]]);
		tmp[2] = (char) (KE.tb.mul13[state[0]] ^ KE.tb.mul9[state[1]] ^ KE.tb.mul14[state[2]] ^ KE.tb.mul11[state[3]]);
		tmp[3] = (char) (KE.tb.mul11[state[0]] ^ KE.tb.mul13[state[1]] ^ KE.tb.mul9[state[2]] ^ KE.tb.mul14[state[3]]);

		tmp[4] = (char) (KE.tb.mul14[state[4]] ^ KE.tb.mul11[state[5]] ^ KE.tb.mul13[state[6]] ^ KE.tb.mul9[state[7]]);
		tmp[5] = (char) (KE.tb.mul9[state[4]] ^ KE.tb.mul14[state[5]] ^ KE.tb.mul11[state[6]] ^ KE.tb.mul13[state[7]]);
		tmp[6] = (char) (KE.tb.mul13[state[4]] ^ KE.tb.mul9[state[5]] ^ KE.tb.mul14[state[6]] ^ KE.tb.mul11[state[7]]);
		tmp[7] = (char) (KE.tb.mul11[state[4]] ^ KE.tb.mul13[state[5]] ^ KE.tb.mul9[state[6]] ^ KE.tb.mul14[state[7]]);

		tmp[8] = (char) (KE.tb.mul14[state[8]] ^ KE.tb.mul11[state[9]] ^ KE.tb.mul13[state[10]] ^ KE.tb.mul9[state[11]]);
		tmp[9] = (char) (KE.tb.mul9[state[8]] ^ KE.tb.mul14[state[9]] ^ KE.tb.mul11[state[10]] ^ KE.tb.mul13[state[11]]);
		tmp[10] = (char) (KE.tb.mul13[state[8]] ^ KE.tb.mul9[state[9]] ^ KE.tb.mul14[state[10]] ^ KE.tb.mul11[state[11]]);
		tmp[11] = (char) (KE.tb.mul11[state[8]] ^ KE.tb.mul13[state[9]] ^ KE.tb.mul9[state[10]] ^ KE.tb.mul14[state[11]]);

		tmp[12] = (char) (KE.tb.mul14[state[12]] ^ KE.tb.mul11[state[13]] ^ KE.tb.mul13[state[14]] ^ KE.tb.mul9[state[15]]);
		tmp[13] = (char) (KE.tb.mul9[state[12]] ^ KE.tb.mul14[state[13]] ^ KE.tb.mul11[state[14]] ^ KE.tb.mul13[state[15]]);
		tmp[14] = (char) (KE.tb.mul13[state[12]] ^ KE.tb.mul9[state[13]] ^ KE.tb.mul14[state[14]] ^ KE.tb.mul11[state[15]]);
		tmp[15] = (char) (KE.tb.mul11[state[12]] ^ KE.tb.mul13[state[13]] ^ KE.tb.mul9[state[14]] ^ KE.tb.mul14[state[15]]);

		for (int i = 0; i < 16; i++) 
		{
			state[i] = tmp[i];
		}
	}

	
	public void ShiftRows(char[] state) 
	{
		
		char[] tmp=new char[16];

		/* Column 1 */
		tmp[0] = state[0];
		tmp[1] = state[13];
		tmp[2] = state[10];
		tmp[3] = state[7];

		/* Column 2 */
		tmp[4] = state[4];
		tmp[5] = state[1];
		tmp[6] = state[14];
		tmp[7] = state[11];

		/* Column 3 */
		tmp[8] = state[8];
		tmp[9] = state[5];
		tmp[10] = state[2];
		tmp[11] = state[15];

		/* Column 4 */
		tmp[12] = state[12];
		tmp[13] = state[9];
		tmp[14] = state[6];
		tmp[15] = state[3];

		for (int i = 0; i < 16; i++) 
		{
			state[i] = tmp[i];
		}
		
	}

	
	public void SubBytes(char[] state) 
	{
		
		for (int i = 0; i < 16; i++) 
		{
			
			state[i] = KE.tb.inv_sBox[state[i]];
		}
		
	}

	
	public void Round(char[] state, char[] key) 
	{
		
		SubRoundKey(state, key);
		InverseMixColumns(state);
		ShiftRows(state);
		SubBytes(state);
		
	}



	public void InitialRound(char[] state, char[] key) 
	{
		
		SubRoundKey(state, key);
		ShiftRows(state);
		SubBytes(state);
	
	}



	public void AESDecrypt(char[] encryptedMessage,char[] expandedKey, char[] decryptedMessage)
	{
		char[] state =  new char[16]; 

		for (int i = 0; i < 16; i++) 
		{
			
			state[i] = encryptedMessage[i];
		}
		
		char[] tempChar = new char[16];
		
		for(int j=0,l=160 ; j<16 ; j++,l++) 
		{
			
			tempChar[j] = expandedKey[l];
		}
		/*
		for(int k=0;k<16;k++){
			
			System.out.println(k+" : "+state[k]);
		}
		System.out.println();
		
		*/
		
		InitialRound(state, tempChar);
		

		/*
		for(int k=0;k<16;k++){
			
			System.out.println(k+" : "+state[k]);
		}
		System.out.println();
		
		*/
		for (int i = 8; i >= 0; i--) 
		{
			
			for(int j=0,l=(16*(i+1)) ; j<16 ; j++,l++) 
			{
				
				tempChar[j] = expandedKey[l];
			}
			
			Round(state, tempChar);
			
			/*
			 * for(int k=0;k<16;k++){
			
				
				System.out.println(k+" : "+state[k]);
			}
			System.out.println();
			
		*/
		
		}

		SubRoundKey(state, expandedKey);
		
		/*
		for(int k=0;k<16;k++){
			
			System.out.println(k+" : "+state[k]);
		}
		System.out.println();
		*/

		for (int i = 0; i < 16; i++) 
		{
			decryptedMessage[i] = state[i];
		}
		
	}

	
	
	public Decryption() throws IOException 
	{
		
		//System.out.println("AES 128 bit Decryption");
		
		/*String file ="encryptedMessage.txt";  
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    
	    String msgStr = reader.readLine();
	    reader.close();
		*/
		String files ="encryptedMessage.txt";
		File file=new File(files);

        byte [] bytes = Files.readAllBytes(file.toPath());
        String msgStr = new String(bytes);
		
		int encryptedMessageLen = msgStr.length();
		
		//System.out.println("encryptedMessageLen : " + encryptedMessageLen);
		
		char[] encryptedMessage = new char[encryptedMessageLen];

		
		  for(int i=0;i<encryptedMessageLen;i++) 
		  {
			
			encryptedMessage[i]=msgStr.charAt(i);
			
			//System.out.println(i+" "+encryptedMessage[i]);
		}
		
		
		
		String keyStr="03040E030D030B0A090B070F0F06030C";
		
		char[] key=new char[16];
		
		for(int i=0,j=0;i<keyStr.length() && j<16 ;i+=2,j++) 
		{
			
			String str = keyStr.substring(i, i+2);
			key[j]=(char)Integer.parseInt(str, 16);
			
		}
		
		char[] expandedKey = new char[176];
		
		KE.Expansion(key,expandedKey);
		
		//for(int i=0;i<176;i++) System.out.println(i+" "+expandedKey[i]);
		
		char[] decryptedMessage = new char[encryptedMessageLen];

		for (int i = 0; i < encryptedMessageLen; i += 16) 
		{
			
			char[] tempEncryptedMessage = new char[16];
			char[] tempDecryptedMessage = new char[16];
			
			for(int j=0,l=i;j<16;j++,l++) 
			{
				
				tempEncryptedMessage[j] = encryptedMessage[l];
			}
			
			AESDecrypt(tempEncryptedMessage, expandedKey, tempDecryptedMessage);
			
			for(int j=0,l=i;j<16;j++,l++) 
			{
				
				decryptedMessage[l] = tempDecryptedMessage[j];
			}
			
		}
		
		
		String hex="\0",str="\0";
		
		String fileName = "decryptedMessage.txt";
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    writer.close();
		
	    System.out.println("AES 128 bit Decryption Done");
		/*for(int i=0;i<encryptedMessageLen;i++) 
		{
			
			hex += String.format("%02x", (int) decryptedMessage[i]);
			writer.write(decryptedMessage[i]);
			str+=decryptedMessage[i];
		}
		
		//System.out.println("Hexadecimal encrypted string\n"+hex);
		
	  
	    
	    //System.out.println("Decrypted message: "+str);
		
		
		*/
		
	}
}
