package com.encdec;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {
        //  Generating a random number using RandomGenerator class
        int[] randomNumber = new RandomGenerator().generateRandomNumbers(8);
        //  creating new EncryptDecrypt object to perform encryption and decryption
        EncryptDecrypt encDec = new EncryptDecrypt("A1B2", "END");

        //  Reading text to encrypt from "originalText.txt" file
        String textFromFile = readTextFile("originalText.txt");

        //  Encrypting the read text and saving into "encryptedMessage.txt" file
        String encMsg = encDec.encryption(textFromFile, randomNumber);
        if(saveToFile("encryptedMessage.txt", encMsg)){
            //  Reading the encrypted file and showing decrypted message on terminal
            String encryptedText = readTextFile("encryptedMessage.txt");
            System.out.println("Decrypted Text: "+encDec.decryption(encryptedText, randomNumber));
        }
    }

    //  method to read text from a file
    public static String readTextFile(String filePath){
        //  handling file exceptions
        try {
            //  using file reader to read the file from the system
            FileReader file = new FileReader(filePath);
            int i;
            String textFromFile = "";
            while ((i=file.read()) != -1)
                textFromFile += (char) i;

            //  returning the text from file
            return textFromFile;
        }
        catch (Exception e){
            System.out.println("File not found. Please ensure that selected file exists.");
            return "";
        }
    }

    //  method to save text to a file
    public static boolean saveToFile(String filePath, String text){
        //  handling file exceptions
        try {
            //  Creating a new text file to store the text
            File newTextFile = new File(filePath);
            newTextFile.createNewFile();
            //  using file writer to write into the file
            FileWriter writer = new FileWriter(filePath);
            writer.write(text);
            writer.close();
            return true;
        }
        catch (Exception e){
            System.out.println("Error saving into file. Please try again.");
            return false;
        }
    }
}
