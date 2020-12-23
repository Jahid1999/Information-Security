package mainPackage;

public class DES {
	
	Tables tab = new Tables();
	Keys KEYS = new Keys();
	Round rund = new Round();
	
	public String encrypt(String plainText, String key) 
    { 
        int i; 
        String keys[] = KEYS.getKeys(key); 

        plainText = KEYS.permutation(tab.IP, plainText); 
        System.out.println( 
            "After initial permutation: "
            + plainText.toUpperCase()); 
        System.out.println( 
            "After splitting: L0="
            + plainText.substring(0, 8).toUpperCase() 
            + " R0="
            + plainText.substring(8, 16).toUpperCase() + "\n"); 

        // 16 rounds 
        for (i = 0; i < 16; i++) { 
            plainText = rund.round(plainText, keys[i], i); 
        } 

        plainText = plainText.substring(8, 16) 
                    + plainText.substring(0, 8); 

        plainText = KEYS.permutation(tab.IP1, plainText); 
        return plainText; 
    } 

//    public String decrypt(String plainText, String key) 
//    { 
//        int i; 
//        // get round keys 
//        String keys[] = getKeys(key); 
//
//        // initial permutation 
//        plainText = permutation(tab.IP, plainText); 
//        System.out.println( 
//            "After initial permutation: "
//            + plainText.toUpperCase()); 
//        System.out.println( 
//            "After splitting: L0="
//            + plainText.substring(0, 8).toUpperCase() 
//            + " R0=" + plainText.substring(8, 16).toUpperCase() 
//            + "\n"); 
//
//        // 16-rounds 
//        for (i = 15; i > -1; i--) { 
//            plainText = round(plainText, keys[i], 15 - i); 
//        } 
//
//        // 32-bit swap 
//        plainText = plainText.substring(8, 16) 
//                    + plainText.substring(0, 8); 
//        plainText = permutation(tab.IP1, plainText); 
//        return plainText; 
//    } 

}
