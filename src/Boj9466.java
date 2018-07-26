import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Boj9466 {
	private static final String END_LINE = "\n";
	
	private static int res = 0;
	private static int cnt = 0;
	private static boolean isCycle = false;
	private static int start = 0;
	
	private static ArrayList<Integer>[] term = null;
	private static int[] isVisited = null;
	private static boolean[] isAdd = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			int n = Integer.parseInt(br.readLine());
			term = new ArrayList[n + 1];
			isVisited = new int[n + 1];
			isAdd = new boolean[n + 1];
			
			for(int i = 0; i < n + 1; i++){
				term[i] = new ArrayList<>();
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i < n + 1; i++){
				term[i].add(Integer.parseInt(st.nextToken()));
			}
			
			res = 0;
			
			for(int i = 1; i < n + 1; i++){
				cnt = 0;
				
				if(isVisited[i] == 0){
					isCycle = false;
					start = i;
					
					dfs(i);
					if(isCycle) res += cnt;
				}
			}			
			
			sb.append(n - res).append(END_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int current){
		if(isVisited[current] != 0) return;
		isVisited[current] = 1;
		
		cnt++;
		
		for(int next : term[current]){
			if(next == start || current == next) {
				if(start != next) cnt = 1;
				isCycle = true;
			}
			dfs(next);
		}
	}
}
