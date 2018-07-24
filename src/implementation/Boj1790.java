package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1790번: 수 이어 쓰기 2
 *
 *	@see https://www.acmicpc.net/problem/1790/
 *
 */
public class Boj1790 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		int size = 0, cnt = 0;
		
		for(int i = 1; i < N + 1; i++){
			int cipher = 1;		// 자릿수
			int num = i;		// 현재 체크할 수
			
			while(num / 10 > 0) {	// 해당 수의 몫이 0보다 큰 경우
				num /= 10;
				cipher++;		// 자릿수를 하나씩 늘려줌
			}
			
			size += cipher;		// 자릿수를 더하고
			cnt++;				// 정지할 경우 진행한 숫자까지 값을 구함
			
			if(size >= K) break;		// 사이즈가 알아내야 할 K보다 크거나 같은 경우 종료, 구하려는 자릿수가 넘어간 경우
		}
		
		// size가 K 까지 도달 한 경우 해당 숫자에서 size와 K 차이만큼 번째의 숫자를 구함, 그 외 -1
		System.out.println(size >= K ? (int) (cnt / Math.pow(10, size - K)) % 10 : -1);
	}
}
