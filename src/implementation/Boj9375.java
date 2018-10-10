package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9375번: 패션왕 신혜빈
 *
 *	@see https://www.acmicpc.net/problem/9375/
 *
 */
public class Boj9375 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통해 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());			
			HashMap<String, Integer> hm = new HashMap<>();
			
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String type = st.nextToken();
				
				if(hm.containsKey(type)) {		// 중복되는 key가 들어오는 경우 해쉬맵에 인덱스 증가 후 담음
					int idx = hm.get(type);
					hm.put(type, idx + 1);
				}
				else {
					hm.put(type, 1);
				}
			}
			
			int res = 1;
			for(int size: hm.values()) res *= size + 1;		// 각 경우의 수를 모두 곱하고
			
			sb.append(res - 1).append(NEW_LINE);		// 결과를 버퍼에 담음
		}
		
		System.out.println(sb.toString());				// 결과 값 한번에 출력
	}
}
