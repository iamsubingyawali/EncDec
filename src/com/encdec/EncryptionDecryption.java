package com.encdec;

public interface EncryptionDecryption {
    String encryption(String message, int[] randomNumbers);
    String decryption(String message, int[] randomNumbers);
}
