package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14720번: 우유 축제
 *
 *	@see https://www.acmicpc.net/problem/14720/
 *
 */
public class Boj14720 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int milks = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(N-- > 0) {
			if((milks % 3) == Integer.parseInt(st.nextToken())) {	// 반복문을 돌면서 먹은 우유 수를 3으로 나눈 나머지와 입력값이 같을때만 우유 갯수 +1
				milks++;
			}
		}
		
		System.out.println(milks);		// 최종 우유 갯수 출력
	}
}
