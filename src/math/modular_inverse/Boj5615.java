package math.modular_inverse;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.InputMismatchException;

/**
 *
 * @author exponential-e
 * 백준 5615번: 아파트 임대
 *
 * @see https://www.acmicpc.net/problem/5615/
 *
 */
public class Boj5615 {
	private static BigInteger ONE = new BigInteger("1");
	private static final int[] list = {2, 3, 5, 7, 11};

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		long count = 0;

		while(N-- > 0) {
			long sqrt = in.readLong();
			if(isPrime(sqrt * 2 + 1)) count++;
		}

		System.out.println(count);
	}

	private static boolean isPrime(long sqrt){
		if (sqrt == 2 || sqrt == 3) return true;
		if (sqrt <= 1 || sqrt % 2 == 0) return false;

		for (int i = 0; i < list.length; i++) {
			long p = list[i];
			if (sqrt == p) return true;
			if (millerRabin(sqrt, p)) return false;
		}
		return true;
	}

	private static boolean millerRabin(long p, long a){				// 2 * S + 1 is not prime?
		long r = 0;
		long div = p - 1;

		while (div % 2 == 0) {
			r++;
			div /= 2;
		}

		BigInteger bigP = new BigInteger(p + "");
		BigInteger bigPMinus = new BigInteger(p + "").subtract(ONE);

		BigInteger x = pow(new BigInteger(a + ""), div, bigP);
		if (x.equals(ONE) || x.equals(bigPMinus)) return false;

		long loop = r - 1;

		while(loop-- > 0) {
			x = pow(x, 2, bigP);
			if (x.equals(bigPMinus)) return false;
		}
		return true;
	}

	private static BigInteger pow(BigInteger x, long div, BigInteger p){
		BigInteger result = ONE;
		x = x.mod(p);

		while(div > 0){
			if(div % 2 == 1) result = result.multiply(x).mod(p);

			div /= 2;
			x = x.multiply(x).mod(p);
		}

		return result;
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

		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
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

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
