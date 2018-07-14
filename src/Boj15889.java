import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15889 {
	private static final String SAFE = "권병장님, 중대장님이 찾으십니다";
	private static final String DANGER = "엄마 나 전역 늦어질 것 같아";
	
	private static final int INF = 1_000_001;
	
	private static boolean isDelivered = true;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] pos = new int[INF];
		long[] range = new long[INF];
		
		Arrays.fill(pos, -1);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			pos[Integer.parseInt(st.nextToken())]++;
		}
		
		if(N > 1) {
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < INF; i++) {				
				if(!st.hasMoreTokens()) break;
				if(pos[i] == -1) continue;
				
				if(pos[i] == 0) { 
					range[i] = Integer.parseInt(st.nextToken());
				}
				else {
					int[] tmp = new int[pos[i] + 1];
					
					for(int j = 0; j < tmp.length; j++) {
						if(!st.hasMoreTokens()) break;
						
						tmp[j] = Integer.parseInt(st.nextToken());
					}
					
					range[i] = getMax(tmp);
				}
			}
		}
		
//		for(int i = 0; i < INF; i++) {
//			if(pos[i] == -1) continue;
//			
//			System.out.print(i + " " + range[i] + '\t');
//		}
//		System.out.println(loop);
		
		System.out.println(giveAndTake(pos, range, N) ? SAFE : DANGER);
	}
	
	private static int getMax(int[] t) {
		int max = 0;
		
		for(int i = 0; i < t.length; i++) {
			if(max < t[i]) {
				max = t[i];
			}
		}
		
		return max;
	}
	
	private static boolean giveAndTake(int[] p, long[] r, int N) {
		boolean[] tmp = new boolean[INF];
		tmp[0] = true;
		
		for(int give = 0; give < INF; give++) {
			if(p[give] == -1) continue;
			
			if(give + r[give] + 1 < INF) {
				for(int take = give + 1; take < give + r[give] + 1; take++) {
					if(p[take] == -1 || tmp[take]) continue;
					
					tmp[take] = true;
				}
			}
		}
		
		for(int i = 0; i < INF; i++) {
			if(p[i] != -1 && !tmp[i]) {
				isDelivered = false;
				break;
			}
		}
		
		return isDelivered;
	}	
}
