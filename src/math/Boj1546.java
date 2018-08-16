package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1546번: 평균
 *
 *	@see https://www.acmicpc.net/problem/1546/
 *
 */
public class Boj1546 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		double[] num = new double[N];
		double max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Double.parseDouble(st.nextToken());
			if(max < num[i]) max = num[i];					// 최댓 값 저장
		}
		
		double res = 0;
		
		for(int i = 0; i < N; i++) {					// 변형된 총합 구하기
			res += (num[i] / max) * 100;
		}
		
		res /= N;
		
		System.out.println(res);			// 평균 값 출력
	}
}
