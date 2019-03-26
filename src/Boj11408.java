import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj11408 {
	private static ArrayList<Integer>[] connected;
	private static int[][] flow;
	private static int[][] cost;
	private static int[][] capacity;
	
	private static final int INF = 100_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		connected = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			connected[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			while(count-- > 0) {
				int work = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
			}
		}
		
		System.out.println();
	}
}
