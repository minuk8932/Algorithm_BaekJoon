import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj3682 {
	private static ArrayList<Integer>[] map, revMap;
	private static ArrayDeque<Integer> stack = new ArrayDeque<>();
	private static boolean[] isVisited;
	private static int count;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			map = new ArrayList[n + 1];
			revMap = new ArrayList[n + 1];
			isVisited = new boolean[n + 1];
			
			for(int i = 0; i < n + 1; i++) {
				map[i] = new ArrayList<>();
				revMap[i] = new ArrayList<>();
			}
			
			while(m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				map[a].add(b);
				revMap[b].add(a);
			}
			
			for(int start = 1; start < n + 1; start++) {
				if(isVisited[start]) continue;
				
				backTracking(map, start, true);
				stack.push(start);
			}
			
			count = 0;
			
			isVisited = new boolean[n + 1];
			while(!stack.isEmpty()) {
				int start = stack.pop();
				if(isVisited[start]) continue;
				
				count++;
				backTracking(revMap, start, false);
			}
			
			sb.append(count).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void backTracking(ArrayList<Integer>[] arr, int current, boolean save) {
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		for(int next: arr[current]) {
			if(isVisited[next]) continue;
			
			if(!save) count++;
			backTracking(arr, next, save);
			if(save) stack.push(next);
		}
	}
}
