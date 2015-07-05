package hunterscode.util;

import java.util.ArrayList;
import java.util.List;

public class Primes {

    public static void main(String [] args) {
        //printPrimes(Integer.parseInt(args[0]));
        printPrimesWithSieve(Integer.parseInt(args[0]));
    }

    //first try, not knowing anything about sieves
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

    //can calculate primes up to 10001 using a Sieve of Eratosthenes
    public static void printPrimesWithSieve(int numberOfPrimes) {
        byte [] possiblePrimes = new byte[10000];

        //start with first possible prime
        for (int i = 2; i < 10000; ++i) {

            //already marked as not prime so move on
            if (possiblePrimes[i - 2] == 1) continue;
            
            for (int x = i * 2; x < 10000; x += i) {
                    possiblePrimes[x - 2] = 1;
            }
        }

        int primeCounter = 1;
        for (int i = 2; i < 10000; ++i) {
            if (primeCounter > numberOfPrimes) return;
            
            if (possiblePrimes[i - 2] == 0) {
                
                if (primeCounter == numberOfPrimes) {
                    System.out.println(i);                    
                }
                else {
                    System.out.print(i + ", ");    
                }
                primeCounter++;
            }            
        }
    }
}