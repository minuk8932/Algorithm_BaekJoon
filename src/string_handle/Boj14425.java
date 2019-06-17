package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14425번: 문자열 집합
 *
 *	@see https://www.acmicpc.net/problem/14425/
 *
 */
public class Boj14425 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> hm = new HashMap<>();
		while(N-- > 0) {
			hm.put(br.readLine(), 1);
		}
		
		int count = 0;
		while(M-- > 0) {
			try {
				count += hm.get(br.readLine());		// 존재하면 +1
			}
			catch (Exception e) {
				continue;
			}
		}
		
		System.out.println(count);
	}
}
