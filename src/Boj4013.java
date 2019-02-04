import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj4013 {
	private static ArrayDeque<Integer> stack = new ArrayDeque<>();
	private static boolean[] isVisited;
	private static int[] indegree;
	private static int[] save;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] map = new ArrayList[N + 1];
		ArrayList<Integer>[] revMap = new ArrayList[N + 1];
		indegree = new int[N + 1];
		save = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			map[i] = new ArrayList<>();
			revMap[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a].add(b);
			map[b].add(a);
//			indegree[b]++;
		}
		
		int[] cash = new int[N + 1];
		boolean[] isRest = new boolean[N + 1];
		for(int i = 1; i < N + 1; i++) {
			cash[i] = Integer.parseInt(br.readLine());
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		while(P-- > 0) {
			isRest[Integer.parseInt(st.nextToken())] = true;
		}
		
		isVisited = new boolean[N + 1];
		backTracking(map, start);
		
		isVisited = new boolean[N + 1];
		while(!stack.isEmpty()) {
			int edge = stack.pop();
			if(isVisited[edge]) continue;
			
			TopologySearch(revMap, isRest, cash, edge);		// indegree가 1개보다 많은 경우엔 위상별로 비교
		}
		
		int max = 0;
		for(int i = 1; i < N + 1; i++) {
			max += save[i];
		}
		
		System.out.println(max);
	}
	
	private static void backTracking(ArrayList<Integer>[] arr, int current) {
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		for(int next: arr[current]) {
			if(isVisited[next]) continue;
			indegree[current]++;
			backTracking(arr, next);
		}
		stack.push(current);
	}
	
	private static void TopologySearch(ArrayList<Integer>[] arr, boolean[] rest, int[] cash, int current) {
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		for(int next: arr[current]) {
			if(isVisited[next]) continue;
			
			TopologySearch(arr, rest, cash, next);
		}
	}
}
