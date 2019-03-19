package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15900번: 나무 탈출
 *
 *	@see https://www.acmicpc.net/problem/15900/
 *
 */
public class Boj15900 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int loop = N - 1;
		
		ArrayList<Integer>[] tree = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		while(loop-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int edge1 = Integer.parseInt(st.nextToken());
			int edge2 = Integer.parseInt(st.nextToken());
			
			tree[edge1].add(edge2);
			tree[edge2].add(edge1);
		}
		
		System.out.println(game(N, tree));
	}
	
	private static String game(int n, ArrayList<Integer>[] list) {
		int[] isVisited = new int[n + 1];
		
		ArrayList<Integer> leaf = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		
		isVisited[1] = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			boolean isLeaf = true;
			
			for(int next: list[current]) {
				if(isVisited[next] != 0) continue;
				isVisited[next] = isVisited[current] + 1;
				
				isLeaf = false;
				q.offer(next);
			}
			
			if(isLeaf) leaf.add(current);
		}
		
		int total = 0;
		
		for(int node: leaf) {				// 리프 노드의 방문 값 합산
			total += (isVisited[node] - 1);
		}
		
		return total % 2 == 0 ? "No": "Yes";		// 짝수면 지고 홀수면 이긴다.
	}
}
