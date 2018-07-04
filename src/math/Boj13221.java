package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13221번: Manhattan
 *
 *	@see https://www.acmicpc.net/problem/13221/
 *
 */
public class Boj13221 {
	private static final int INF = 1_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int resX = 0, resY = 0;
		
		int diff = INF;
		
		int N = Integer.parseInt(br.readLine());
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			
			if(diff > Math.abs(x1 - x) + Math.abs(y1 - y)) {	// 맨하탄 거리 값 중 최소에 해당하는 좌표를 저장
				diff = Math.abs(x1 - x) + Math.abs(y1 - y);
				resX = x1;
				resY = y1;
			}
		}
		
		System.out.println(resX + " " + resY);		// 결과 값 출력
	}
}
