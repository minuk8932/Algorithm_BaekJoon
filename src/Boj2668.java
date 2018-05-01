import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj2668 {
	private static final String END_LINE = "\n";
	private static final int isCycle = 1;
	
	private static int N = 0;
	private static int max = 0;
	private static ArrayList<Integer>[] map = null;
	private static boolean[] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new ArrayList[N + 1];
		
		for(int i = 0; i < N + 1; i++){
			map[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N + 1; i++){
			map[i].add(Integer.parseInt(br.readLine()));
		}
		
		isVisited = new boolean[N + 1];
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < N + 1; i++){			
			if(!isVisited[i]){
				dfs(i);
			}
		}
		
		for(int i = 1; i < N + 1; i++){
			if(isVisited[i]){
				sb.append(i).append(END_LINE);
				max++;
			}
		}
		
		System.out.println(max);
		System.out.println(sb.toString());
	}
	
	private static void dfs(int current){	
		if(isVisited[current]) return;
		
		isVisited[current] = true;
		
		
		for(int next: map[current]){	
			if(isVisited[next]){
				isVisited[current] = false;
				return;
			}
								
			dfs(next);
		}
	}
}
