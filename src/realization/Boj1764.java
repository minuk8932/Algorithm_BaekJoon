package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj1764 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken()); // didn't hear
		int M = Integer.parseInt(st.nextToken()); // didn't see
		
		String[] notSH = new String[N+M];
		
		for(int i = 0; i < N + M; i++){
			notSH[i] = br.readLine();
			
		}
		Arrays.sort(notSH);
		
		int cnt = 0;
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N + M; i++){
			if(notSH[i].equals(notSH[i - 1])){
				sb.append(notSH[i]).append(NEW_LINE);
				cnt++;
			}
		}
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
}
