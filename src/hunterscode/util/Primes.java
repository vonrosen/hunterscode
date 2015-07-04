package hunterscode.util;

import java.util.ArrayList;
import java.util.List;

public class Primes {

    public static void main(String [] args) {
        printPrimes(1000);
    }
    
    public static void printPrimes(int numberOfPrimes) {
        int possiblePrime = 2;
        int primeCounter = 1;
        List<Integer> previousPrimes = new ArrayList<Integer>();
        
        while (true) {
            if (possiblePrime == 2) {
                System.out.println(possiblePrime);
                primeCounter++;
                possiblePrime++;
                continue;
            }
            
            boolean prime = true;
            for (int previousPrime : previousPrimes) {
                if ((possiblePrime % previousPrime) == 0) {
                    prime = false;
                    break;
                }
            }
            
            if (prime) {
                System.out.println(possiblePrime);
                previousPrimes.add(possiblePrime);
                primeCounter++;
            }

            possiblePrime += 2;
            if (primeCounter > numberOfPrimes) return;
        }
    }    
}