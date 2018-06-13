import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3060 {	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int tmpN = N;
			
			int[] pigs = new int[6];
			int[] curPigs = new int[6];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < pigs.length; i++) {
				pigs[i] = Integer.parseInt(st.nextToken());
				tmpN -= pigs[i];
			}
			
			int days = 1, cnt = 0;
			
			for(int i = 0; tmpN >= 0; i++) {
				int idx = i % 6;
				int left = idx - 1;
				int right = idx + 1;
				int jump = idx >= 3 ? idx - 3 : idx + 3;
				
				if(left < 0) left = 5;				
				if(right > 5) right = 0;
				if(idx == 0) days++;
				
				curPigs[idx] += (pigs[left] + pigs[right] + pigs[jump]);		// 전날 돼지들이 쳐먹은것을 현재 먹이에
				System.out.println(curPigs[idx]);
				cnt++;
				
				tmpN -= curPigs[idx];
				if(cnt == 6) {
					tmpN = N;
					
					for(int j = 0; j < curPigs.length; j++) {
						pigs[j] = curPigs[j];
					}
				}
			}
			
			sb.append(days).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
