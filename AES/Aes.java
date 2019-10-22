package AES;

public class Aes 
{
	
	
	KeyExpansion KE = new KeyExpansion();
	
	public void AddRoundKey(char[] state,char[] roundKey) 
	{
		
		
		for (int i = 0; i < 16; i++) 
		{
			state[i] ^= roundKey[i];
		}
		
	}

	
	
	public void SubBytes(char[] state) 
	{
		
		
		for (int i = 0; i < 16; i++) 
		{
			state[i] = KE.tb.sBox[state[i]];
		}
		
	}

	public void ShiftRows(char[] state) 
	{
		
		char[] tmp = new char[16];

		
		tmp[0] = state[0];
		tmp[1] = state[5];
		tmp[2] = state[10];
		tmp[3] = state[15];

		
		tmp[4] = state[4];
		tmp[5] = state[9];
		tmp[6] = state[14];
		tmp[7] = state[3];

		
		tmp[8] = state[8];
		tmp[9] = state[13];
		tmp[10] = state[2];
		tmp[11] = state[7];

		
		tmp[12] = state[12];
		tmp[13] = state[1];
		tmp[14] = state[6];
		tmp[15] = state[11];

		for (int i = 0; i < 16; i++) 
		{
			state[i] = tmp[i];
		}
	}

	 
	public void MixColumns(char[] state) 
	{
		
		char[] tmp=new char[16];

		tmp[0] = (char) (KE.tb.mul2[state[0]] ^ KE.tb.mul3[state[1]] ^ state[2] ^ state[3]);
		tmp[1] = (char) (state[0] ^ KE.tb.mul2[state[1]] ^ KE.tb.mul3[state[2]] ^ state[3]);
		tmp[2] = (char) (state[0] ^ state[1] ^ KE.tb.mul2[state[2]] ^ KE.tb.mul3[state[3]]);
		tmp[3] = (char) (KE.tb.mul3[state[0]] ^ state[1] ^ state[2] ^ KE.tb.mul2[state[3]]);

		tmp[4] = (char) (KE.tb.mul2[state[4]] ^ KE.tb.mul3[state[5]] ^ state[6] ^ state[7]);
		tmp[5] = (char) (state[4] ^ KE.tb.mul2[state[5]] ^ KE.tb.mul3[state[6]] ^ state[7]);
		tmp[6] = (char) (state[4] ^ state[5] ^ KE.tb.mul2[state[6]] ^ KE.tb.mul3[state[7]]);
		tmp[7] = (char) (KE.tb.mul3[state[4]] ^ state[5] ^ state[6] ^ KE.tb.mul2[state[7]]);

		tmp[8] = (char) (KE.tb.mul2[state[8]] ^ KE.tb.mul3[state[9]] ^ state[10] ^ state[11]);
		tmp[9] = (char) (state[8] ^ KE.tb.mul2[state[9]] ^ KE.tb.mul3[state[10]] ^ state[11]);
		tmp[10] = (char) (state[8] ^ state[9] ^ KE.tb.mul2[state[10]] ^ KE.tb.mul3[state[11]]);
		tmp[11] = (char) (KE.tb.mul3[state[8]] ^ state[9] ^ state[10] ^ KE.tb.mul2[state[11]]);

		tmp[12] = (char) (KE.tb.mul2[state[12]] ^ KE.tb.mul3[state[13]] ^ state[14] ^ state[15]);
		tmp[13] = (char) (state[12] ^ KE.tb.mul2[state[13]] ^ KE.tb.mul3[state[14]] ^ state[15]);
		tmp[14] = (char) (state[12] ^ state[13] ^ KE.tb.mul2[state[14]] ^ KE.tb.mul3[state[15]]);
		tmp[15] = (char) (KE.tb.mul3[state[12]] ^ state[13] ^ state[14] ^ KE.tb.mul2[state[15]]);

		for (int i = 0; i < 16; i++) 
		{
			state[i] = tmp[i];
		}
	}

	public void Round(char[] state, char[] key) 
	{
		SubBytes(state);
		ShiftRows(state);
		MixColumns(state);
		AddRoundKey(state, key);
	}

	public void FinalRound(char[] state,  char[] key) 
	{
		SubBytes(state);
		ShiftRows(state);
		AddRoundKey(state, key);
	}

	public void AESEncrypt(char[] message, char[] expandedKey, char[] encryptedMessage) 
	{
		
		char[] state = new char[16]; 

		for (int i = 0; i < 16; i++) 
		{
			
			state[i] = message[i];
		}

		int numberOfRounds = 9;

		AddRoundKey(state, expandedKey); // Initial round

		char[] tempChar = new char[16]; 
		
		for (int i = 0; i < numberOfRounds; i++) 
		{
			
			
			for(int j=0,l=(16*(i+1)) ; j<16 ; j++,l++) 
			{
				
				tempChar[j] = expandedKey[l];
			}
			
			Round(state, tempChar);
			
		}
		
		for(int j=0,l=160 ; j<16 ; j++,l++) 
		{
			
			tempChar[j] = expandedKey[l];
		}

		FinalRound(state, tempChar);

		for (int i = 0; i < 16; i++) 
		{
			encryptedMessage[i] = state[i];
		}
	}


}
