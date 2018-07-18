import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14267 {
	private static final String SPACE = " ";
	
	private static ArrayList<Integer>[] mem = null;
	private static long[] res = null;
	
	private static int n = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		mem = new ArrayList[n + 1];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n + 1; i++) {
			mem[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < n + 1 ; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			
			if(tmp == - 1) tmp = 0;
			mem[tmp].add(i);
		}
		
		int[][] socialLife = new int[m][2];
		res = new long[n + 1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			socialLife[i][0] = Integer.parseInt(st.nextToken());
			socialLife[i][1] = Integer.parseInt(st.nextToken());
		}
		
		search(socialLife);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < n + 1; i++) {
			sb.append(res[i]).append(SPACE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void search(int[][] social) {		
		for(int[] s : social) {
			Queue<Integer> q = new LinkedList<>();
			
			res[s[0]] += s[1];
			
			q.offer(s[0]);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next : mem[current]) {
					if(next > 0 && next < n + 1) {						
						res[next] += s[1];
						
						q.offer(next);
					}
				}
			}
		}
	}
}
