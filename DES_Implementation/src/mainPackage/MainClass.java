package mainPackage;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 String text = "123456ABCD132536"; 
	        String key = "AABB09182736CCDD"; 
	  
	        DES des = new DES(); 
	        System.out.println("Encrypting..."); 
	        text = des.encrypt(text, key); 
	        System.out.println( 
	            "Cipher Text: " + text.toUpperCase() + "\n"); 
	        System.out.println("Decrypting..."); 
	        text = des.decrypt(text, key); 
	        System.out.println( 
	            "Plain Text: "
	            + text.toUpperCase()); 

	}

}
