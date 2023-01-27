## Programming Assignment 1

### Instructions

1. Open command line in project root
2. Enter the commands:
   - `javac hw1.java`
   - `java hw1`
3. Open `primes.txt` to see program output

### Explanation of Program

To find primes, the Sieve of Eratosthenes is used. The implementation of the sieve is as follows:

1. Create a boolean array of size n + 1, where n is upper bounds of numbers to asses. Set each element in the array to false;

1. For each element in the array from index i in range 2 to n:
   1. If element at i is false:
      1. Let base = i^2 and j = 0
      2. While base + i * j <= n:
         1. Set value at base + i* j to true
         2. Increment j
2. For each element in the array from index i in range 2 to n:
   1. If the element is true, the index of the array is prime.

To introduce parallelization to the program, I used java's ExecutorService which provides a method to manage thread pools. The section after the statement "If element at i is false:" was run in parallel using the ExecutorService.

Using a brute-force method the runtime on my machine was around 20 seconds. For the sieve approach the runtime on my machine was around .45 seconds. Due to the large speed up from the sieve, I believe this program has adequate efficacy.

To verify correctness of the program, I used <https://adding.info/sum-prime-numbers/sum-of-prime-numbers-calculator.html>. This website provides primes and sums of primes. From this I was able to create simple test cases.
