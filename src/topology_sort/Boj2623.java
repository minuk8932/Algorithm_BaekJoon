package topology_sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2623번: 음악 프로그램
 *
 *	@see https://www.acmicpc.net/problem/2623/
 *
 */
public class Boj2623 {
	private static ArrayDeque<Integer> stack = new ArrayDeque<>();
	private static boolean[] isVisited;
	private static boolean[] isFinished;
	private static boolean isCycle;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] singer = new ArrayList[N + 1];
		isFinished = new boolean[N + 1];
		isVisited = new boolean[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			singer[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			int[] seq = new int[count];
			for(int i = 0; i < count; i++) {
				seq[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < count - 1; i++) {
				singer[seq[i]].add(seq[i + 1]);
			}
		}
		
		for(int i = 1; i < N + 1; i++) {
			if(singer[i].size() == 0 || isVisited[i]) continue;		// 다수의 그래프가 존재할 수 있으므로 싹다 돌림
			topologySort(singer, i);
		}
		
		if(isCycle) {
			System.out.println(0);
		}
		else {
			StringBuilder sb = new StringBuilder();
			
			while(!stack.isEmpty()) {
				int node = stack.pop();
				sb.append(node).append(NEW_LINE);
			}
			
			for(int i = 1; i < N + 1; i++) {
				if(!isVisited[i]) sb.append(i).append(NEW_LINE);
			}
			
			System.out.print(sb);
		}
	}
	
	private static void topologySort(ArrayList<Integer>[] map, int current) {
		isVisited[current] = true;
		
		for(int next: map[current]) {
			if(!isVisited[next]) topologySort(map, next);
			else if(!isFinished[next]) isCycle = true;		// 방문한 정점이 아직 스택에 들어가지 않은경우 -> 사이클
		}
		
		isFinished[current] = true;
		stack.push(current);
	}
}
