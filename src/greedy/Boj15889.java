package greedy;
import java.util.Scanner;

/**
 * 
 * 	@author minchoba
 *	백준 15889번: 호 안에 수류탄이야!!
 */
public class Boj15889 {
	private static final String SAFE = "권병장님, 중대장님이 찾으십니다";
	private static final String DANGER = "엄마 나 전역 늦어질 것 같아";
	
	private static final int INF = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long[] pos = new long[INF];
		
		for(int i = 0; i < N; i++) {
			pos[i] = sc.nextLong();
		}
		
		boolean youngChang = false;		// 영창 여부
		
		long fire = 0;
		long max = 0;
		
		for(int i = 1; i < N; i++) {
			fire = sc.nextLong();			// 수류탄 던지는 거리
			
			if(max < fire + pos[i - 1]) {			// 현 위치의 사람이 수류탄을 던진 경우
				max = (long) (fire + pos[i - 1]);	// 최댓값보다 크면 최댓값 갱신
			}
			
			if(max < pos[i]) {			// 최댓값이 다음 위치의 인원보다 못미치는 경우
				youngChang = true;		// 영창가자
				break;
			}
		}
		
		System.out.println(youngChang ? DANGER : SAFE);		// 영창인 경우와 아닌 경우를 나눠 결과 값 출력
	}
}
