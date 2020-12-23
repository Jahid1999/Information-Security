package mainPackage;

public class Round {
	
	Keys KEYS = new Keys();
	
	public String round(String input, String key, int num) 
    { 
        String left = input.substring(0, 8); 
        String temp = input.substring(8, 16); 
        String right = temp; 
        
        temp = KEYS.permutation(KEYS.tab.EP, temp); 
        temp = xor(temp, key); 
        temp = sBox(temp); 
        
        temp = KEYS.permutation(KEYS.tab.P, temp); 
        left = xor(left, temp); 
        
        System.out.println("Round "
                           + (num + 1) + " "
                           + right.toUpperCase() 
                           + " " + left.toUpperCase() + " "
                           + key.toUpperCase()); 

        return right + left; 
    } 
	
	public String sBox(String input) 
    { 
        String output = ""; 
        input = KEYS.hextoBin(input); 
        for (int i = 0; i < 48; i += 6) { 
            String temp = input.substring(i, i + 6); 
            int num = i / 6; 
            int row = Integer.parseInt( 
                temp.charAt(0) + "" + temp.charAt(5), 2); 
            int col = Integer.parseInt( 
                temp.substring(1, 5), 2); 
            output += Integer.toHexString( 
            		KEYS.tab.sbox[num][row][col]); 
        } 
        return output; 
    } 
	
	public String xor(String a, String b) 
    { 
        long t_a = Long.parseUnsignedLong(a, 16); 
        long t_b = Long.parseUnsignedLong(b, 16); 
        t_a = t_a ^ t_b; 
        a = Long.toHexString(t_a); 
        while (a.length() < b.length()) 
            a = "0" + a; 
        return a; 
    } 

}
