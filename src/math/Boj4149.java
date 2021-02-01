package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author exponential-e
 * 백준 4149번: 큰 수 소인수 분해
 *
 * @see https://www.acmicpc.net/problem/4149
 * Can't solved by Java, ref comments
 *
 */
public class Boj4149 {
	private static final String NEW_LINE = "\n";
	private static final long[] FACTORS = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		System.out.print(getPrimeFactor(N));
	}
	
	private static StringBuilder getPrimeFactor(long n) {
		StringBuilder sb = new StringBuilder();
		ArrayList<Long> result = new ArrayList<>();

		while(n > 1) {
			long r = pollardRho(n);
			result.add(r);
			n /= r;
		}

		Collections.sort(result);
		for(long r: result) {
			sb.append(r).append(NEW_LINE);
		}
		
		return sb;
	}

	private static long gcd(long a, long b) {
		while(b != 0) {
			long mod = a % b;
			a = b;
			b = mod;
		}

		return a;
	}

	private static boolean isPrime(long n) {
		for(final long FACTOR: FACTORS) {
			if(FACTOR == n) return true;
			if(n > 40 && !millerRabin(n, FACTOR)) return false;
		}

		if(n <= 40) return false;
		return true;
	}

	private static boolean millerRabin(long n, long a) {
		long remain = 0;
		long div = n - 1;

		while(div % 2 == 0) {
			remain += 1;
			div >>= 1;
		}

		long x = pow(a, div, n);
		if(x == 1 || x == n - 1) return true;

		for(int i = 0; i < remain - 1; i++) {
			x = pow(x, 2, n);
			if(x == n - 1) return true;
		}

		return false;
	}

	private static long pow (long x, long y, long p) {
		long result = 1L;
		x %= p;

		while(y > 0) {
			if ((y & 1) == 1) result = ((result % p) * (x % p)) % p;
			y >>= 1;
			x = ((x % p) * (x % p)) % p;
		}

		return result;
	}

	/**
	 *
	 * PollardRho prime specification
	 *
	 * line 109 ~ 114: Long overflow, if use BigInteger then get OOM
	 *
	 * @param n
	 * @return
	 */
	private static long pollardRho(long n) {
		if(isPrime(n)) return n;

		if(n == 1) return 1;
		if(n % 2 == 0) return 2;

		long x = getRandomNumberInRange(2, n);
		long y = x;
		long carry = getRandomNumberInRange(1, n);
		long delay = 1;

		while(delay == 1) {
			x = cal(x, n, carry);
			y = cal(y, n, carry);
			y = cal(y, n, carry);
			delay = gcd(Math.abs(x - y), n);
		}

		if(!isPrime(delay)) return pollardRho(delay);
		return delay;
	}

	private static long cal(long a, long n, long c) {
		return (((a % n) * (a % n)) + c) % n;
	}

	private static long getRandomNumberInRange(long min, long max) {
		return (long) (Math.random() * ((max - min) + 1) + min);
	}
}

/**
 *
 * Solved by python3
 *
 * import sys
 * import random
 * sys.setrecursionlimit(10 ** 5)
 *
 * def gcd(a, b):
 *     while b != 0:
 *         r = a % b
 *         a = b
 *         b = r
 *
 *     return a
 *
 * def pow(x, y, p):
 *     result = 1
 *     x = x % p
 *
 *     while y > 0:
 *         if y & 1:
 *             result = (result * x) % p
 *
 *         y >>= 1
 *         x = (x * x) % p
 *
 *     return result
 *
 * def miller_rabin(n, a):
 *         remain = 0
 *         div = n - 1
 *
 *         while div % 2 == 0:
 *             remain += 1
 *             div >>= 1
 *
 *         x = pow(a, div, n)
 *         if x == 1 or x == n - 1:
 *             return True
 *
 *         for i in range(remain - 1):
 *             x = pow(x, 2, n)
 *
 *             if x == n - 1:
 *                 return True
 *
 *         return False
 *
 * def is_prime(n):
 *     factors = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]
 *
 *     for f in factors:
 *         if n == f:
 *             return True
 *         if not miller_rabin(n, f) and n > 40:
 *             return False
 *
 *     if n <= 40:
 *     	return False
 *     return True
 *
 *
 * def pollard_rho(n):
 *     if is_prime(n):
 *         return n
 *     if n == 1:
 *         return 1
 *     if n % 2 == 0:
 *         return 2
 *
 *     x = random.randrange(2, n)
 *     y = x
 *     carry = random.randrange(1, n)
 *     delay = 1
 *     while delay == 1:
 *         x = ((x * x % n) + carry) % n
 *         y = ((y * y % n) + carry) % n
 *         y = ((y * y % n) + carry) % n
 *         delay = gcd(abs(x - y), n)
 *
 *     if is_prime(delay):
 *         return delay
 *     else:
 *         return pollard_rho(delay)
 *
 * n = int(input())
 *
 * primes = []
 * while n > 1:
 *     r = pollard_rho(n)
 *     primes.append(r)
 *     n = n // r
 *
 * primes.sort()
 *
 * for i in primes:
 *     print(i)
 */
