package breadth_first_search;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 11952번: 좀비
 *
 *	@see https://www.acmicpc.net/problem/11952/
 *
 */
public class Boj11952 {
	private static boolean[] zombieCity;
	private static int[] roomCharge;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		int K = in.readInt();
		int S = in.readInt();
		
		int p = in.readInt();
		int q = in.readInt();
		
		zombieCity = new boolean[N];
		roomCharge = new int[N];
		
		while(K-- > 0) {
			zombieCity[in.readInt() - 1] = true;		// 좀비가 점령한 도시
		}
		
		ArrayList<Integer>[] path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			int v1 = in.readInt() - 1;
			int v2 = in.readInt() - 1;
			
			path[v1].add(v2);
			path[v2].add(v1);
		}
		
		setCost(N, S, path, p, q);
		System.out.println(getCost(N, path));
	}
	
	private static void setCost(int n, int limit, ArrayList<Integer>[] list, int safe, int danger) {
		Queue<Integer> q = new LinkedList<>();
		int[] visit = new int[n];
		Arrays.fill(visit, Integer.MAX_VALUE);
		
		for(int i = 0; i < n; i++) {
			if(!zombieCity[i]) continue;
			
			q.offer(i);
			visit[i] = 1;
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: list[current]) {
					if(visit[next] > visit[current] + 1) {
						visit[next] = visit[current] + 1;		// 좀비에게 점령된 도시로 부터 떨어진 거리
						
						q.offer(next);
					}
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(visit[i] - 1 <= limit) roomCharge[i] = danger;	// 위험 지역과 안전 지역 숙박비를 나눠서 저장
			else roomCharge[i] = safe;
		}
	}
	
	private static long getCost(int n, ArrayList<Integer>[] list) {
		long[] cost = new long[n];
		Arrays.fill(cost, Long.MAX_VALUE);
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		cost[0] = 0;

		while(!q.isEmpty()) {
			int current = q.poll();

			for(int next: list[current]) {
				if(zombieCity[next]) continue;
				
				if(cost[next] > cost[current] + roomCharge[next]) {		// 최소 비용을 계산하며 n번 도시로 이동
					cost[next] = cost[current] + roomCharge[next];
					
					q.offer(next);
				}
			}
		}
		
		return cost[n - 1] - roomCharge[n - 1];			// n번 도시의 숙박 비용은 무시
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
