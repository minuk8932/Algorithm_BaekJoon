package depth_first_search;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Boj10451 {
	private static final String END_LINE = "\n";
	
	private static int n = 0;
	private static int res = 0;
	private static ArrayList<Integer>[] term = null;
	private static boolean[] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			n = Integer.parseInt(br.readLine());
			term = new ArrayList[n + 1];
			isVisited = new boolean[n + 1];
			
			for(int i = 0; i < n + 1; i++){
				term[i] = new ArrayList<>();
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i < n + 1; i++){
				term[i].add(Integer.parseInt(st.nextToken()));
			}
			
			res = 0;
			
			for(int i = 1; i < n + 1; i++){
				if(!isVisited[i]){
					isCycle(i);
					res++;
				}
			}
			
			sb.append(res).append(END_LINE);
		}
		
		System.out.println(sb.toString());
	}
	private static void isCycle(int current){
		if(isVisited[current]) 	return;
		
		isVisited[current] = true;
		
		for(int next : term[current]){
			if(!isVisited[next]){				
				isCycle(next);
			}
		}
	}
}
