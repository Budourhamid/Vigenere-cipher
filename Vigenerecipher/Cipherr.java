/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vigenerecipher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author budoo
 */
public class Cipherr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Scanner scanner = new Scanner(System.in);
        int function;
        String inputFile;
        String keyword;
        int command;
        System.out.println("What is the input text file?");
        inputFile = scanner.next();
        System.out.println("Do you want to 1. Encrypt or 2. Decrypt?");
        function = scanner.nextInt();
        System.out.println("What is the keyword?");
        keyword = scanner.next();
        System.out.println("Do you want your keyword to 1. reoeated or 2. Auto key mode?");
        command = scanner.nextInt();

        //auto key
        FileReader plaintextFile = new FileReader(inputFile); //error
        BufferedReader readPlaintextFile = new BufferedReader(plaintextFile);
        String plaintext = readPlaintextFile.readLine();
        readPlaintextFile.close();
        scanner.close();
        switch (command) {
            case 1:

                // for vigenere cipher
                VigenereCipher vigenereCipher = new VigenereCipher(inputFile, keyword);

                vigenereCipher.doCipher(function);
                break;

            case 2:
                //  Auto key
                // append key and plaintext to match number of characters in both
                String keyText = "deceptive";
                String key = keyword + plaintext;
                int match = key.length() - keyword.length();
                key = key.substring(0, match);
                if (isAlpha(plaintext)) {
                    switch (function) {
                        case 1:
                            encrypt(key, plaintext);
                            System.out.println("Done encrypting");
                            break;
                        case 2:
                            decrypt(key, plaintext);
                            System.out.println("Done decrypting");
                            break;
                        default:
                            System.out.println("Error: That function is not defined.");
                            break;
                    }
                } else {
                    System.out.println(inputFile + " \nAll the characters should be Alphabets");
                }
                break;
        }
    }

    // Auto key 
    public static boolean isAlpha(String plaintext) {
        return plaintext.matches("[a-zA-Z]+");
    }

    private static String encrypt(String key, String plaintext) throws IOException {
        key = key.toUpperCase();
        plaintext = plaintext.toUpperCase();
        System.out.println(plaintext);
        System.out.println(key);
        String setOfAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cipherText = "";
        
        for (int i = 0; i < plaintext.length(); i++) {
            int cipher = (key.charAt(i) + plaintext.charAt(i)) % 26;
            cipherText = cipherText + setOfAlphabets.charAt(cipher);
        }
        FileWriter writeCipher = new FileWriter(plaintext+ "_encrypted.txt");
        BufferedWriter c = new BufferedWriter(writeCipher);
        c.write(cipherText);
        c.close();
        return cipherText;

    }

    private static String decrypt(String key, String cipherText) throws IOException {
        key = key.toUpperCase();
        String setOfAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            int text = (cipherText.charAt(i) - key.charAt(i)) % 26;
            if (text < 0) {
                text = text + 26;
            }

            plainText = plainText + setOfAlphabets.charAt(text);
        }
        FileWriter writePlainText = new FileWriter(cipherText+ "_Decrypted.txt");
        BufferedWriter c = new BufferedWriter(writePlainText);
        c.write(plainText);
        c.close();
        return plainText;
    }
}
