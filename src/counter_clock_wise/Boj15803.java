package counter_clock_wise;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15803번: PLAYERJINAH’S BOTTLEGROUNDS
 *
 *	@see https://www.acmicpc.net/problem/15803/
 *
 */
public class Boj15803 {
	private static final int CCW = 3;
	private static final String IS_NOT_LINEAR = "WINNER WINNER CHICKEN DINNER!";
	private static final String IS_LINEAR = "WHERE IS MY CHICKEN?";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] x = new int[CCW];
		int[] y = new int[CCW];
		
		for(int i = 0; i < CCW; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = (x[1] - x[0]) * (y[2] - y[0]) - (y[1] - y[0]) * (x[2] - x[0]);	// 외적으로 직선상인지 구함, 0이면 직선상
		
		System.out.println(res == 0 ? IS_LINEAR : IS_NOT_LINEAR);	// 결과값 출력
	}
}
