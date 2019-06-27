package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 8907번: 네온 사인
 *
 *	@see https://www.acmicpc.net/problem/8907/
 *
 */
public class Boj8907 {
	private static final String NEW_LINE = "\n";
	
	private static class Node{
		int idx;
		int color;
		
		public Node(int idx, int color) {
			this.idx = idx;
			this.color = color;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			ArrayList<Node>[] line = new ArrayList[N];			
			for(int i = 0; i < N; i++) {
				line[i] = new ArrayList<Node>();
			}
			
			for(int i = 0; i < N - 1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int next = i + 1;
				
				while(st.hasMoreTokens()) {
					int color = Integer.parseInt(st.nextToken());
					line[i].add(new Node(next, color));
					line[next++].add(new Node(i, color));
				}
			}
			
			sb.append(counting(N, line)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int counting(int n, ArrayList<Node>[] list) {
		int count = 0;
		int total = n * (n - 1) * (n - 2) / 6;			// n개의 점으로 만들 수 있는 삼각형의 갯수
		
		for(int start = 0; start < n; start++) {
			int[] color = new int[2];
			
			for(Node other: list[start]) {				// 그 중 0번과 1번의 색의 갯수를 나누어 카운팅		
				color[other.color]++;
			}
			
			count += color[0] * color[1];				// 서로 다른 색의 변을 갖는 두 선분의 갯수(즉, 한개의 꼭지점)
		}
		
		return total - count / 2;						// 꼭지점이 하나씩 중복됨
	}
}
