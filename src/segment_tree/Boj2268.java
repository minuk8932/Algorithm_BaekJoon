package segment_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 2268번: 수들의 합
 */
public class Boj2268 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		
		int S = 1;
		while(S < N) S <<= 1;
		long[] seg = new long[S * 2];
		
		StringBuilder sb = new StringBuilder();
		
		while(M-- > 0) {
			int cmd = in.readInt();
			int a = in.readInt();
			int b = in.readInt();
			
			switch(cmd) {
			case 0:
				int start = (a > b ? b : a) + S - 1;
				int end = (b > a ? b : a) + S - 1;
				
				sb.append(sum(seg, start, end)).append(NEW_LINE);
				break;
				
			case 1:
				seg = modify(seg, S + a - 1, b);
				break;
			}
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static long sum(long[] tree, int from, int to) {
		long total = 0;
		
		while(from <= to) {				// 구간 합, 단 시작 인덱스나 끝 인덱스가 혼자 떨어진 경우는 나누어서 미리 합해줌
			if(from % 2 == 1) {
				total += tree[from];
				from++;
			}
			
			if(to % 2 == 0) {
				total += tree[to];
				to--;
			}
			
			from /= 2;
			to /= 2;
		}
		
		return total;
	}
	
	private static long modifyArr(long[] arr, int mod, int idx) {
		int diff = mod * -2 + 1;
		return arr[idx / 2] = arr[idx] + arr[idx + diff];
	}
	
	private static long[] modify(long[] tree, int idx, int val) {
		tree[idx] = val;
	
		while(idx > 1) {										// 지정 인덱스 값 수정 후 차례로 올라가며 부모노드의 값도 변경
			tree[idx / 2] = modifyArr(tree, idx % 2, idx);
			idx /= 2;
		}
		
		return tree;
	}
	
	private static class InputReader {				// fast I/O
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
