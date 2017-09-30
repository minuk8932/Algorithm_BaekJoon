import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
		
		String[] hear = new String[N];
		String[] see = new String[M];
		
		for(int i = 0; i < N; i++){
			hear[i] = br.readLine();
		}
		for(int i = 0; i < M; i++){
			see[i] = br.readLine();	
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				if(hear[i].equals(see[j])){
					sb.append(hear[i]).append(NEW_LINE);
				}
			}
		}
		System.out.println(sb.toString());
	}
}
