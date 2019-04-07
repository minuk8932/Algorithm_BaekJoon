package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14670번: 병약한 영정
 *
 * 	@see https://www.acmicpc.net/problem/14670/
 * 
 */
public class Boj14670 {
	private static final String DIE = "YOU DIED";
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] pills = new int[101];
		Arrays.fill(pills, -1);
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pills[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		int R = Integer.parseInt(br.readLine());
		
		while(R-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			int[] tmp = new int[count];
			
			boolean flag = false;
			
			for(int i = 0; i < count; i++) {
				tmp[i] = pills[Integer.parseInt(st.nextToken())];
				if(tmp[i] == -1) flag = true;
			}
			
			if(flag) sb.append(DIE).append(NEW_LINE);			// 하나라도 처리 불가인 경우
			else {
				for(int i = 0; i < count; i++) {				// 모두 처리 가능
					sb.append(tmp[i]).append(SPACE);
				}
				
				sb.append(NEW_LINE);
			}
		}
		
		System.out.println(sb);
	}
}
