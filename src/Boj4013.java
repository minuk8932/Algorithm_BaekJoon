import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj4013 {
	private static ArrayDeque<Integer> stack = new ArrayDeque<>();
	private static boolean[] isVisited;
	private static int[] cost;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] map = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			map[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
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
		stack.push(start);
		
		while(!stack.isEmpty()) {
			int egde = stack.pop();
			
			
		}
	}
	
	private static void backTracking(ArrayList<Integer>[] arr, int current) {
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		for(int next: arr[current]) {
			if(isVisited[next]) continue;
			
			backTracking(arr, next);
			stack.push(next);
		}
	}
}
