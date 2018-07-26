import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2812 {
	private static final char ZERO = '0';
	private static final char EXCEPT = 'A';
	private static final int INF = 500_001;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		char[] num = new char[N];
		int max = 0, maxIdx = INF;
		
		String line = br.readLine();
		for(int i = 0; i < N; i++) {
			num[i] = line.charAt(i);
			
			if(max < (num[i] - ZERO)) {
				max = num[i] - ZERO;
				maxIdx = i;
			}
		}
		
		if(maxIdx == K) {
			for(int i = 0; i < maxIdx; i++) {
				num[i] = EXCEPT;
			}
		}
		else {
			int loop = K;
			
			if(maxIdx < K) {
				for(int i = 0; i < maxIdx; i++) {
					num[i] = EXCEPT;
				}
				
				loop--;
			}
			
			int[] ten = new int[10];
			
			for(int i = maxIdx + 1; i < N; i++) {
				ten[num[i] - ZERO]++;
			}
			
LOOP:		for(int i = 0; i < ten.length; i++) {
				for(int j = maxIdx + 1; j < N; j++) {
					if(ten[i] == 0) continue;
					
					if(i == (num[j] - ZERO)) {
						num[j] = EXCEPT;
						ten[i]--;
						loop--;
					}
					
					if(loop == 0) break LOOP;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(char tmp: num) {
			if(tmp == EXCEPT) continue;
			sb.append(tmp);
		}
		
		System.out.println(sb.toString());
	}
}
