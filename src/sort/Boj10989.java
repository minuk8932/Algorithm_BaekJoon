package sort;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 10989 : 수 정렬하기 3
 *
 *	@see https://www.acmicpc.net/problem/10989
 *
 */
public class Boj10989 {
	private static final int MAX = 10_001;
	private static Number[] nums = new Number[MAX];
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int N = in.readInt();
		
		for(int i = 1; i < MAX; i++) nums[i] = new Number(false, 0);
		
		for(int i = 0; i < N; i++){
			int idx = in.readInt();
			nums[idx].isPlaced = true;
			nums[idx].counts += 1;
		}
		
		for(int i = 1; i < MAX; i++){
			if(nums[i].isPlaced){					
				while(nums[i].counts-- > 0){
					out.printLine(i);
				}
			}
		}
	}
	
	private static class Number{
		boolean isPlaced;
		int counts;
		
		public Number(boolean isPlaced, int counts) {
			this.isPlaced = isPlaced;
			this.counts = counts;
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
	}
}
