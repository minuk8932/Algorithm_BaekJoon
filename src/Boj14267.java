import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14267 {
	private static final int INF = 100_001;
	private static final String SPACE = " ";
	
	private static ArrayList<Integer>[] mem = null;
	private static int[] res = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		mem = new ArrayList[n + 1];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n + 1; i++) {
			mem[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < n + 1; i++) {
			mem[i].add(Integer.parseInt(st.nextToken()));
		}
		
		int[][] socialLife = new int[m][2];
		res = new int[n + 1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			socialLife[i][0] = Integer.parseInt(st.nextToken());
			socialLife[i][1] = Integer.parseInt(st.nextToken());
		}
		
		search(socialLife);
	}
	
	private static void search(int[][] sl) {
		for(int[] social : sl) {
			Queue<Integer> q = new LinkedList<>();
			
			q.offer(social[0]);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next : mem[current]) {
					
				}
			}
		}
	}
}
