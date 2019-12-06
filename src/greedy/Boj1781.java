package greedy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 * 
 *	@author exponential-e
 *	백준 1781번: 컵라면
 *
 *	@see https://www.acmicpc.net/problem/1781/
 *
 */
public class Boj1781 {
	private static class Homework implements Comparable<Homework>{
		int deadline;
		int cup;
		
		public Homework(int deadline, int cup) {
			this.deadline = deadline;
			this.cup = cup;
		}

		@Override
		public int compareTo(Homework h) {
			if(this.deadline < h.deadline) return -1;
			else if(this.deadline > h.deadline) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		
		Homework[] hw = new Homework[N];
		for(int i = 0; i < N; i++) {
			int d = in.readInt() - 1;
			int c = in.readInt();
			
			hw[i] = new Homework(d, c);
		}
		
		System.out.println(getSum(hw));
	}
	
	private static long getSum(Homework[] arr) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		Arrays.sort(arr);
		
		long result = 0;
		int idx = arr.length - 1;
		
		for (int i = arr.length - 1; i >= 0; i--) {
			for (; idx >= 0 && arr[idx].deadline == i; idx--) {		// before deadline that is smaller than size
				pq.offer(-arr[idx].cup);
			}

			if (!pq.isEmpty()) result -= pq.poll();					// add cup
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
