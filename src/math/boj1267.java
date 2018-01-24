package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 1267번: 핸드폰 요금
 *
 *	@see https://www.acmicpc.net/problem/1267
 *
 */

public class boj1267 {
	private static final String SPACE = " ";
	private static final int M = 60;
	private static final int Y = 30;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] call = new int[N];							// 사용량
		int[] cost = new int[2];							// 요금
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < N; i++){
			call[i] = Integer.parseInt(st.nextToken());
			cost[0] += ((call[i] / Y) + 1) * 10;
			cost[1] += ((call[i] / M) + 1) * 15;
		}
		br.close();
		
		int res = 0;
		
		// 경우에 따른 결과 출력
		if(cost[0] == cost[1]){													// 두 요금제의 비용이 같을 시
			System.out.println("Y" + " " + "M" + " " + cost[0]);
		}
		else{
			res = Math.min(cost[0], cost[1]);
			
			if(res == cost[0]){													// 비용이 다를때 영식 요금제가 더 저렴할 시
				System.out.println("Y" + " " + cost[0]);
			}
			else{																		// 비용이 다를때 민식 요금제가 더 저렴할 시
				System.out.println("M" + " " + cost[1]);
			}
		}
	}
}
