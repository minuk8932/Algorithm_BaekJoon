package dynamic_programming;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 14002번: 가장 긴 증가하는 부분 수열 4
 *
 *	@see https://www.acmicpc.net/problem/14002/
 *
 */
public class Boj14002 {
	private static OutputWriter out = new OutputWriter(System.out);
	private static final String SPACE = " ";
	
	private static class Pair{
		int idx;
		int num;
		
		public Pair(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = in.readInt();
		}
		
		lis(N, arr);
	}
	
	private static void lis(int n, int[] arr) {
		int[] dp = new int[n];
		Pair[] track = new Pair[n];
		
		int index = 0;
		dp[0] = arr[0];
		track[0] = new Pair(0, arr[0]);
		
		for(int i = 1; i < n; i++) {				// nlogn lis		
			if(dp[index] < arr[i]) {
				dp[++index] = arr[i];
				
				track[i] = new Pair(index, arr[i]);			// 추적자를 심어둠
			}
			else {
				int tmp = binarySearch(dp, 0, index, arr[i]);
				dp[tmp] = arr[i];
				
				track[i] = new Pair(tmp, arr[i]);
			}
		}
		
		out.printLine(index + 1);			// 부분 수열의 최장 길이
		getSubSequence(track, index);
	}
	
	private static int binarySearch(int[] dp, int start, int end, int target) {
		int idx = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(dp[mid] >= target) {
				end = mid - 1;
				idx = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return idx;
	}
	
	private static void getSubSequence(Pair[] p, int loop) {
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		for(int i = p.length - 1; i >= 0; i--) {
			if(loop != p[i].idx) continue;
			
			stack.push(p[i].num);			// 추적자가 존재했던 위치를 비교해서 해당 값을 스택에 저장
			loop--;
		}

		while(!stack.isEmpty()) {
			out.print(stack.pop() + SPACE);	// 수열에 해당하는 값을 순서대로 출력
		}
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

	private static class OutputWriter {
		private final PrintWriter writer;

		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		}

		public OutputWriter(Writer writer) {
			this.writer = new PrintWriter(writer);
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0) {
					writer.print(' ');
				}
				writer.print(objects[i]);
			}
			writer.flush();
		}

		public void printLine(Object... objects) {
			print(objects);
			writer.println();
			writer.flush();
		}

		public void close() {
			writer.close();
		}

		public void flush() {
			writer.flush();
		}
	}
}
