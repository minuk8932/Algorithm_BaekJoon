package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15780번: 멀티탭 충분하니?
 *
 *	@see https://www.acmicpc.net/problem/15780/
 *
 */
public class Boj15780 {
	private static int N = 0;
	private static int K = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		boolean[][] A = new boolean[K][];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < K; i++) {
			A[i] = new boolean[Integer.parseInt(st.nextToken())];
		}
		
		System.out.println(process(A) == 0 ? "YES" : "NO");	// process 메소드 값이 0이면 YES or NO 출력
	}
	
	/**
	 * 
	 * @param 멀티탭 상태 배열
	 * @return 멀티탭이 부족한지 아닌지 반환
	 */
	private static int process(boolean[][] A) {
		for(int i = 0; i < K; i++) {
			for(int j = 0; j < A[i].length; j += 2) {
				A[i][j] = true;		// 0번째부터 격칸으로 콘센트를 끼운것으로 체크
				
				N--;			// 사용할 인원수를 각 경우마다 1명씩 줄여줌
				
				if(N == 0) return N;	// 만약 반복문이 끝나기전에 인원모두가 멀티탭 사용이 가능하면 N==0 값 반환
			}
		}
		
		return N;	// 멀티탭의 칸이 부족한 경우 남은 인원 N 반환
	}
}
