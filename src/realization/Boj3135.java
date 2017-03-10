package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj3135 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		
		int[] bookmrk = new int[N];
		int compA = (int) Math.abs(A - B);
		int[] compB = new int[N];
		
		for(int i = 0; i < N; i++){
			bookmrk[i] = Integer.parseInt(br.readLine());
			compB[i] = (int) Math.abs(B - bookmrk[i]);
		}
		
		Arrays.sort(compB);
		StringBuilder sb = new StringBuilder();
		
		int cnt = 0;		
		if(compA <= compB[0]){
			sb.append(compA);
		}
		else{
			cnt++;
			if(compB[0] == 0){
				sb.append(cnt);
			}
			else{
				sb.append(compB[0]+cnt);
			}
		}
		System.out.println(sb.toString());
	}

}
