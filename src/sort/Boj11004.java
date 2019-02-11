package sort;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 11004번: K번째 수
 *
 *	@see https://www.acmicpc.net/problem/11004
 *
 */
public class Boj11004 {
	private static int[] res, arr;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int K = in.readInt() - 1;
		
		arr = new int[N];
		res = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = in.readInt();
		}
		
		mergeSort(0, N - 1);			// 합병 정렬
		System.out.println(arr[K]);
	}
	
	private static void mergeSort(int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(start, mid);			// 재귀를 통해 지속적으로 2등분
			mergeSort(mid + 1, end);
			merge(start, mid, end);
		}
	}
	
	private static void merge(int start, int mid, int end) {
		int[] bound = {start, mid + 1, start};
		
		while(bound[0] <= mid && bound[1] <= end) {		// 2등분된 배열 각각의 범위 제한
			if(arr[bound[0]] <= arr[bound[1]]) {		// 2등분된 배열 원소끼리 비교 후 임시 배열로 저장
				insertOne(bound[2], bound[0]);
				bound[0]++;
			}
			else {
				insertOne(bound[2], bound[1]);
				bound[1]++;
			}
			
			bound[2]++;			// 두 배열의 총 길이
		}
		
		if(bound[0] > mid) 
			bound[2] = insertRange(bound[1], bound[2], end);
		else 
			bound[2] = insertRange(bound[0], bound[2], mid);
		
		for(int i = start; i < end + 1; i++) {		// 정렬된 배열의 값을 원본 배열로
			arr[i] = res[i];
		}
	}
	
	private static void insertOne(int target, int value) {
		res[target] = arr[value];
	}
	
	private static int insertRange(int start, int target, int limit) {		
		for(int i = start; i < limit + 1; i++) {
			res[target] = arr[i];
			target++;
		}
		
		return target;
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
