package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5671번: 호텔 방 번호
 *
 *	@see https://www.acmicpc.net/problem/5671/
 *
 */
public class Boj5671 {
	private static boolean[] except = new boolean[5_001];
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		init();				// 미리 중복 숫자를 갖는 수를 구함
		
		String line = "";
		while((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int count = 0;
			for(int i = N; i <= M; i++) {
				if(except[i]) continue;
				count++;					// 카운팅
			}
			
			sb.append(count).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static void init() {
		for(int i = 10; i < except.length; i++) {
			boolean[] check = new boolean[10];
			int num = i;
			
			while(num > 0) {
				int mod = num % 10;
				
				if(check[mod]) {
					except[i] = true;
					break;
				}
				check[mod] = true;
				
				num /= 10;
			}
		}
	}
}
