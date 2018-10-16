package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3009번: 네번째 점
 *
 *	@see https://www.acmicpc.net/problem/3009/
 *
 */
public class Boj3009 {
	private static final int INF = 1_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] x = new int[INF];
		int[] y = new int[INF];
		
		for(int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[Integer.parseInt(st.nextToken())]++;
			y[Integer.parseInt(st.nextToken())]++;
		}
	
		int resX = 0, resY = 0;	
		for(int i = 0; i < INF; i++) {				// 배열의 값이 1인 경우 아직 나오지 않은 점이 하나 존재
			if(x[i] == 0 && y[i] == 0) continue;
			
			if(x[i] == 1) resX = i;
			if(y[i] == 1) resY = i;
		}
		
		System.out.println(resX + " " + resY);		// 4번째 점 출력
	}
}
