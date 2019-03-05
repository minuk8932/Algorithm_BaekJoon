package greedy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 2109번: 순회 강연
 *
 *	@see https://www.acmicpc.net/problem/2109/
 *
 */
public class Boj2109 {
	private static class Lecture implements Comparable<Lecture>{
		int day;
		int pay;
		
		public Lecture(int pay, int day) {
			this.pay = pay;
			this.day = day;
		}

		@Override
		public int compareTo(Lecture l) {
			if(this.day < l.day) return -1;
			else if(this.day > l.day) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		
		int N = in.readInt();
		
		Lecture[] lec = new Lecture[N];
		for(int i = 0; i < N; i++) {
			lec[i] = new Lecture(in.readInt(), in.readInt());
		}
			
		Arrays.sort(lec);
		System.out.println(lectureCost(N, lec));
	}
	
	private static int lectureCost(int n, Lecture[] arr) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int cost = 0;
		
		for(int i = 0; i < n; i++) {
			cost += arr[i].pay;								// 수익을 모두 더함
			pq.offer(arr[i].pay);
			
			if(pq.size() > arr[i].day) cost -= pq.poll();	// 지난 날짜의 수익 중 받지 못한 수익은 빼줌
		}
		
		return cost;
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
