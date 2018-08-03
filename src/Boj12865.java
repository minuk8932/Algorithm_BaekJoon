import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12865 {
	private static final int INF = 101;
	private static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Back[] map = new Back[N + 1];
		map[0] = new Back(-1, -1);
		
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int weigh = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			map[i] = new Back(weigh, value);
		}
		
		Arrays.sort(map);
//		for(int i = 1; i < N + 1; i++) System.out.println(map[i].w + " " + map[i].c);
	
		search(N, K, map);
		System.out.println(max);
	}
	
	private static void search(int N, int W, Back[] knap) {
		int[][] total = new int[N + 1][N + 1];
		int[] weight = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			weight[i] = knap[i].w;
			total[i][i] = knap[i].c;
			
			Queue<Back> q = new LinkedList<>();
			q.offer(new Back(i, knap[i].c));
			
			while(!q.isEmpty()) {
				Back current = q.poll();
				
				for(int next = current.w; next < N + 1; next++) {
					weight[i] += knap[next].w;
					total[i][next] += knap[next].c;
					
					if(weight[i] <= W) {
						
						if(max < total[i][next]) max = total[i][next];
						q.offer(new Back(next, knap[next].c));
					}					
				}
			}
		}
	}
	
	private static class Back implements Comparable<Back>{
		int w;
		int c;
		
		public Back(int w, int c) {
			this.w = w;
			this.c = c;
		}

		@Override
		public int compareTo(Back b) {
			return b.w > this.w ? -1 : 1;
		}
	}
}
