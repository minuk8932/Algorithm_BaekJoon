package greedy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 1202번: 보석 도둑
 *
 *	@see https://www.acmicpc.net/problem/1202/
 *
 */
public class Boj1202 {	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int K = in.readInt();
		
		Jewel[] heap  = new Jewel[N];
		int[] bag = new int[K];
		for(int i = 0; i < N; i++) {
			int m = in.readInt();
			int v = in.readInt();
			
			heap[i] = new Jewel(m, v);
		}
		
		for(int i = 0; i < K; i++) {
			bag[i] = in.readInt();
		}
		
		Arrays.sort(bag);
		Arrays.sort(heap);
		System.out.println(steal(heap, bag));		// 결과 출력
	}
	
	private static class Jewel implements Comparable<Jewel>{
		int weight;
		int value;
		
		public Jewel(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(Jewel j) {
			if(this.weight < j.weight) return -1;
			else if(this.weight > j.weight) return 1;
			else return 0;
		}
	}
	
	private static class Value implements Comparable<Value> {
		int num;
		
		public Value (int num) {
			this.num = num;
		}

		@Override
		public int compareTo(Value v) {
			return this.num > v.num ? -1 : 1;
		}
	}
	
	private static long steal(Jewel[] heap, int[] arr) {
		long sum = 0;
		int idx = 0;
		
		PriorityQueue<Value> pq = new PriorityQueue<>();
		
		for(int i = 0; i < arr.length; i++) {
			while(idx < heap.length && heap[idx].weight <= arr[i]) {		// 수용 가능 무게보다 작거나 같은 경우 중
				pq.offer(new Value(heap[idx++].value));				// 포함되는 보석의 가치를 우선순위 큐에 저장
			}
			
			if(!pq.isEmpty()) sum += pq.poll().num;		// 가치가 높은 순으로 저장된 우선순위 큐 가장 앞의 값 +
		}
		
		return sum;
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

		public double readDouble() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') {
				if (c == 'e' || c == 'E') {
					return res * Math.pow(10, readInt());
				}
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') {
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) {
					if (c == 'e' || c == 'E') {
						return res * Math.pow(10, readInt());
					}
					if (c < '0' || c > '9') {
						throw new InputMismatchException();
					}
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
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

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
