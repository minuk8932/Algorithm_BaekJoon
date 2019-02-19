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
 *	백준 16940번: BFS 스페셜 저지
 *
 *	@see https://acmicpc.net/problem/16940/
 *
 */
public class Boj16940 {	
	private static boolean[] isVisited;
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] tree = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		parent = new int[N + 1];
		
		for(int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			tree[from].add(to);
			tree[to].add(from);
		}
		
		int[] sequence = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}

		getParent(N, tree, sequence);
		System.out.println(getResult(N, tree, sequence));
	}
	
	private static void getParent(int n, ArrayList<Integer>[] list, int[] seq) {
		isVisited = new boolean[n + 1];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		
		isVisited[1] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: list[current]) {
				if(isVisited[next]) continue;
				isVisited[next] = true;
				parent[next] = current;			// 각 노드 별 부모 노드 값 저장
				
				q.offer(next);
			}
		}
	}
	
	private static int getResult(int n, ArrayList<Integer>[] list, int[] seq) {
		isVisited = new boolean[n + 1];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		isVisited[1] = true;
		
		int index = 1, count = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			while(index < n) {
				if(parent[seq[index]] != current) break;		// 이번 차례 노드의 부모 노드 값과 현재 값이 다른경우
				if(isVisited[seq[index]]) continue;
				isVisited[seq[index]] = true;
				count++;
				
				q.offer(seq[index++]);		// 다음 인덱스 탐색
			}
		}

		return count == n ? 1 : 0;
	}
}
