import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj10816 {
	private static final String SPACE = " ";
	private static final int MAX = 10_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] hasC = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < N; i++){
			hasC[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] chkC = new int[M];
		int[][] cnt = new int[2][MAX];
		int[] chk = new int[M];
		
		st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < M; i++){
			int x = Integer.parseInt(st.nextToken());
			chkC[i] = x;
			chk[i] = x;
		}
		
		Arrays.sort(chkC);
		
		for(int i = 0; i < hasC.length; i++){			
			int left = 0, right = M - 1;
			int mid = 0;
			
			while(right - left >= 0){
				mid = (left + right) / 2;
				
				if(hasC[i] == chkC[mid]){
					if(hasC[i] >= 0){
						cnt[0][hasC[i]]++;
					}
					else{
						int num =  hasC[i] * (-1);
						cnt[1][num]++;
					}
					break;
				}
				else if (hasC[i] < chkC[mid]){
					right = mid - 1;
				}
				else{
					left = mid + 1;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++){
			if(chk[i] >= 0){
				sb.append(cnt[0][chk[i]]).append(SPACE);
			}
			else{
				int num = chk[i] * (-1);
				sb.append(cnt[1][num]).append(SPACE);
			}
		}
		System.out.println(sb.toString());
	}
}
