import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj2668 {
	private static final String END_LINE = "\n";
	private static final int isCycle = 1;
	
	private static int N = 0;
	private static int max = 0;
	private static int start = 0;
	private static int loop = 0;
	
	private static ArrayList<Integer>[] map = null;
	private static int[] isVisited = null;
	
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
		
		StringBuilder sb = new StringBuilder();
		
		isVisited = new int[N + 1];
		for(int i = 1; i < N + 1; i++){		
			if(isVisited[i] == 0){
				start = i;
				dfs(i);
				loop++;
			}
		}
		
		for(int i = 1; i < N + 1; i++){
			if(isVisited[i] != 0){
				sb.append(i).append(END_LINE);
				max++;
			}
		}
		
		System.out.println(max);
		System.out.println(sb.toString());
	}
	
	private static void dfs(int current){	
		if(isVisited[current] != 0) return;
		
		isVisited[current] = loop;
		
		for(int next: map[current]){	
			if(isVisited[next] == 0){
				isVisited[next] = loop;
				
				dfs(next);
			}
		}
	}
}
