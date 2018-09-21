package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 * 	백준 1051번: 숫자 사각형
 * 
 * 	@see https://www.acmicpc.net/problem/1051/
 * 
 */
public class Boj1051 {
	public static void main(String[] arsg) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(N < M ? colSearch(map, N, M) : rowSearch(map, N, M));
	}
	
	/**
	 * 가로의 길이가 긴 경우
	 * 
	 */
	private static int colSearch(char[][] arr, int n, int m) {
		int max = 0;
		int loop = n;
		
		while(loop >= 0) {
			for(int i = 0; i + loop < n; i++) {
				for(int j = 0; j + loop < m; j++) {					
					if(isSame(arr[i][j], arr[i + loop][j], arr[i][j + loop], arr[i + loop][j + loop])) {	// 사각형 각 꼭지점을 받아 메소드를 실행
						int size =(loop + 1) * (loop + 1);	// 그때의 사각형 넓이를 구하고
						
						if(max < size) max = size;		// 최댓값으로 저장
					}
				}
			}
			
			loop--;			// 사각형의 변의 길이를 줄여가며
		}
		
		return max;			// 최대 사각형 크기를 반환
	}
	
	/**
	 * colSearch와 같은 방식으로 동작, 단 세로의 길이가 더 긴 경우
	 * 
	 */
	private static int rowSearch(char[][] arr, int n, int m) {
		int max = 0;
		int loop = m;
		
		while(loop >= 0) {
			for(int j = 0; j + loop < m; j++) {
				for(int i = 0; i + loop < n; i++) {					
					if(isSame(arr[i][j], arr[i + loop][j], arr[i][j + loop], arr[i + loop][j + loop])) {
						int size =(loop + 1) * (loop + 1);
						
						if(max < size) max = size;
					}
				}
			}
			
			loop--;
		}
		
		return max;
	}
	
	/**
	 * 각 네 꼭지점 값이 동일한지 확인 후 값 반환 메소드
	 * 
	 */
	private static boolean isSame(int a, int b, int c, int d) {		
		return a == b && b == c && c == d ? true : false;
	}
}
