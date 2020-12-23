package mainPackage;

public class Keys {
	Tables tab = new Tables();
	
	public String[] getKeys(String key) 
    { 
        String keys[] = new String[16]; 
        key = permutation(tab.PC1, key); 
        for (int i = 0; i < 16; i++) { 
            key = leftCircularShift( 
                      key.substring(0, 7), tab.shiftBits[i]) 
                  + leftCircularShift(key.substring(7, 14), 
                		  tab.shiftBits[i]); 
            keys[i] = permutation(tab.PC2, key); 
        } 
        return keys; 
    } 
	
	public String permutation(int[] sequence, String input) 
    { 
        String output = ""; 
        input = hextoBin(input); 
        for (int i = 0; i < sequence.length; i++) 
            output += input.charAt(sequence[i] - 1); 
        output = binToHex(output); 
        return output; 
    } 
	
	public String hextoBin(String input) 
    { 
        int n = input.length() * 4; 
        input = Long.toBinaryString( 
            Long.parseUnsignedLong(input, 16)); 
        while (input.length() < n) 
            input = "0" + input; 
        return input; 
    } 

    public String binToHex(String input) 
    { 
        int n = (int)input.length() / 4; 
        input = Long.toHexString( 
            Long.parseUnsignedLong(input, 2)); 
        while (input.length() < n) 
            input = "0" + input; 
        return input; 
    } 
    
    public String leftCircularShift(String input, int numBits) 
    { 
        int n = input.length() * 4; 
        int perm[] = new int[n]; 
        for (int i = 0; i < n - 1; i++) 
            perm[i] = (i + 2); 
        perm[n - 1] = 1; 
        while (numBits-- > 0) 
            input = permutation(perm, input); 
        return input; 
    } 


}
