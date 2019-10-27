package AES;

public class KeyExpansion 
{
	
	Tables tb= new Tables();
	
	public void KeyExpansionCore(char[] in, int rconIteration) 
	{
		
		//left  shift  
		char t = in[0];
		in[0] = in[1];
		in[1] = in[2];
		in[2] = in[3];
		in[3] = t;

		// S-box  
		in[0] = tb.sBox[in[0]];
		in[1] = tb.sBox[in[1]];
		in[2] = tb.sBox[in[2]];
		in[3] = tb.sBox[in[3]];

		// RCon
		in[0] ^= tb.rcon[rconIteration];
	}
	
	
	
	public void Expansion(char[] inputKey, char[] expandedKeys) 
	{
		
		for (int i = 0; i < 16; i++) 
		{
			expandedKeys[i] = inputKey[i];
		}

		int bytesGenerated = 16;
		int rconIteration = 1;
		char[] tmpCore = new char[4];

		while (bytesGenerated < 176) 
		{
			for (int i = 0; i < 4; i++) 
			{
				tmpCore[i] = expandedKeys[i + bytesGenerated - 4];
			}

			if (bytesGenerated % 16 == 0) 
			{
				KeyExpansionCore(tmpCore, rconIteration++);
			}

			for (char a = 0; a < 4; a++) 
			{
				expandedKeys[bytesGenerated] = (char) (expandedKeys[bytesGenerated - 16] ^ tmpCore[a]);
				bytesGenerated++;
			}

		}
	}

	
	
}