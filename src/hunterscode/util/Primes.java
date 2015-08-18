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
    public static void printPrimesWithSieve(int number) {
        byte [] possiblePrimes = new byte[10000];
        int i = 2;
        
        //start with first possible prime, which is i = 2
        while (true) {
            boolean keepGoing = false;
            
            for (int x = i * 2; x < 10000; x += i) {
                possiblePrimes[x - 2] = 1;
            }

            for (int k = i + 1; k < 10000; ++k) {
                if (possiblePrimes[k - 2] == 0) {
                    i = k;
                    keepGoing = true;
                    break;
                }
            }

            if (!keepGoing)
                break;
        }
        
        if (number < 2) {
            System.out.println("");
            return;
        }
        
        StringBuffer result = new StringBuffer();
        
        for (i = 2; i <= number; ++i) {
            if (possiblePrimes[i - 2] == 0) {
                
                result.append(i + ", ");    
            }            
        }
        
        System.out.println(result.toString().substring(0, result.length() - 2));
    }
}