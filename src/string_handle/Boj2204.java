package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 2204번: 도비의 난독증 테스트
 *
 *	@see https://www.acmicpc.net/problem/2204/
 *
 */
public class Boj2204 {
	private static final String NEW_LINE = "\n";
	
	private static class Comp implements Comparable<Comp>{
		String str;
		
		public Comp(String str) {
			this.str = str;
		}

		@Override
		public int compareTo(Comp c) {
			return this.str.compareToIgnoreCase(c.str);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			Comp[] arr = new Comp[N];
			for(int i = 0; i < N; i++) {
				arr[i] = new Comp(br.readLine());
			}
			
			Arrays.sort(arr);
			sb.append(arr[0].str).append(NEW_LINE);		// 정렬 후 결과 반환
		}
		
		System.out.println(sb);
	}
}
