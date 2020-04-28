package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15723번: n단 논법
 *
 *	@see https://www.acmicpc.net/problem/15723/
 *
 */
public class Boj15723 {	
	private static final int C_TO_I = 97;
	private static final int ALPHA = 26;

	private static final String TRUE = "T";
	private static final String FALSE = "F";
	private static final String NEW_LINE = "\n";
	
	private static boolean[][] isTrue = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		isTrue = new boolean[ALPHA][ALPHA];				// 기본 논제에 대한 값 저장
		
		while(n-- > 0){
			char[] exp = br.readLine().toCharArray();	// 전제를 한줄씩 받아 온 후
			int from = exp[0] - C_TO_I;							// 전제 내의 필요 식만 가져와서 정수형으로 변환
			int to = exp[exp.length - 1] - C_TO_I;
			
			isTrue[from][to] = true;		// 변환한 식을 인덱스로 설정 후 해당 논제를 참으로 초기화
		}
		
		logic();				// 3단 논법 (floyd-washall 알고리즘) 수행
		
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());			// 3단논법 검증 시행
		
		while(m-- > 0){
			char[] exp = br.readLine().toCharArray();		// 전제를 한줄씩 받아와서
			int from = exp[0] - C_TO_I;								// 위와같이 필요 식을 가져와 정수형으로 변환
			int to = exp[exp.length - 1] - C_TO_I;
			
			sb.append(isTrue[from][to] ? TRUE : FALSE).append(NEW_LINE);		// 해당 전제가 참이면 T 거짓이면 F를 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 		Floyd-Washall 알고리즘을 통한 3단 논법 수행
	 */
	private static void logic(){
		for(int v = 0; v < ALPHA; v++){
			for(int s = 0; s < ALPHA; s++){
				for(int e = 0; e < ALPHA; e++){					
					if(isTrue[s][v] && isTrue[v][e]){		// 1번 전제와 2번 전제가 모두 참 일때 해당 3번 전제도 참으로 바꾸어줌 (3단 논법)
						isTrue[s][e] = true;
					}
				}
			}
		}
	}
}
