/*******************************************************************************
 * @Author(
 *  name = "Bob Su",
 *  date = "11/26/2017"
 * )
 *******************************************************************************
 * DESCRIPTION:
 * 
 * This program uses object-oriented programming style, efficiently performs
 * 3 types of encryption and decryption covered in the TEJ3M1 curriculum. It 
 * allows the user to choose between 3 encryption/decryption choices(ROT n, 
 * Vigenere, XOR), takes the plaintext/ciphertext and the key as user input, and
 * displays the encrypted/decrypted text.
 *******************************************************************************
 * ERROR HANDLING:
 * 
 * This program has its own custom exception type: Text Invalid Exception.
 * This program has error check for every user input to ensure that all inputs
 * are valid.
 *******************************************************************************
 * PROGRAM LIMITATIONS:
 * 
 * This program does not have a quit option, it can only be quitted manually.
 ******************************************************************************/

package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//**************************************************
//class Main:
//
//The driver class of the program.
//**************************************************
public class Main {
	//**************************************************************************
	// void main():
	//
	//The main method of this program.
	//**************************************************************************
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int method = 0, ende = 0;
        System.out.printf("Welcome to TEJ3M1 cryptography example: SmartCrypt by Bob SU\n");        
        // Get and check input: encrypt/decrypt method.
        do {
	        do {
	        	displayPrompt1();
	        	s = br.readLine();
	        	method = checkInt(s, "%s is not a valid response, please try again.\n");
	        	if(method <= 0 || method >= 4) System.out.println("Please choose from the choices given.");
	        }while(method <= 0 || method >= 4);
	        // Get and check input: encrypt or decrypt.
	        do {
	        	displayPrompt2();
	        	s = br.readLine();
	        	ende = checkInt(s, "%s is not a valid response, please try again.");
	        	if(ende <= 0 || ende >= 3) System.out.println("Please choose from the choices given.");
	        }while(ende <= 0 || ende >= 3);        
	        
	        if(method == 1) { // If using ROT n
	        	// Get and check input: plaintext/ciphertext.
	        	letterText text;
	        	int key;
	        	do {
	        		displayEnterTextPrompt(ende);
		        	s = br.readLine();
		        	text = (letterText)checkText(s, "The characters in the text must be in the alphabet to perform ROT n encrypt/decrypt");	        	
	        	}while(text == null);
	        	// Get and check user input: key.
	        	do {
	        		displayEnterKeyPrompt();
	        		s = br.readLine();
	        		key = checkInt(s, "The key for ROT n must be an integer.\n");
	        		if(key < 0) {
	        			System.out.println("The key for ROT n must be a positive integer.");
	        		}
	        	}while(key <= 0);
	        	key %= 26;
	        	// Encrypt or decrypt text as the user's choice.
	        	if(ende == 1) {
	        		s = text.rotEncrypt(key);
	        	}else if(ende == 2) {
	        		s = text.rotDecrypt(key);
	        	}
	        	displayResult(text, key, ende);
	        }else if(method == 2) { //
	        	letterText text, key;
	        	do {
	        		displayEnterTextPrompt(ende);
		        	s = br.readLine();
		        	text = (letterText)checkText(s, "The characters in the text must be in the alphabet to perform vigenere encrypt/decrypt");	        	
	        	}while(text == null);
	        	do {
	        		displayEnterKeyPrompt();
		        	s = br.readLine();
		        	key = (letterText)checkText(s, "The characters in the key must be in the alphabet to perform vigenere encrypt/decrypt");	        	
	        	}while(key == null);
	        	if(ende == 1) {
	        		s = text.vigEncrypt(key);
	        	}else if(ende == 2) {
	        		s = text.vigDecrypt(key);
	        	}
	        	displayResult(text, key, ende);
	        }else {
	        	XorText text, key;
	        	displayEnterTextPrompt(ende);
	        	s = br.readLine();
	        	text = new XorText(s);
	        	displayEnterKeyPrompt();
	        	s = br.readLine();
	        	key = new XorText(s);
	        	s = text.xor(key);
	        	displayResult(text, key, ende);
	        }
        }while(true);
    }
	//**************************************************************************
	// int checkInt(String in, String mes):
	//
	//Checks if the argument in is a valid integer, if it is, return the input,
	//else, print the argument mes as an error message and return 0.
	//**************************************************************************
	static int checkInt(String in, String mes) {
		try {
			int res = Integer.parseInt(in);
			return res;
		}catch(NumberFormatException e) {
			System.out.printf(mes, in);
			return 0;
		}
	}
	//**************************************************************************
	// Text checkText(String in, String mes):
	//
	//Checks if the argument in can be stored in a valid letterText, if it can,
	//store the input in a Text object and return it, else, print the argument
	// mes as an error message and return null.
	//**************************************************************************
	static Text checkText(String in, String mes) {
		try {
			Text t = new letterText(in);
			return t;
		}catch(TextInvalidException e) {
			System.out.println(mes);
			return null;
		}
	}
	//**************************************************************************
	// void displayPrompt1():
	//
	//Prompt the user to choose the method.
	//**************************************************************************
	static void displayPrompt1() {
		System.out.printf("------------------------------------------------------\n"
          		+ "Please choose an encrypt/decrypt method:\n"
          		+ "1. ROT n\n"
          		+ "2. Vigenere\n"
          		+ "3. XOR\n");
	}
	//**************************************************************************
	// void displayPrompt2():
	//
	//Prompt the user to choose to encrypt or decrypt.
	//**************************************************************************
	static void displayPrompt2() {
		System.out.printf("------------------------------------------------------\n"
        		+ "Please choose to encrypt or decrypt\n"
        		+ "1. encrypt\n"
        		+ "2. decrypt\n");
	}
	//**************************************************************************
	// void displayEnterTextPrompt():
	//
	//Prompt the user to enter text according to user's choice of encrypt or
	//decrypt.
	//**************************************************************************
	static void displayEnterTextPrompt(int a) {
		if(a == 1) {
    		System.out.println("Please enter your plaintext:");
    	}else if(a == 2) {
    		System.out.println("Please enter your ciphertext:");
    	}
	}
	//**************************************************************************
	// void displayEnterKeyPrompt():
	//
	//Prompt the user to enter key.
	//**************************************************************************
	static void displayEnterKeyPrompt() {
		System.out.println("Please enter your key:");
	}
	//**************************************************************************
	// void displayResult(Text t, int key, int a):
	//
	//Displays the data field text of Text argument t, int argument key, and the
	//data field result according to user's choice of encrypt or decrypt.
	//**************************************************************************
	static void displayResult(Text t, int key, int a) {
		if(a == 1) {
			System.out.printf("Your plaintext is %s\n", t.getText());
			System.out.printf("Your key is %d\n", key);
			System.out.printf("The ciphertext is %s\n", t.getResult());
		}else if(a == 2) {
			System.out.printf("Your ciphertext is %s\n", t.getText());
			System.out.printf("Your key is %d\n", key);
			System.out.printf("The plaintext is %s\n", t.getResult());
		}
	}
	//**************************************************************************
	// void displayResult(Text t, int key, int a):
	//
	//Displays the data field text of Text argument t, the data field text of
	//Text argument key, and the data field result of t according to user's
	//choice of encrypt or decrypt.
	//**************************************************************************
	static void displayResult(Text t, Text key, int a) {
		if(a == 1) {
			System.out.printf("Your plaintext is %s\n", t.getText());
			System.out.printf("Your key is %s\n", key.getText());
			System.out.printf("The ciphertext is %s\n", t.getResult());
		}else if(a == 2) {
			System.out.printf("Your ciphertext is %s\n", t.getText());
			System.out.printf("Your key is %s\n", key.getText());
			System.out.printf("The plaintext is %s\n", t.getResult());
		}
	}
	
	
}
