package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 11050번: 이항계수 1
 * 
 * @see https://www.acmicpc.net/problem/11050/
 * 
 */
public class Boj11050 {
	private static final int INF = 11;
	private static int[][] arr = new int[11][11];
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(combine(N, K));		// 조합 메소드를 통한 결과값 출력
	}
	
	/**
	 * 
	 * @param nCr
	 * @return nCr 결과
	 */
	private static int combine(int n, int r) {
		if(r == 0 || n == r) return 1;	// nCn or nC0 의 경우: 무조건 1 반환
		if(arr[n][r] > 0) return arr[n][r];	// 그 외 nCr이 0보다 큰 경우, 즉 결과값이 저장되어 있는 경우 nCr 결과 반환
		
		return arr[n][r] = combine(n-1, r-1) + combine(n-1, r);	// 조합의 정의에 따른 값을 재귀적으로 호출해 nCr 결과 출력
	}
}
