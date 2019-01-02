package topology_sort;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 1948번: 임계경로
 *
 *	@see https://www.acmicpc.net/problem/1948/
 *
 */
public class Boj1948 {
	private static ArrayList<Node>[] map;
	private static ArrayList<Node>[] revMap;
	private static int[] res;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int n = in.readInt();
		int m = in.readInt();
		
		map = new ArrayList[n + 1];
		revMap = new ArrayList[n + 1];
		
		for(int i = 0; i < n + 1; i++) {
			map[i] = new ArrayList<>();
			revMap[i] = new ArrayList<>();
		}
		
		int[] indegree = new int[n + 1];
		
		while(m-- > 0) {
			int from = in.readInt();
			int to = in.readInt();
			int cost = in.readInt();
			
			map[from].add(new Node(to, cost));
			revMap[to].add(new Node(from, cost));
			indegree[to]++;		// 위상 갯수
		}
		
		int start = in.readInt();
		int end = in.readInt();
		
		int max = topologySort(n, indegree, start, end);		// 정방향 탐색, 최댓값
		int counts = revBFS(n, start, end, max);				// 역방향 탐색, 경로
		
		System.out.println(max + "\n" + counts);
	}
	
	private static class Node{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	private static int topologySort(int N, int[] parent, int s, int e) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(s);
		
		res = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(Node next: map[current]){
					parent[next.edge]--;
					
					if(res[next.edge] < res[current] + next.cost) {		// 제일 오래걸리는 경로로 초기화
						res[next.edge] = res[current] + next.cost;
					}
					
					if(parent[next.edge] == 0) q.offer(next.edge);		// 진행되어야하는 경로
				}
			}
		}
		
		return res[e];
	}
	
	private static int revBFS(int N, int s, int e, int max) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(e);
		boolean[] isVisited = new boolean[N + 1];
		int cnt = 0;
		
		for(int i = 1; i < N + 1; i++) {
			while(!q.isEmpty()) {
				int current = q.poll();
				
				if(isVisited[current]) continue;
				isVisited[current] = true;
				
				for(Node next: revMap[current]){		
					if(res[next.edge] == res[current] - next.cost) {		// 최대 경로와 부분적으로 같은 값을 가지는 집합
						
						cnt++;
						q.offer(next.edge);
					}
				}
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
