package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15805번: 트리나라 관광 가이드
 *
 *	@see https://www.acmicpc.net/problem/15805/
 *
 */
public class Boj15805 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static class Node{
		int p;
		int c;
		
		public Node(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] path = new int[N];
		for(int i = 0; i < N; i++) {
			path[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(makeMap(N, path)); 
	}
	
	private static StringBuilder makeMap(int n, int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		ArrayList<Node> parent = new ArrayList<>();
		boolean[] visit = new boolean[n];

		parent.add(new Node(-1, arr[0]));
		visit[arr[0]] = true;
		
		for(int i = 1; i < n; i++) {
			if(visit[arr[i]]) continue;					// 이미 방문한 곳은 어떤 노드의 부모노드가 될 수도
			visit[arr[i]] = true;
			
			parent.add(new Node(arr[i - 1], arr[i]));	// 이전 등장한 노드 번호는 현재 노드의 부모 노드
		}
		
		int[] result = new int[parent.size()];
		sb.append(result.length).append(NEW_LINE);
		
		for(Node node: parent) {		// 해당 순번에 따라 부모 노드의 번호 저장
			result[node.c] = node.p;
		}
		
		for(int i = 0; i < result.length; i++) {
			sb.append(result[i]).append(SPACE);
		}
		
		return sb;
	}
}
