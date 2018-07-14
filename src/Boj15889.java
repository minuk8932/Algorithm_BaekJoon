import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15889 {
	private static final String SAFE = "권병장님, 중대장님이 찾으십니다";
	private static final String DANGER = "엄마 나 전역 늦어질 것 같아";
	
	private static final int INF = 1000_001;
	
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
		
		int loop = 0;
		
		if(N > 1) {
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < INF; i++) {
				if(loop == N) break;
				if(pos[i] == -1) continue;
				
				if(pos[i] == 1) {
					range[i] = Integer.parseInt(st.nextToken());
					loop++;
				}
				else {
					int[] tmp = new int[pos[i]];
					
					for(int j = 0; j < pos[i]; j++) {
						if(loop == N) break;
						
						tmp[j] = Integer.parseInt(st.nextToken());
						loop++;
					}
					
					range[i] = getMax(tmp);
				}
			}
		}
		
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
		
		
		return false;
	}	
}
