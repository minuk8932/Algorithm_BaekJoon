package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11098번: 첼시를 도와줘!
 *
 *	@see https://www.acmicpc.net/problem/11098/
 *
 */
public class Boj11098 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		while(n-- > 0) {
			int p = Integer.parseInt(br.readLine());
			int[] c = new int[p];
			String[] name = new String[p];
			
			for(int i = 0; i < p; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				c[i] = Integer.parseInt(st.nextToken());
				name[i] = st.nextToken();
			}
			
			int max = 0, idx = 0;
			
			for(int i = 0; i < p; i++) {
				if(max < c[i]) {			// 가장 비싼 선수에 해당하는 순서를 저장
					max = c[i];
					idx = i;
				}
			}
			
			sb.append(name[idx]).append(NEW_LINE);		// 가장 비싼 선수 이름을 버퍼에 저장
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
