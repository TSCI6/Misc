package com.tanmey.Duke.ArraysListsStructuredData.Week1 ;

import edu.duke.*;

public class Part1CaeserCipher {
	public static void main (String args[])
	{
		Part1CaeserCipher main =new Part1CaeserCipher();
		main.testCaesar();
		main.testEncryptTwoKeys();
	}
    public String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        StringBuilder encryptedMessage = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            int index = alphabet.toLowerCase().indexOf(Character.toLowerCase(currentCharacter));
            
            if (index != -1) {
                if (Character.isLowerCase(currentCharacter)) {
                    encryptedMessage.append(Character.toLowerCase(shiftedAlphabet.charAt(index)));                
                } else {
                    encryptedMessage.append(shiftedAlphabet.charAt(index));
                }
            } else {
                encryptedMessage.append(currentCharacter);
            }
        }
        
        return encryptedMessage.toString();
    }
    
    public void testCaesar() {
       
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key = 15;
        
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String firstShiftedAlphabet = alphabet.substring(key1) + alphabet.substring(0, key1);
        String secondShiftedAlphabet = alphabet.substring(key2) + alphabet.substring(0, key2);
        StringBuilder encryptedMessage = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            int index = alphabet.toLowerCase().indexOf(Character.toLowerCase(currentCharacter));
            
            if (index != -1) {
                String shiftedAlphabet;
                
                if (i % 2 == 0) {
                    shiftedAlphabet = firstShiftedAlphabet;
                } else {
                    shiftedAlphabet = secondShiftedAlphabet;
                }
                
                if (Character.isLowerCase(currentCharacter)) {
                    encryptedMessage.append(Character.toLowerCase(shiftedAlphabet.charAt(index)));                
                } else {
                    encryptedMessage.append(shiftedAlphabet.charAt(index));
                }
            } else {
                encryptedMessage.append(currentCharacter);
            }
        }
        
        return encryptedMessage.toString();
    }
    
    public void testEncryptTwoKeys() {
        int key1 = 12;
        int key2 = 2;
        String   message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        System.out.println(message + " -> " + encryptTwoKeys(message, key1, key2));
    }
}
