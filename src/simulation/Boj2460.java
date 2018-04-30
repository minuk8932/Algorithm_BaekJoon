package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2460번: 지능형 기차2
 *
 *	@see https://www.acmicpc.net/problem/2460/
 *
 */
public class Boj2460 {
	private static final String SPACE = " ";
	private static final int STATION = 10;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int max = 0, flow = 0;					// 사람이 가장 많은 경우, 역마다 기차내 탑승 인원
		
		for(int i = 0; i < STATION; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			flow += (-Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));	// 탑승인원을 지속적으로 변경해주고
			
			if(flow > max){				// 역마다 최대 인원을 갱신
				max = flow;
			}
		}
		
		System.out.println(max);	// 결과값 출력
	}
}
