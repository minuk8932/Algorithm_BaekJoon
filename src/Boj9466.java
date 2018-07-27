import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj9466 {
private static final String NEW_LINE = "\n";
	
	private static int start = 0;
	private static int cnt = 0;
	
	private static ArrayList<Integer>[] term = null;
	private static boolean[] isVisited = null;
	private static boolean[] chk = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			int n = Integer.parseInt(br.readLine());
			term = new ArrayList[n + 1];
			isVisited = new boolean[n + 1];
			
			for(int i = 0; i < n + 1; i++){
				term[i] = new ArrayList<>();
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i < n + 1; i++){
				term[i].add(Integer.parseInt(st.nextToken()));
			}
			
			isVisited = new boolean[n + 1];
			
			for(int i = 1; i < n + 1; i++){
				if(isVisited[i]) continue;				
				chk = new boolean[n + 1];

				
				dfs(i);
			}
			
			sb.append(n - cnt).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int current) {
		if(!isVisited[current]) {
			isVisited[current] = true;
		
			for(int next: term[current]) {
				dfs(next);
			}
		}
		else {
			isVisited[start] = true;
		}
	}
}
