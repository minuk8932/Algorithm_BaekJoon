package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2617번: 구슬 찾기
 *
 *	@see https://www.acmicpc.net/problem/2617/
 *
 */
public class Boj2617 {	
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] ball = new ArrayList[N + 1];
		ArrayList<Integer>[] rev = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			ball[i] = new ArrayList<>();
			rev[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			ball[from].add(to);
			rev[to].add(from);
		}
		
		int count = 0;
		int[] isHeavier = new int[N + 1];
		int[] isLighter = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			isVisited = new boolean[N + 1];
			isHeavier = dfs(ball, isHeavier, i, i);		// 현재 i보다 더 무거운 구슬의 갯수
		}
		
		for(int i = 1; i < N + 1; i++) {
			isVisited = new boolean[N + 1];
			isLighter = dfs(rev, isLighter, i, i);		// 현재 i보다 더 가벼운 구슬의 갯수
		}
		
		int half = N / 2;
		for(int i = 1; i < N + 1; i++) {
			if(isLighter[i] > half || isHeavier[i] > half) count++;		// 중간 값이 불가능한 구슬의 갯수
		}
		
		System.out.println(count);
	}
	
	private static int[] dfs(ArrayList<Integer>[] list, int[] visit, int current, int node) {
		if(isVisited[current]) return visit;
		isVisited[current] = true;
		
		for(int next: list[current]) {
			if(isVisited[next]) continue;
			visit[node]++;					// 현재 구슬에 보다 무거운 구슬의 갯수 + 1
			
			dfs(list, visit, next, node);
		}
		
		return visit;
	}
}
