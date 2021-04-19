package com.encdec;

import java.util.Random;

public class RandomGenerator {

    //  method to generate random number
    //  takes seed value as an argument and returns a random number
    public int[] generateRandomNumbers(int seed){
        //  Initializing new random object
        Random rnd = new Random();
        //  setting the seed value of the random number generation
        rnd.setSeed(seed);
        //  generating new random number with max value 100 (exclusive)
        int randomNumber = rnd.nextInt(100);
        // appending the generated number into an integer array and returning the array
        int randArray[] = new int[]{randomNumber};
        return randArray;
    }
}
