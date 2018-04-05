package counter_clock_wise;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11758번 : CCW(Counter-Clock Wise)
 *
 *	@see https://www.acmicpc.net.problem/11758/
 *
 */
public class Boj11758 {
	private static final String SPACE =  " ";
	private static final int CCW = 3;
	
	public static void main(String[] args)throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] x = new int[CCW];
		int[] y = new int[CCW];
		
		for(int i = 0; i < CCW; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = (x[1] - x[0]) * (y[2] - y[0]) - (y[1] - y[0]) * (x[2] - x[0]);		// CCW 실행
		
		System.out.println(res == 0 ? 0 :  res > 0 ? 1 : -1);		// 해당 계산결과에 따라 res < 0 : 시계방향, res > 0 : 반시계방향, res == 0 : 일직선
	}
}
