package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14659번: 한조서열정리하고옴ㅋㅋ
 *
 *	@see https://www.acmicpc.net/problem/14659/
 *
 */
public class Boj14659 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] mt = new int[N];
		int kill = 0, killer = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < N; i++){
			mt[i] = Integer.parseInt(st.nextToken());
		}
		
		killer = mt[0];
		int max = 0;
		
		for(int i = 1; i < N; i++){
			if(mt[i] <= killer){					// 현재 활잡이보다 낮은곳에 있을 경우
				kill++;
				
				max = Math.max(kill, max);	// 최대 킬수를 저장
			}
			else{										// 현재 활잡이보다 높은 활잡이가 있을 경우 활잡이 교체 후
				kill = 0;								// 킬수 초기화
				killer = mt[i];
			}
		}
		
		System.out.println(max);			// 최대 킬수 출력
	}
}
