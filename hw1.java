import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

public class hw1 {
    public static void main(String[] args) {
        ExecutorService exService = Executors.newFixedThreadPool(8);
        long primeSum = 0;
        long primeCount = 0;
        long startTime;
        long endTime;
        int maxNum = (int) Math.pow(10, 8);
        boolean[] sieve = new boolean[maxNum + 1];
        int[] maxPrimes = new int[10];

        // START
        startTime = System.currentTimeMillis();

        // find primes using sieve of eratosthenes
        for (int i = 2; i <= Math.sqrt(maxNum); i++) {
           if (sieve[i] == false) exService.execute(new Ascend(sieve, i, maxNum));
        }

        // wait for threads to finish
        exService.shutdown();
        try {
            exService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // get total primes, sum of primes, and largest primes
        for (int i = maxNum; i >= 2 ; i--) {
            if(sieve[i] != false) continue;

            if(primeCount < 10) maxPrimes[(int) primeCount] = i;
        
            primeSum += i;
            primeCount++;
        }

        // END
        endTime = System.currentTimeMillis();

        try {
            File outFile = new File("primes.txt");
            outFile.createNewFile();
            FileWriter outFileWriter = new FileWriter("primes.txt");

            // Display results, (time / 1000) -> ms to s
            outFileWriter.write(((endTime - startTime) / 1000.0) + " " + primeCount + " " + primeSum);
            for (int i = maxPrimes.length - 1; i >= 0; i--)
                outFileWriter.write(Integer.toString(maxPrimes[i]) + "\n");

            outFileWriter.close();
        } 
        catch (IOException e) {
            System.out.println(e);
        }        
    }
}

class Ascend implements Runnable {
    boolean[] sieve;
    int i;
    int maxNum;

    public Ascend(boolean[] sieve, int i, int maxNum) {
        this.sieve = sieve;
        this.i = i;
        this.maxNum = maxNum;
    }

    public void run() {
        int base = i * i;
        for (int j = 0; base + i*j <= maxNum; j++) {
            sieve[base + i*j] = true;       
        }
    }
}
