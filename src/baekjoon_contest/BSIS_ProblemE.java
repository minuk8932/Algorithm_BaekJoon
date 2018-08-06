package baekjoon_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BSIS_ProblemE {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			arr[i] = a;
		}
		
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			int cnt = 1;			
			
			for(int j = i + 1; j < N; j++) {
				if(arr[i] + cnt == arr[j]) {
					cnt++;
				}
			}
			
			if(max < cnt) max = cnt;
		}
		
		
		System.out.println(max);
	}
}
