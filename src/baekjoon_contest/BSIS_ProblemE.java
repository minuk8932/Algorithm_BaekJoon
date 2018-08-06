package baekjoon_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BSIS_ProblemE {
	private static int[] seq = new int[1_000_001];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			seq[i] = a;
		}
		
		int cnt = 1;
		int a1 = seq[0];
		
		for(int i = 1; i < seq.length; i++) {
			if(seq[i] == a1 + 1) {
				cnt++;
				a1 = seq[i];
			}
		}
		
		System.out.println(cnt);
	}
}
