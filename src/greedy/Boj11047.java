package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 11047번 : 동전 0
 * 
 * @see https://www.acmicpc.net/problem/2875
 * 
 */

public class Boj11047 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통해 입력을 받은 후 공백을 기준으로 입력받은 데이터를 구분
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int N = Integer.parseInt(st.nextToken());		// 동전 종류
		int K = Integer.parseInt(st.nextToken());		// 최종 목표 값
		int[] val = new int[N + 1];							// 동전 종류 별 값
		int cnt = 0;												// 최소 동전 갯수
		
		for(int i = 1; i < N + 1; i++){
			// 각 동전 별 값을 입력
			val[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = N; i > 0; i--){		// 역순으로, 큰 값 부터 비교해 보기 위해
			if(K >= val[i]){
				cnt += K / val[i];		// 구하려는 값이 각 동전마다 비교해서 크기가 작거나 같을때, 나눈 몫을 갯수로
				K = K % val[i];			// 나머지를 다시 최종 목표값으로 넣어서 반복 수행
			}
		}
		
		System.out.println(cnt);		// 필요한 동전의 최소 갯수를 출력
	}
}
