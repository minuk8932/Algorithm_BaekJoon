package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2875번: 대회 or 인턴
 *
 *	@see https://www.acmicpc.net/problem/2875/
 *
 */
public class Boj2875 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int res = 0;
		
		while (N >= 2 && M >= 1 && N + M >= K + 3) {	// N:M 비율을 유지하는 동시에, 두 값을 합친 값이 K+3보다 클 때 -> 팀 생성 가능
			N -= 2;
			M -= 1;
			res += 1;
		}
		
		System.out.println(res);		// 생성된 팀의 수 출력
	}
}
