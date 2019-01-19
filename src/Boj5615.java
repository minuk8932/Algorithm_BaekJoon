import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Boj5615 {
	private static final int[] MILLER_RABIN_CHECKER = {2, 7, 61};
	private static final int BIG = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		long timer = System.currentTimeMillis();
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int count = 0;
		
		while(N-- > 0) {
			long square = in.readLong();
			count += isPrime(square * 2 + 1);
		}
		
		System.out.println(count);
		System.out.println(System.currentTimeMillis() - timer);
	}
	
	private static int isPrime(long sqr) {		
		if(sqr <= 100000L) {
			for(long i = 2; i * i <= sqr; i++) {
				if(sqr % i == 0) return 0;
			}
			
			return 1;
		}
		
	    for (int prime : MILLER_RABIN_CHECKER) {
	    	if (!millerRabin(sqr, prime)) return 0;
	    }
	    
		return 1;
	}
	
	private static boolean millerRabin(long sqr, long prime) {
		if(sqr % prime == 0) return false;
		
		long d = sqr - 1;
		int loop = 0;
		
		while(d % 2 == 0) {
			loop++;
			d /= 2;
		}
		
		long tmp = powNMod(prime, d, sqr);
		if(tmp == 1 || tmp == sqr - 1) return true;
		
		while(loop-- > 1) {
			if(tmp > BIG) tmp = mulMod(tmp, tmp, sqr);
			else tmp = (tmp * tmp) % sqr;
			
			if(tmp == sqr - 1) return true;
		}
		
		return false;
	}
	
	private static long powNMod(long prime, long tmp, long sqr) {
		prime %= sqr;
		long x = 1L;
		
		while(tmp > 0) {
			if(tmp % 2 == 1) {
				if(x > BIG) x = mulMod(x, prime, sqr);
				else x = (x * prime) % sqr;
			}
			
			if(prime > BIG) prime = mulMod(prime, prime, sqr);
			else prime = (prime * prime) % sqr;
			
			tmp /= 2;
		}
		
		return x;
	}
	
	private static long mulMod(long a, long b, long mod) {
		a %= mod;
		b %= mod;
		
		long r = 0;
		
		while(b > 0) {
			if(b % 2 == 1) r = adder(r, a, mod);
			
			a = adder(a, a, mod);
			b /= 2;
		}
		
		return r;
	}
	
	private static long adder(long a, long b, long mod) {
		a %= mod;
		b %= mod;
		
		long diff = mod - b;
		return (a >= diff ? a - diff: a + b);
	}
	
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public long readLong() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
