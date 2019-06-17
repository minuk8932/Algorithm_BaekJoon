package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 * 	@author minchoba
 *	백준 3022번: 식사 예절
 *
 *	@see https://www.acmicpc.net/problem/3022/
 *
 */
public class Boj3022 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> status = new HashMap<>();
		int N = Integer.parseInt(br.readLine());
		
		int total = 0, result = 0;
		
		while(N-- > 0) {
			String name = br.readLine();
			
			if(!status.containsKey(name)) {				// 아직 쿠키를 안가져간 경우
				status.put(name, 1);
			}
			else {
				if(total - status.get(name) < status.get(name)) result++;		// 쿠키를 가져가기전 다른 아이들의 총 쿠키 수 보다 많이 갖고 있는 경우: 혼남
				status.put(name, status.get(name) + 1);							// 쿠키 가져가기
			}
			
			total++;			// 현재까지 아이들이 가져간 총 쿠키 수
		}
		
		System.out.println(result);
	}
}
