package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16466번: 콘서트
 *
 *	@see https://www.acmicpc.net/problem/16466/
 *
 */
public class Boj16466 {
	private static final int INF = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[] ticket = new boolean[INF];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());		// 나오지 않은 가장 작은 번호를 출력하는데 그 수는 최대 100만이므로
			if(num >= INF) continue;						// 100만보다 큰 수는 다 제낌
			
			ticket[num] = true;
		}
		
		System.out.println(getTicketNumber(ticket));		// 결과 출력
	}
	
	
	private static int getTicketNumber(boolean[] arr) {
		for(int i = 1; i < INF; i++) {						// 티켓팅이 안된 티켓번호를 반환
			if(!arr[i]) return i;
		}
		
		return INF;
	}
}
