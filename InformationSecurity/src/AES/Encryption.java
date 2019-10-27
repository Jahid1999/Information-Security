package AES;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;



public class Encryption 
{

	
	////ds,l//
	
	Aes AES = new Aes();
	
	String counter="D030B0A090B070F0";
	
	
	public Encryption() throws IOException 
	{
		
		Scanner cin = new Scanner(System.in);
		System.out.println("choose your encryption mode\n 1.CBC	2.ECB");
		int ch=cin.nextInt();
		String temp;
		temp= cin.nextLine();
		
		//CBC MODE************
		
		if(ch==1)
		{
			Scanner ci = new Scanner(System.in);
			System.out.print("Enter  plaintext: ");
			String message;
			message = ci.nextLine(); 
			ci.close();
			
			//System.out.println(message);
			
			int messageLen = message.length();
			int paddedMessageLen = messageLen;
			
			if( (paddedMessageLen%16)!=0 ) 
			{
				
				paddedMessageLen = ((paddedMessageLen/16)+1)*16;
			}
			
			char[] paddedMessage = new char[paddedMessageLen];
			
			for(int i=0;i<paddedMessageLen;i++) 
			{
				
				if(i>=messageLen) 
				{
					
					paddedMessage[i]=0;
				}
				
				else 
				{
					
					paddedMessage[i]=message.charAt(i);
				}
				
			}
			
			
			String keyStr="03040E030D030B0A090B070F0F06030C";
			
			char[] key=new char[16];
			
			for(int i=0,j=0;i<keyStr.length() && j<16 ;i+=2,j++) 
			{
				
				String str = keyStr.substring(i, i+2);
				key[j]=(char)Integer.parseInt(str, 16);
				
			}
			
			char[] expandedKey = new char[176];
			
			AES.KE.Expansion(key,expandedKey);
			
			//for(int i=0;i<176;i++) System.out.println(i+" "+expandedKey[i]);
			
			char[] encryptedMessage = new char[paddedMessageLen];
			
			char[] iv=new char[16];
			
			for(int i=0,j=0;i<counter.length() && j<16 ;i+=2,j++) 
			{
				
				String str = counter.substring(i, i+2);
				iv[j]=(char)Integer.parseInt(str, 16);
				
			}
			
			
			
			for (int i = 0; i < paddedMessageLen; i += 16) 
			{
				
				char[] tempPaddedMessage = new char[16];
				char[] tempEncryptedMessage = new char[16];
				
				
				
				
				for(int j=0,l=i;j<16;j++,l++) 
				{
					
					tempPaddedMessage[j] = paddedMessage[l];
				}
				
				for (int k = 0; k< 16; k++) 
				{
					tempPaddedMessage[k] ^= iv[k];
				}
				
				
				
				AES.AESEncrypt(tempPaddedMessage, expandedKey, tempEncryptedMessage);
				
				
				for(int j=0,l=i;j<16;j++,l++) 
				{
					encryptedMessage[l] = tempEncryptedMessage[j];
					iv[j] = tempEncryptedMessage[j];
				}
				
				
				
				
					
			}
			
			
			
			String hex="\0";
			
			String fileName = "CBCencryptedMessage.txt";
		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    
		    
			
			for(int i=0;i<paddedMessageLen;i++) 
			{
				
				hex += String.format("%02x", (int) encryptedMessage[i]);
				writer.write(encryptedMessage[i]);
			}
			
			//System.out.println("Hexadecimal encrypted string:\n"+hex);
			
		    writer.close();
		    System.out.println("AES 128 bit Encryption Done");
		    
		}
	
	
	//ECB MODE****************
	
	
	
	
	if(ch==2)
	{
		
		System.out.print("Enter  plaintext: ");
		String message;
		message = cin.nextLine(); 
		cin.close();
		
		//System.out.println(message);
		
		int messageLen = message.length();
		int paddedMessageLen = messageLen;
		
		if( (paddedMessageLen%16)!=0 ) 
		{
			
			paddedMessageLen = ((paddedMessageLen/16)+1)*16;
		}
		
		char[] paddedMessage = new char[paddedMessageLen];
		
		for(int i=0;i<paddedMessageLen;i++) 
		{
			
			if(i>=messageLen) 
			{
				
				paddedMessage[i]=0;
			}
			
			else 
			{
				
				paddedMessage[i]=message.charAt(i);
			}
			
		}
		
		
		String keyStr="03040E030D030B0A090B070F0F06030C";
		
		char[] key=new char[16];
		
		for(int i=0,j=0;i<keyStr.length() && j<16 ;i+=2,j++) 
		{
			
			String str = keyStr.substring(i, i+2);
			key[j]=(char)Integer.parseInt(str, 16);
			
		}
		
		char[] expandedKey = new char[176];
		
		AES.KE.Expansion(key,expandedKey);
		
		//for(int i=0;i<176;i++) System.out.println(i+" "+expandedKey[i]);
		
		char[] encryptedMessage = new char[paddedMessageLen];
		
		
		for (int i = 0; i < paddedMessageLen; i += 16) 
		{
			
			char[] tempPaddedMessage = new char[16];
			char[] tempEncryptedMessage = new char[16];
			
			
			
			
			for(int j=0,l=i;j<16;j++,l++) 
			{
				
				tempPaddedMessage[j] = paddedMessage[l];
			}
			
			
			
			
			AES.AESEncrypt(tempPaddedMessage, expandedKey, tempEncryptedMessage);
			
			
			for(int j=0,l=i;j<16;j++,l++) 
			{
				encryptedMessage[l] = tempEncryptedMessage[j];
			}
					
				
		}
		
			
		String hex="\0";
		
		String fileName = "ECBencryptedMessage.txt";
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    
	    
		
		for(int i=0;i<paddedMessageLen;i++) 
		{
			
			hex += String.format("%02x", (int) encryptedMessage[i]);
			writer.write(encryptedMessage[i]);
		}
		
		//System.out.println("Hexadecimal encrypted string:\n"+hex);
		
	    writer.close();
	    System.out.println("AES 128 bit Encryption Done");
	    
	}
}
	
}