package com.encdec;

public class EncryptDecrypt implements EncryptionDecryption{

    //  Defining two variables to store the supplies header and trailer message globally
    String headerMsg;
    String trailerMsg;
    //  Defining two variables to store the alphabets and number for shifting
    String alphabets;
    String numbers;

    //  parameterized constructor
    //  takes header and trailer message as arguments
    public EncryptDecrypt(String headerMsg, String trailerMsg){
        this.headerMsg = headerMsg;
        this.trailerMsg = trailerMsg;
        //  assigning alphabets from a to z
        this.alphabets = "abcdefghijklmnopqrstuvwxyz";
        //  assigning numbers from 0 to 9
        this.numbers = "0123456789";
    }

    //  method to check if a supplied string is an integer
    public static boolean isInteger(String value){
        try{
            int number = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    //  Implemented methods from EncryptionDecryption interface
    //  method to perform encryption operation
    @Override
    public String encryption(String message, int[] randomNumbers) {
        //  converting message into lowercase
        message = message.toLowerCase();
        //  Initializing a variable to store the cipher
        String cipher = "";
        //  looping over the supplied message to analyze each character
        for (int i = 0; i < message.length(); i++) {
            //  getting single character at current index
            char character = message.charAt(i);
            //  checking if the character in current index is an integer
            if (isInteger(""+character)){
                // Getting the index of the integer and shifting
                int originalPos = numbers.indexOf(character);
                int shiftedPos = (randomNumbers[0] + originalPos) % 10;
                //  Getting new integer after shifting and appending to the cipher
                char newChar = numbers.charAt(shiftedPos);
                cipher = cipher + newChar;
            }
            //  checking if the character is not an space or any special character
            else if (character != ' ' && alphabets.indexOf(character) != -1) {
                // Getting the index of the integer and shifting
                int originalPos = alphabets.indexOf(character);
                int shiftedPos = (randomNumbers[0] + originalPos) % 26;
                //  Getting new integer after shifting and appending to the cipher
                char newChar = alphabets.charAt(shiftedPos);
                cipher = cipher + newChar;
            }
            //  appending the characters as it as if they are not numbers or alphabets
            else {
                cipher = cipher + character;
            }
        }
        //  creating mutable string builder object to reverse the string
        StringBuilder reverseCipher = new StringBuilder();
        reverseCipher.append(cipher);
        reverseCipher.reverse();
        return headerMsg+reverseCipher+trailerMsg;
    }

    //    method to perform decryption operation
    @Override
    public String decryption(String cipher, int[] randomNumbers) {
        //  converting cipher text into lowercase
        cipher = cipher.toLowerCase();
        //  Getting back the original order of the cipher by reversing the string with string builder
        StringBuilder orderedText = new StringBuilder();
        orderedText.append(cipher);
        cipher = orderedText.reverse().toString();
        //  Stripping the header and trailer message
        cipher = cipher.substring(3, cipher.length()-4);
        //  Initializing a variable to store the cipher
        String message = "";
        //  looping over the supplied cipher to analyze each character
        for (int i = 0; i < cipher.length(); i++) {
            //  getting single character at current index
            char character = cipher.charAt(i);
            //  checking if the character in current index is an integer
            if (isInteger(""+character)){
                // Getting the index of the integer and shifting
                int originalPos = numbers.indexOf(character);
                int shiftedPos = ( originalPos - randomNumbers[0]) % 10;
                //  handling negative values
                if (shiftedPos < 0) { shiftedPos = numbers.length() + shiftedPos; }
                //  Getting new integer after shifting and appending to the plain text message
                char newChar = numbers.charAt(shiftedPos);
                message = message + newChar;
            }
            //  checking if the character is not an space or any special character
            else if (character != ' ' && alphabets.indexOf(character) != -1) {
                // Getting the index of the integer and shifting
                int originalPos = alphabets.indexOf(character);
                int shiftedPos = ( originalPos - randomNumbers[0]) % 26;
                //  handling negative values
                if (shiftedPos < 0) { shiftedPos = alphabets.length() + shiftedPos; }
                //  Getting new integer after shifting and appending to the plain text message
                char newChar = alphabets.charAt(shiftedPos);
                message = message + newChar;
            }
            //  appending the characters as it as if they are not numbers or alphabets
            else {
                message = message + character;
            }
        }
        return message;
    }
}
