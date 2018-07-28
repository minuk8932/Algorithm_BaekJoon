package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 * 	@author minchoba
 *	백준 10527번: Judging Troubles
 *
 *	@see https://www.acmicpc.net/problem/10527/
 *
 */
public class Boj10527 {	
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> hm = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			
			if(!hm.containsKey(str)) {		// 처음 들어오는 경우 해당 키와 값 1을 저장
				hm.put(str, 1);
			}
			else {
				int val = hm.get(str) + 1;		// 이미 존재하는 경우, 해당 키의 값을 가져와서 +1 후 저장
				hm.put(str, val);
			}
		}		
		
		int res = 0;
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			if(hm.containsKey(line)) {			// 들어오는 문자열을 해쉬의 키와 비교			
				int val = hm.get(line) - 1;		// 같은게 있다면 해당 키의 값을 -1 한 후
				if(val < 0) continue;			// 0보다 크거나 같을 때
				
				res++;						// 카운트 +1
				
				hm.put(line, val);			// 해쉬맵에 값 저장
			}
		}
		
		System.out.println(res);		// 결과값 출력
	}
}
