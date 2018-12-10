package sliding_window;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * 
 * 	@author minchoba
 *	백준 11003번: 최솟값 찾기
 *
 *	@see https://www.acmicpc.net/problem/11003/
 *
 */
public class Boj11003 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		
		int N = in.readInt();
		int L = in.readInt();
		slidingWindow(in, N, L);
	}
	
	private static class Pair{
		int first;
		int second;
		
		public Pair(int first, int last) {
			this.first = first;
			this.second = last;
		}
	}
	
	private static void slidingWindow(InputReader in, int numSize, int winSize) throws Exception{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Deque<Pair> deq = new LinkedList<>();
		
		for(int i = 1; i < numSize + 1; i++) {
			while(!deq.isEmpty() && deq.peekFirst().second <= i - winSize) {	// 윈도우 사이즈에 따른 조정
				deq.pollFirst();
			}
			
			int num = in.readInt();
			while(!deq.isEmpty() && deq.peekLast().first >= num) {				// 값 비교에 따른 조정
				deq.pollLast();
			}
			
			deq.offer(new Pair(num, i));
			bw.write(deq.peekFirst().first + SPACE);
		}
		
		bw.flush();
		bw.close();
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
