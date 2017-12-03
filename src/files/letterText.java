package files;
//*******************************************************************************
// class letterText:
//
//The class for the object letterText, which is a subclass of the abstract class
//Text. It can store a string only consists of spaces and letters in the English 
//alphabet, and perform ROT n and Vigenere encryption/decryption with the text
//and a key.
//*******************************************************************************
public class letterText extends Text{
	
	private String text, result;
	//***************************************************************************
	// String getText():
	//
	//The get method of the data field text.
	//***************************************************************************
	public String getText() {
		return text;
	}
	//***************************************************************************
	// String getResult():
	//
	//The get method of the data field result.
	//***************************************************************************
	public String getResult() {
		return result;
	}
	//***************************************************************************
	// letterText(String t):
	//
	//The constructor of this class, it takes a string argument and checks if
	//it can be stored in a valid letterText, if it can, convert it into uppercase
	//and store the string into the data field text, else, throw 
	//TextInvalidException
	//***************************************************************************
	public letterText(String t) throws TextInvalidException{
		for(int i = 0; i < t.length(); i ++) {
			char c = t.charAt(i);
			if((c < 65 || c > 90) && ( c < 97 || c > 122) && c != 32) {
				throw new TextInvalidException("The characters in the plaintext must be in the alphabet to encrypt");
			}
		}
		text = t.toUpperCase();
	}
	//***************************************************************************
	// String rotEncrypt(int key):
	//
	//Takes a int argument key and performs ROT n encryption with the data
	//field text and the argument key, stores the result into the data field 
	//result, and returns the result.
	//***************************************************************************
	public String rotEncrypt(int key){
		String res = "";
		for(int i = 0; i < text.length(); i ++) {
			if(text.charAt(i) != ' ') { // If the character is not ' ',
				int c = text.charAt(i) + key; // Shift the character right by key in the ASCII chart.
				res = res + (char)((c>90)?(c-26):c); // If the character goes out of bound of the alphabet, subtract it by 26.
			}else { // If the character is ' ',
				res = res + " "; // Do not convert it.
			}
		}
		result = res;
		return res;
	}
	//***************************************************************************
	// String rotDecrypt(int key):
	//
	//Takes a int argument key and performs ROT n decryption with the data
	//field text and the argument key, stores the result into the data field 
	//result, and returns the result.
	//***************************************************************************
	public String rotDecrypt(int key){
		String res = "";
		for(int i = 0; i < text.length(); i ++) {
			if(text.charAt(i) != ' ') {
				int c = text.charAt(i) - key; // Shift the character left by key in the ASCII chart.
				res = res + (char)((c<65)?(c+26):c); // If the character goes out of bound of the alphabet, add it by 26.
			}else {
				res = res + " ";
			}
		}
		result = res;
		return res;
	}
	//***************************************************************************
	// String vigEncrypt(Text key):
	//
	//Takes a Text argument key and performs vigenere encryption with the data
	//field text and the argument key, stores the result into the data field 
	//result, and returns the result.
	//***************************************************************************
	public String vigEncrypt(Text key) {
		String s = key.getText();
		while(s.length() < text.length()) {
			s = s + s; // If key is shorter than text, repeat the key. 
		}
		String res = "";
		for(int i = 0; i < text.length(); i ++) {
			if(text.charAt(i) != ' ') {
				char c = (char)(text.charAt(i) + s.charAt(i) - 65); // The resultant character equals the plaintext character plus the key character minus 'A'.
				res = res + (char)((c > 90)?(c-26):c);
			}else {
				res = res + " ";
			}
		}
		result = res;
		return res;
	}
	//***************************************************************************
	// String vigDecrypt(Text key):
	//
	//Takes a Text argument key and performs vigenere decryption with the data
	//field text and the argument key, stores the result into the data field 
	//result, and returns the result.
	//***************************************************************************
	public String vigDecrypt(Text key) {
		String s = key.getText();
		while(s.length() < text.length()) {
			s = s + s;
		}
		String res = "";
		for(int i = 0; i < text.length(); i ++) {
			if(text.charAt(i) != ' ') {
				char c = (char)(text.charAt(i) + 65 - s.charAt(i)); // The resultant character equals the ciphertext character plus 'A' minus the key character.
				res = res + (char)((c < 65)?(c+26):c);
			}else {
				res = res + " ";
			}
		}
		result = res;
		return res;
	}
}
