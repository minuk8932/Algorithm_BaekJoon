package tree_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1620번: 나는야 포켓몬 마스터 이다솜
 *
 *	@see https://www.acmicpc.net/problem/1620/
 *
 */
public class Boj1620 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> hm = new HashMap<>();
		String[] mop = new String[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			String mon = br.readLine();
			hm.put(mon, i);
			mop[i] = mon;
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(M-- > 0) {
			String call = br.readLine();
			
			if(call.charAt(0) >= '0' && call.charAt(0) <= '9') {		// 숫자가 들어오는 경우 배열의 값을 받아 버퍼에 저장
				int callNum = Integer.parseInt(call);
				
				sb.append(mop[callNum]).append(NEW_LINE);
			}
			else {													// 문자가 들어오는 경우 해쉬맵의 값을 버퍼에 저장
				sb.append(hm.get(call).intValue()).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
