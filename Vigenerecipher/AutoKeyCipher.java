/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vigenerecipher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author budoo
 */
public class AutoKeyCipher {

  public static Scanner in = new Scanner(System.in);
           
    public static String encryption(String plainText, String keyPhrase) {
        String cipherText = "";
        plainText = plainText.toUpperCase();
        keyPhrase = keyPhrase.toUpperCase();
        String setOfAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        try {
            FileReader fileReader = new FileReader(plainText);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter writer = new FileWriter((plainText + "_encrypted"), false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
// encrypt code
            for (int i = 0; i < plainText.length(); i++) {
                int cipher = (keyPhrase.charAt(i) + plainText.charAt(i)) % 26;
	    cipherText = cipherText + setOfAlphabets.charAt(cipher);
            }
            bufferedWriter.close();
            bufferedReader.close();
            System.out.println();
            System.out.println("Done encrypting!");

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + plainText + " not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    public static String decryption(String cipherText, String keyPhrase) {
        String plainText = "";
        cipherText = cipherText.toUpperCase();
        keyPhrase = keyPhrase.toUpperCase();
        String setOfAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        try {
            FileReader fileReader = new FileReader(cipherText);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter writer = new FileWriter((cipherText + "_decrypted"), false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
// decrypt code 
            for (int i = 0; i < cipherText.length(); i++) {

                int text = (cipherText.charAt(i) - keyPhrase.charAt(i)) % 26;
			if (text < 0)
				text = text + 26;

			plainText = plainText + setOfAlphabets.charAt(text);
            }
            bufferedWriter.close();
            bufferedReader.close();
            System.out.println();
            System.out.println("Done Decrypting!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + cipherText + " not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return plainText;
    }
    
    
 
}
