import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj18250 {
	private static HashMap<Integer, Integer> visit = new HashMap<>();
	private static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			path[a].add(b);
			path[b].add(a);
		}
		
		getPath(N, path);
	}
	
	private static void getPath(int n, ArrayList<Integer>[] list) {		
		for(int from = 0; from < n; from++) {
			if(list[from].size() == 1) {
				
			}
		}
		
		System.out.println(result);
	}
}
