package files;
//*******************************************************************************
// class XorText:
//
//The class for the object XorText, which is a subclass of the abstract class
//Text. It can store a string, and perform XOR encryption/decryption with the text
//and a key.
//*******************************************************************************
public class XorText extends Text{
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
	// XorText(String t):
	//
	//The constructor of this class. It takes a string argument t and stores it
	//in the data field text.
	//***************************************************************************
	public XorText(String t){
		text = t;
	}
	//***************************************************************************
	// String xor(XorText key):
	//
	//Takes a XorText argument key and performs XOR encryption/decryption with
	//the data field text and the argument key, stores the result into the data 
	//field result, and returns the result.
	//***************************************************************************
	public String xor(XorText key) {
		String s = key.getText();
		while(s.length() < text.length()) {
			s = s + s; // If key is shorter than text, repeat the key.
		}
		String res = "";
		for(int i = 0; i < text.length(); i ++) {
			res = res + (char)(text.charAt(i) ^ s.charAt(i)); // XOR the text to the key.
		}
		result = res;
		return res;
	}
}
