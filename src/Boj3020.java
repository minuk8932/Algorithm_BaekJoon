import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Boj3020 {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int H = in.readInt();
		
		Cave[] c = new Cave[N];
		for(int i = 0; i < N; i++) {
			// true 종유석, false 석순
			c[i] = new Cave(in.readInt(), i % 2 == 0 ? false: true);
		}
		
		Arrays.sort(c);
		
		binarySearch(c, H);
	}
	
	private static class Cave implements Comparable<Cave>{
		int num;
		boolean pos;
		
		public Cave(int num, boolean pos) {
			this.num = num;
			this.pos = pos;
		}

		@Override
		public int compareTo(Cave c) {
			if(this.pos) return -1;
			else return this.num < c.num ? -1: 1;
		}
	}
	
	private static void binarySearch(Cave[] arr, int h) {
		int start = 0, end = arr.length;
		int max = arr.length - 1;
		
		while(start < end) {
			int mid = (start + end) / 2;
			int count = getCount(arr, arr[mid].num, h);
			
			if(count < max) {
				max = count;
				start = mid + 1;
			}
		}
	}
	
	private static int getCount(Cave[] arr, int limit, int h) {
		int cnt = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(!arr[i].pos) {
				if(arr[i].num <= limit) cnt++;
			}
			else {
				if((h - arr[i].num) >= (h - limit)) cnt++;
			}
		}
		
		return cnt;
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
