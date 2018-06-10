package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10424번: 알고리즘 기말고사
 *
 *	@see https://www.acmicpc.net/problem/10424/
 *
 */
public class Boj10424 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] score = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {						// idx: 중간고사 순위, 값: 기말고사 순위
			score[Integer.parseInt(st.nextToken())] = i + 1;
		}
		
		for(int i = 1; i < N + 1; i++) {				// 중간고사 기준 기말고사 순위에 따른 학생별 만족도를 버퍼에 담음
			sb.append(i - score[i]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
