import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj14466 {
	private static ArrayList<Integer>[] path;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		path = new ArrayList[N * N + 1];
		for(int i = 0; i < path.length; i++) {
			path[i] = new ArrayList<>();
		}
		
		link(N);
		
		while(K-- > 0) {
			
		}
		
	}
	
	private static void link(int n) {
		for(int current = 1; current < n * n + 1; current += n) {
			for(int edge = current; edge <current + n; edge++) {
				int next = edge + 1;
				if(next >= current + n) continue;
				
				path[next].add(edge);
				path[edge].add(next);
			}
			
			for(int edge = current; edge < current + n; edge++) {
				int next = edge + n;

				if(next > n * n) continue;
				path[edge].add(next);
				path[next].add(edge);
			}
		}
	}
}
