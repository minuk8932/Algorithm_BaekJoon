package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10448번: 유레카 이론
 *
 *	@see https://www.acmicpc.net/problem/10448/
 *
 */
public class Boj10448 {
	private static final String NEW_LINE = "\n";
	
	private static final int isTrue = 1;
	private static final int isFalse = 0;
	
	private static final int INF = 1_001;
	private static final int size = 50;
	private static int[] triNum = new int[size];
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		getTriNums();	// 1000보다 작은 삼각수들을 삼각수 메소드를 통해 구함
		
		while(T-- > 0 ) {
			int K = Integer.parseInt(br.readLine());
			
			int idx = 0;
			
			for(int i = 1; i < triNum.length; i++) {
				if(triNum[i] > K) {			// 주어진 수보다 작은 삼각수까지만 비교 할 수 있도록, 인덱스를 저장 후 종료
					idx = i;
					break;
				}
			}
			
			boolean hasTri = false;			// 삼각수 세개로 구해지는지 체크
			
LOOP:		for(int i = 1; i < idx; i++) {
				for(int j = 1; j < idx; j++) {					
					for(int k = 1; k < idx; k++) {						
						if(K - (triNum[i] + triNum[j] + triNum[k]) == 0) {	// 주어진 K와 세개의 수를 빼주었을 때 0이 나오면
							hasTri = true;							// hasTri를 참으로 바꾸고 전체 반복문 종료
							
							break LOOP;
						}
					}
				}
			}
			
			sb.append(hasTri ? isTrue : isFalse).append(NEW_LINE);	// 진리 변수에 따라 값을 버퍼에 저장
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 삼각수 고정 메소드
	 * 
	 */
	private static void getTriNums() {		
		for(int i = 1; i < size; i++) {
			triNum[i] = triNum[i - 1] + i;
			
			if(triNum[i] > INF) break;
		}
	}
}
